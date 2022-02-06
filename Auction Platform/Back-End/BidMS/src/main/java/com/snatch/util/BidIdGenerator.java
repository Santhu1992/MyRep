package com.snatch.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BidIdGenerator implements IdentifierGenerator {

    private static int count = 101;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        int id = count++;
        LocalDateTime now = LocalDateTime.now();
        String value = "BD"+now.getDayOfMonth()+now.getMonthValue()+now.getYear()+now.getHour()+now.getMinute();
        return value+id;
    }
}
