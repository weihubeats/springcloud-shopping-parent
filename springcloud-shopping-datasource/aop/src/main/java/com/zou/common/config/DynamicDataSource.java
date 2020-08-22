package com.zou.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author : wh
 * @date : 2020/7/21 11:32
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        final String lookupKey = DbContextHolder.getDbType();
        DbContextHolder.clearDbType();
        return  lookupKey;
    }
}
