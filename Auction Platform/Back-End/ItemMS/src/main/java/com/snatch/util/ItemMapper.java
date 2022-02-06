package com.snatch.util;


import com.snatch.dto.ItemDTO;
import com.snatch.entity.Item;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item prepareItemEntity(ItemDTO itemDTO);
    ItemDTO prepareItemDTO(Item item);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemFromDTO(ItemDTO itemDTO, @MappingTarget Item item);

}
