package com.snatch.controller;

import com.snatch.dto.BidTransactionDTO;
import com.snatch.exception.ItemException;
import com.snatch.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(path = "bid")
public class BidController {

    @Autowired
    BidService bidService;

    private final static Logger logger = LoggerFactory.getLogger(BidController.class.getSimpleName());

    @PostMapping(path = "item/{itemId}/user/{userId}")
    public ResponseEntity<BidTransactionDTO> bidItem(@PathVariable("itemId") Integer itemId,
                                                     @PathVariable("userId") String userId,
                                                     @RequestBody Map<String, BigDecimal> body) throws ItemException {
        return ResponseEntity.ok(bidService.bidItem(itemId,userId,body.get("currentBidPrice")));
    }

    @GetMapping(path = "pricequote")
    public ResponseEntity<BigDecimal> priceQuote(@RequestParam("itemId") Integer itemId) throws ItemException {
        return ResponseEntity.ok(bidService.priceQuote(itemId));
    }

    @PostMapping(path = "hammerbid")
    public ResponseEntity<BidTransactionDTO> hammerBid(@RequestParam("itemId") Integer itemId,
                                                       @RequestParam("userId") String userId,
                                                       @RequestBody Map<String,BigDecimal> body) throws ItemException {
        return ResponseEntity.ok(bidService.hammerBid(itemId,userId,body.get("hammerPrice")));
    }

    @PostMapping(path = "closeauction")
    public ResponseEntity<BidTransactionDTO> closeAuction(@RequestBody Map<String,Integer> body) throws ItemException {
        return ResponseEntity.ok(bidService.closeAuction(body.get("itemId")));
    }

    @PostMapping(path = "cancelbid")
    public ResponseEntity<BidTransactionDTO> cancelBid(@RequestParam("bidId") String bidId) throws ItemException {
        return ResponseEntity.ok(bidService.cancelBid(bidId));
    }


}
