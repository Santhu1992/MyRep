package com.snatch.service;

import com.snatch.dto.BidTransactionDTO;
import com.snatch.dto.ItemDTO;
import com.snatch.entity.BidTransaction;
import com.snatch.exception.ItemException;
import com.snatch.repo.BidTransactionRepo;
import com.snatch.util.BidTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BidServiceImpl implements BidService{

    @Autowired
    BidTransactionRepo bidTransactionRepo;

    @Autowired
    RestTemplate itemServiceTemplate;

    @Autowired
    BidTransactionMapper bidTransactionMapper;

    private final static Logger logger = LoggerFactory.getLogger(BidServiceImpl.class.getSimpleName());

    @Override
    public BidTransactionDTO bidItem(Integer itemId, String userId, BigDecimal currentBidPrice) throws ItemException {
        ItemDTO itemDTO = getItem(itemId);
        logger.info("Retrieved item details {}",itemDTO);
        if(canBid(itemDTO)){
            if(currentBidPrice.compareTo(itemDTO.getMinimumBid())>=0) {
                logger.info("current bid is greater than minimum bid");
                if(isBidHigher(itemDTO,currentBidPrice)){
                    return createBidTransaction(itemDTO, userId, currentBidPrice);
                } else{
                    logger.warn("new bid should be greater than current maximum bid price");
                    throw new ItemException("new bid should be greater than current maximum bid price");
                }

            } else {
                logger.warn("new bid should be greater than or equal to minimum bid");
                throw new ItemException("new bid should be greater than minimum bid");
            }
        } else{
            throw new ItemException("item is not active to participate for bidding or sold out");
        }

    }

    private boolean isBidHigher(ItemDTO itemDto,BigDecimal currentBidPrice) {
        Optional<BidTransactionDTO> highestBidder = highestBidder(itemDto.getItemId());
        if(highestBidder.isPresent()) {
            BigDecimal priceQuote = highestBidder.get().getBidPrice();
            logger.info("price quote for item {} is {}", itemDto.getItemId(), priceQuote);
            if (currentBidPrice.compareTo(priceQuote.add(itemDto.getMinimumIncrement())) >= 0) {
                logger.info("current bid is higher, currentBidPrice {}, minimumIncrement {}",currentBidPrice,itemDto.getMinimumIncrement());
                return true;
            }
            else{
                logger.warn("current bid is lesser than priceQuote + minimumIncrement");
                return false;
            }
        }
        else {
            logger.info("first bid for item {}",itemDto.getItemId());
            return true;
        }
    }

    private boolean canBid(ItemDTO itemDTO) throws ItemException {
        logger.info("checking bid eligibility");
        if(itemDTO.getActive()){
            if(itemDTO.getOpen()){
                if( LocalDateTime.now().isBefore(itemDTO.getExpirationTime())) {
                    logger.info("item eligible to bid");
                    return true;
                } else {
                    logger.warn("time expired closing auction");
                    closeAuction(itemDTO.getItemId());
                    return false;
                }
            }
            else{
                logger.warn("item is sold out");
                return false;
            }
        }
        else {
            logger.warn("item is not active to participate for bidding");
            return false;
        }
    }

    private BidTransactionDTO createBidTransaction(ItemDTO itemDTO, String userId, BigDecimal currentBidPrice) {
        logger.info("inside createBidTransaction");
        BidTransaction bidTransaction = new BidTransaction();
        bidTransaction.setItemId(itemDTO.getItemId());
        bidTransaction.setBidderId(userId);
        bidTransaction.setSellerId(itemDTO.getAddedBy());
        bidTransaction.setBidPrice(currentBidPrice);
        bidTransaction.setTransactionTime(LocalDateTime.now());
        bidTransaction.setValid(Boolean.TRUE);
        return bidTransactionMapper.prepareDTO(bidTransactionRepo.saveAndFlush(bidTransaction));
    }

    private ItemDTO getItem(Integer itemId) {
        return itemServiceTemplate.getForObject("http://localhost:8081/item/"+itemId,ItemDTO.class);
    }


    @Override
    public BigDecimal priceQuote(Integer itemId) {
        Optional<BidTransactionDTO> optionalBidTransactionDTO = highestBidder(itemId);
        return optionalBidTransactionDTO.isPresent() ? optionalBidTransactionDTO.get().getBidPrice() : BigDecimal.ZERO ;
    }

    private Optional<BidTransactionDTO> highestBidder(Integer itemId)  {
        Optional<BidTransaction> bidTransaction = bidTransactionRepo.findFirstByItemIdAndIsValidTrueOrderByBidPriceDesc(itemId);
        if(bidTransaction.isPresent()){
            return Optional.of(bidTransactionMapper.prepareDTO(bidTransaction.get()));
        }
        else{
            return Optional.empty();
        }

    }

    @Override
    public BidTransactionDTO closeAuction(Integer itemId) throws ItemException {
        ItemDTO itemDTO = getItem(itemId);
        Optional<BidTransactionDTO> highestBid = highestBidder(itemId);
        if(highestBid.isPresent()) {
            if (highestBid.get().getBidPrice().compareTo(itemDTO.getReservePrice()) > 0) {
                Map<String, String> map = new HashMap<>();
                map.put("itemId", String.valueOf(itemId));
                map.put("userId", highestBid.get().getBidderId());
                map.put("buyOutPrice", String.valueOf(highestBid.get().getBidPrice()));
                itemServiceTemplate.postForEntity("http://localhost:8081/item/buyout", map, Boolean.class);
                return highestBid.get();
            }
            else{
                Map<String, String> map = new HashMap<>();
                map.put("itemId",String.valueOf(itemId));
                itemServiceTemplate.postForEntity("http://localhost:8081/item/terminateauction",map, Boolean.class);
                throw new ItemException("current bid did not reach reserve price item moved to house");
            }
        }
        else{
            Map<String, String> map = new HashMap<>();
            map.put("itemId",String.valueOf(itemId));
            itemServiceTemplate.postForEntity("http://localhost:8081/item/terminateauction",map, Boolean.class);
            throw new ItemException("no bid received during auction so auction closed");
        }
    }

    @Override
    public BidTransactionDTO hammerBid(Integer itemId, String userId, BigDecimal hammerPrice) throws ItemException {
        ItemDTO itemDTO = getItem(itemId);
        if(itemDTO.getReservePrice()==null || hammerPrice.compareTo(itemDTO.getReservePrice())>=0){
            BidTransactionDTO bidTransactionDTO= bidItem(itemId,userId,hammerPrice);
            Map<String,String> map = new HashMap<>();
            map.put("itemId", String.valueOf(itemId));
            map.put("userId",userId);
            map.put("buyOutPrice", String.valueOf(hammerPrice));
            itemServiceTemplate.postForEntity("http://localhost:8081/item/buyout",map,Boolean.class);
            return bidTransactionDTO;
        }
        else{
            throw new ItemException("current bid did not reach reserve price item moved to house");
        }
    }

    @Override
    public BidTransactionDTO cancelBid(String bidId) throws ItemException {
        BidTransaction bidTransaction = bidTransactionRepo.findById(bidId).orElseThrow(()->new ItemException("Invalid BidId"));
        bidTransaction.setValid(Boolean.FALSE);
        bidTransaction.setCancellationTIme(LocalDateTime.now());
        return bidTransactionMapper.prepareDTO(bidTransactionRepo.saveAndFlush(bidTransaction));
    }
}
