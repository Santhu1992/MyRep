package com.snatch.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidTransactionDTO {

    private String bidId;
    private Integer itemId;
    private String bidderId;
    private String sellerId;
    private BigDecimal bidPrice;
    private LocalDateTime transactionTime;
    private Boolean isValid;
    private LocalDateTime cancellationTIme;

    public BidTransactionDTO(String bidId, Integer itemId, String bidderId, String sellerId, BigDecimal bidPrice, LocalDateTime transactionTime, Boolean isValid, LocalDateTime cancellationTIme) {
        this.bidId = bidId;
        this.itemId = itemId;
        this.bidderId = bidderId;
        this.sellerId = sellerId;
        this.bidPrice = bidPrice;
        this.transactionTime = transactionTime;
        this.isValid = isValid;
        this.cancellationTIme = cancellationTIme;
    }

    public BidTransactionDTO() {
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public LocalDateTime getCancellationTIme() {
        return cancellationTIme;
    }

    public void setCancellationTIme(LocalDateTime cancellationTIme) {
        this.cancellationTIme = cancellationTIme;
    }

    @Override
    public String toString() {
        return "BidTransactionDTO{" +
                "bidId='" + bidId + '\'' +
                ", itemId=" + itemId +
                ", bidderId='" + bidderId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", bidPrice=" + bidPrice +
                ", transactionTime=" + transactionTime +
                ", isValid=" + isValid +
                ", cancellationTIme=" + cancellationTIme +
                '}';
    }
}
