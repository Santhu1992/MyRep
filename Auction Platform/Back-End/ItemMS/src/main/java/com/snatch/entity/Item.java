package com.snatch.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sn_item")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "item_name")
    private String ItemName;
    @Column(name = "item_url")
    private String ItemUrl;
    @Column(name = "minimum_bid")
    private BigDecimal minimumBid;
    @Column(name = "reserve_price")
    private BigDecimal reservePrice = BigDecimal.ZERO;
    @Column(name = "buy_out_price")
    private BigDecimal buyOutPrice;
    @Column(name = "added_by")
    private String addedBy;
    @Column(name = "won_by")
    private String wonBy;
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isActive;
    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isOpen;
    private LocalDateTime startTime;
    private LocalDateTime expirationTime;
    private BigDecimal minimumIncrement;

    public Item() {
    }

    public Item(Integer itemId, String itemName, String itemUrl, BigDecimal minimumBid, BigDecimal reservePrice, BigDecimal buyOutPrice, String addedBy, String wonBy, Boolean isActive, Boolean isOpen, LocalDateTime startTime, LocalDateTime expirationTime, BigDecimal minimumIncrement) {
        this.itemId = itemId;
        ItemName = itemName;
        ItemUrl = itemUrl;
        this.minimumBid = minimumBid;
        this.reservePrice = reservePrice;
        this.buyOutPrice = buyOutPrice;
        this.addedBy = addedBy;
        this.wonBy = wonBy;
        this.isActive = isActive;
        this.isOpen = isOpen;
        this.startTime = startTime;
        this.expirationTime = expirationTime;
        this.minimumIncrement = minimumIncrement;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemUrl() {
        return ItemUrl;
    }

    public void setItemUrl(String itemUrl) {
        ItemUrl = itemUrl;
    }

    public BigDecimal getMinimumBid() {
        return minimumBid;
    }

    public void setMinimumBid(BigDecimal minimumBid) {
        this.minimumBid = minimumBid;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public BigDecimal getBuyOutPrice() {
        return buyOutPrice;
    }

    public void setBuyOutPrice(BigDecimal buyOutPrice) {
        this.buyOutPrice = buyOutPrice;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getWonBy() {
        return wonBy;
    }

    public void setWonBy(String wonBy) {
        this.wonBy = wonBy;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public BigDecimal getMinimumIncrement() {
        return minimumIncrement;
    }

    public void setMinimumIncrement(BigDecimal minimumIncrement) {
        this.minimumIncrement = minimumIncrement;
    }
}
