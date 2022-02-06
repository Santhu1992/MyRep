package com.snatch.util;

import com.snatch.dto.BidTransactionDTO;
import com.snatch.entity.BidTransaction;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface BidTransactionMapper {

    BidTransaction prepareEntity(BidTransactionDTO bidTransactionDTO);
    BidTransactionDTO prepareDTO(BidTransaction bidTransaction);

}
