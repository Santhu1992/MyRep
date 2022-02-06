package com.snatch.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid_transaction")
//@NamedQuery(name = "BidTransaction.priceQuote", query = "select c.bidPrice from BidTransaction where itemId= ?1 order by bidPrice ")
public class BidTransaction {
    @Id
    @GenericGenerator(name = "bidIdGenerator", strategy = "com.snatch.util.BidIdGenerator")
    @GeneratedValue(generator = "bidIdGenerator")
    private String bidId;
    private Integer itemId;
    private String bidderId;
    private String sellerId;
    @Column(nullable = false)
    private BigDecimal bidPrice;
    private LocalDateTime transactionTime;
    @Column(columnDefinition = "BOOLEAN")
    private Boolean isValid;
    private LocalDateTime cancellationTIme;

    public BidTransaction() {
    }

    public BidTransaction(String bidId, Integer itemId, String bidderId, String sellerId , BigDecimal bidPrice, LocalDateTime transactionTime, Boolean isValid, LocalDateTime cancellationTIme) {
        this.bidId = bidId;
        this.itemId = itemId;
        this.bidderId = bidderId;
        this.sellerId = sellerId;
        this.bidPrice = bidPrice;
        this.transactionTime = transactionTime;
        this.isValid = isValid;
        this.cancellationTIme = cancellationTIme;
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
}
