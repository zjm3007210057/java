package com.jdbc;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;
import com.jdbc.algorithm.ModelTableShardingAlgorithm;
import com.jdbc.algorithm.ModuleDatebaseShardingAlgorithm;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjm on 2017/2/13.
 */
public class Main {

    public static void main(String[] args) throws SQLException{
        DataSource dataSource = getShardingDataSource();
        insert(dataSource, 10);
        System.out.println("------------------");
        printSimpleSelect(dataSource);
        System.out.println("-----------------");
        printGroupBy(dataSource);
        System.out.println("-------------------");
        printHintSimpleSelect(dataSource);
    }

    private static void insert(DataSource dataSource, int num)throws SQLException{
        String sql1 = "insert into t_order(order_id, user_id) values(?, ?)";
        String sql2 = "insert into t_order_item(item_id, order_id, user_id) values(?, ?, ?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2)){
            for(int i = 100; i<100+num; i++){
                preparedStatement1.setInt(1, i);
                preparedStatement1.setInt(2, i + num);
                preparedStatement2.setInt(1, i);
                preparedStatement2.setInt(2, i);
                preparedStatement2.setInt(3, i + num);
                preparedStatement1.executeUpdate();
                preparedStatement2.executeUpdate();
            }
        }
    }

    private static void printSimpleSelect(final DataSource dataSource)throws SQLException{
        String sql = "select i.* from t_order o join t_order_item i on o.order_id=i.order_id where " +
                "o.user_id=? and o.order_id=?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 1001);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                    System.out.println(rs.getInt(3));
                }
            }
        }
    }

    private static void printGroupBy(final DataSource dataSource)throws SQLException{
        String sql = "select o.user_id, count(*) from t_order o join t_order_item i on o.order_id=i.order_id " +
                "group by o.user_id";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                System.out.println("user_id : " + rs.getInt(1) + ", count :" + rs.getInt(2));
            }
        }
    }

    private static void printHintSimpleSelect(final DataSource dataSource)throws SQLException{
        String sql = "select i.* from t_order o join t_order_item i on o.order_id=i.order_id";
        try(HintManager hintManager = HintManager.getInstance();
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            hintManager.addDatabaseShardingValue("t_order", "user_id", 10);
            hintManager.addTableShardingValue("t_order", "order_id", 1001);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                    System.out.println(rs.getInt(3));
                }
            }
        }
    }

    private static ShardingDataSource getShardingDataSource(){
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
        TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule).build();
        TableRule orderItemTableRule = TableRule.builder("t_order_item").actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule).build();
        ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(Arrays.asList(orderTableRule, orderItemTableRule))
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuleDatebaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModelTableShardingAlgorithm())).build();
        return new ShardingDataSource(shardingRule);
    }

    private static Map<String, DataSource> createDataSourceMap(){
        Map<String, DataSource> rs = new HashMap<>(2);
        rs.put("db0", createDataSource("db0"));
        rs.put("db1", createDataSource("db1"));
        return rs;
    }

    private static DataSource createDataSource(final String dataSourceName){
        BasicDataSource rs = new BasicDataSource();
        rs.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        rs.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        rs.setUsername("root");
        rs.setPassword("123456");
        return rs;
    }
}
