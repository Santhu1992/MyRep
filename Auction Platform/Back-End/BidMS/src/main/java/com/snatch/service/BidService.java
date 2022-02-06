package com.snatch.service;

import com.snatch.dto.BidTransactionDTO;
import com.snatch.exception.ItemException;

import java.math.BigDecimal;


public interface BidService {

    BidTransactionDTO bidItem(Integer itemId, String userId, BigDecimal currentBidPrice) throws ItemException;
    BigDecimal priceQuote(Integer itemId);
    BidTransactionDTO closeAuction(Integer itemId) throws ItemException;
    BidTransactionDTO hammerBid(Integer itemId, String userId, BigDecimal hammerPrice) throws ItemException;
    BidTransactionDTO cancelBid(String bidId) throws ItemException;

}
