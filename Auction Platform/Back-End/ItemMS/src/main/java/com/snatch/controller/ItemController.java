package com.snatch.controller;

import com.snatch.dto.ItemDTO;
import com.snatch.exception.ItemException;
import com.snatch.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@Valid @RequestBody ItemDTO itemDTO) throws ItemException {
        return new ResponseEntity<>(itemService.createItem(itemDTO), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{itemId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable("itemId") int itemId, @RequestBody ItemDTO itemDTO) throws ItemException {
        return ResponseEntity.ok(itemService.updateItem(itemId,itemDTO));
    }

    @DeleteMapping(path = "{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemId") int itemId) throws ItemException{
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

    @GetMapping(path = "{itemId}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable("itemId") int itemId) throws ItemException{
        return ResponseEntity.ok(itemService.getItem(itemId));
    }

    @GetMapping(path = "user/{userId}")
    public ResponseEntity<List<ItemDTO>> getItemByUser(@PathVariable("userId") String userId) throws ItemException{
        return ResponseEntity.ok(itemService.getItemsByUser(userId));
    }

    @PostMapping(path= "buyout")
    public ResponseEntity<Boolean> buyOutItem(@RequestBody Map<String,String> map) throws ItemException {
        int itemId = Integer.parseInt(map.get("itemId"));
        String userId = map.get("userId");
        BigDecimal buyOutPrice = new BigDecimal(map.get("buyOutPrice"));
        return ResponseEntity.ok(itemService.buyOutItem(itemId,userId,buyOutPrice));
    }

    @PostMapping(path = "terminateauction")
    public ResponseEntity<Boolean> terminateAuction(@RequestBody Map<String ,Integer> body) throws ItemException {
        return ResponseEntity.ok(itemService.terminateAuction(body.get("itemId")));
    }



}
