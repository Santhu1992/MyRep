package com.snatch.service;

import com.snatch.dto.ItemDTO;
import com.snatch.entity.Item;
import com.snatch.exception.ItemException;

import java.math.BigDecimal;


public interface ItemService {
    ItemDTO createItem(ItemDTO itemDTO) throws ItemException;
    ItemDTO updateItem(int itemId,ItemDTO itemDTO) throws ItemException;
    String deleteItem(int itemId) throws ItemException;
    ItemDTO getItem(int itemId) throws ItemException;
    ItemDTO getItemsByUser(String userId) throws ItemException;
    boolean buyOutItem(int itemId, String userId, BigDecimal buyOutPrice) throws ItemException;
    boolean terminateAuction(Integer itemId) throws ItemException;
}
