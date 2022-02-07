package com.snatch.service;

import com.snatch.dto.ItemDTO;
import com.snatch.entity.Item;
import com.snatch.exception.ItemConstants;
import com.snatch.exception.ItemException;
import com.snatch.repo.ItemRepo;
import com.snatch.util.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@PropertySource(value = "messages.properties")
public class ItemServiceImpl implements ItemService{

    private ItemRepo itemRepo;
    private ItemMapper itemMapper;
    private Environment env;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, ItemMapper itemMapper, Environment env) {
        this.itemRepo = itemRepo;
        this.itemMapper = itemMapper;
        this.env = env;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) throws ItemException {
        return itemMapper.prepareItemDTO(itemRepo.saveAndFlush(itemMapper.prepareItemEntity(itemDTO)));
    }

    @Override
    public ItemDTO updateItem(int itemId, ItemDTO itemDTO) throws ItemException {
        Item item = findById(itemId);
        itemMapper.updateItemFromDTO(itemDTO,item);
        return itemMapper.prepareItemDTO(saveItem(item));
    }

    private Item saveItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public String deleteItem(int itemId) throws ItemException {
        itemRepo.delete(findById(itemId));
        return env.getProperty(ItemConstants.ITEM_DELETED.getType());
    }

    @Override
    public ItemDTO getItem(int itemId) throws ItemException {
        Item item = findById(itemId);
        return itemMapper.prepareItemDTO(item);
    }

    private Item findById(int itemId) throws ItemException {
        return itemRepo.findById(itemId).orElseThrow(() -> new ItemException(env.getProperty(ItemConstants.ITEM_NOT_FOUND.getType())));
    }

    @Override
    public List<ItemDTO> getItemsByUser(String userId) throws ItemException {
        List<Item> item = findByUser(userId);
        return item.stream().map(item1 -> itemMapper.prepareItemDTO(item1)).collect(Collectors.toList());
    }

    private List<Item> findByUser(String userId) throws ItemException {
        return itemRepo.findByAddedBy(userId).orElseThrow(() -> new ItemException(env.getProperty(ItemConstants.USER_NOT_FOUND.getType())));
    }


    @Override
    public boolean buyOutItem(int itemId, String userId, BigDecimal buyOutPrice) throws ItemException {
        Item item = findById(itemId);
        item.setBuyOutPrice(buyOutPrice);
        item.setWonBy(userId);
        item.setOpen(Boolean.FALSE);
        saveItem(item);
        return true;
    }

    @Override
    public boolean terminateAuction(Integer itemId) throws ItemException {
        Item item = findById(itemId);
        item.setOpen(Boolean.FALSE);
        saveItem(item);
        return true;
    }
}
