package com.jdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by zjm on 2017/2/10.
 */
public final class ModuleDatebaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    public String doEqualSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        for (String each: dataSourceNames) {
            if(each.endsWith(shardingValue.getValue() % 2 + "")){
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    public Collection<String> doInSharding(final Collection<String> tableNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> res = new LinkedHashSet<String>(tableNames.size());
        for(Integer value : shardingValue.getValues()){
            for(String tableName : tableNames){
                if(tableName.endsWith(value % 2 + "")){
                    res.add(tableName);
                }
            }
        }
        return res;
    }

    public Collection<String> doBetweenSharding(final Collection<String> tableNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> res = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for(Integer i=range.lowerEndpoint(); i<=range.upperEndpoint(); i++){
            for(String each : tableNames){
                if(each.endsWith(i % 2 + "")){
                    res.add(each);
                }
            }
        }
        return res;
    }
}
