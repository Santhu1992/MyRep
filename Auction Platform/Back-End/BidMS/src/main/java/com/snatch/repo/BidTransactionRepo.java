package com.snatch.repo;

import com.snatch.entity.BidTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface BidTransactionRepo extends JpaRepository<BidTransaction,String> {

    Optional<BidTransaction> findFirstByItemIdAndIsValidTrueOrderByBidPriceDesc(Integer itemId);

}
