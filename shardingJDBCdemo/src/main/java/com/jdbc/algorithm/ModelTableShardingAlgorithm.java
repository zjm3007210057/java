package com.jdbc.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by zjm on 2017/2/10.
 */
public class ModelTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        for(String each : tableNames){
            if(each.endsWith(shardingValue.getValue() % 2 + "")){
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
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

    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> res = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for(Integer i=range.lowerEndpoint(); i<range.upperEndpoint(); i++){
            for(String tableName : tableNames){
                if(tableName.endsWith(i % 2 + "")){
                    res.add(tableName);
                }
            }
        }
        return res;
    }
}
