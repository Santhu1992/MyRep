package com.snatch.dto;



import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemDTO {

    private Integer itemId;
    @NotNull
    @Pattern(regexp = "[a-zA-Z ]{3,50}")
    private String itemName;
    @NotNull
    private String itemUrl;
    @NotNull
    private BigDecimal minimumBid;
    @NotNull
    private BigDecimal reservePrice;
    private BigDecimal buyOutPrice;
    @NotNull
    private String addedBy;
    private String wonBy;
    @NotNull
    private Boolean isActive;
    @NotNull
    private Boolean isOpen;
    @NotNull
    @FutureOrPresent
    private LocalDateTime startTime;
    @NotNull
    @Future
    private LocalDateTime expirationTime;
    @NotNull
    private BigDecimal minimumIncrement;

    public ItemDTO() {
    }

    public ItemDTO(Integer itemId, String itemName, String itemUrl, BigDecimal minimumBid, BigDecimal reservePrice, BigDecimal buyOutPrice, String addedBy, String wonBy, Boolean isActive, Boolean isOpen, LocalDateTime startTime, LocalDateTime expirationTime, BigDecimal minimumIncrement) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemUrl = itemUrl;
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
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", minimumBid=" + minimumBid +
                ", reservePrice=" + reservePrice +
                ", buyOutPrice=" + buyOutPrice +
                ", addedBy='" + addedBy + '\'' +
                ", wonBy='" + wonBy + '\'' +
                ", isActive=" + isActive +
                ", isOpen=" + isOpen +
                ", startTime=" + startTime +
                ", expirationTime=" + expirationTime +
                ", minimumIncrement=" + minimumIncrement +
                '}';
    }
}
