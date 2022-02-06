package com.snatch.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserIdGenerator implements IdentifierGenerator {

    private static int count =101;
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        LocalDateTime now = LocalDateTime.now();
        String value = "US"+now.getDayOfMonth()+now.getMonthValue()+now.getYear()+now.getHour()+now.getMinute();
        int id = count++;
        return value+id;
    }
}
