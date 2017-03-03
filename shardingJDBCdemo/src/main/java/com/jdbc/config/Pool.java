package com.jdbc.config;

import java.sql.*;
import java.util.Vector;

/**
 * 数据库连接池类
 * Created by zjm on 2017/2/8.
 */
public class Pool {

    private String jdbcDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String testTable = "";
    private int initialConnectionNum = 10;
    private int maxConnectionNum = 50;
    private int incrementNum = 5;
    private Vector<PooledConnection> connections = null;

    public Pool(String jdbcDriver, String dbUrl, String dbUsername, String dbPassword) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    /**
     * 创建连接池
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public synchronized void createPool()throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException{
        //确保连接池为创建模式，如果已经创建，则直接返回
        if(this.connections != null){
            return;
        }
        //驱动器实例化
        Driver driver = (Driver) Class.forName(this.jdbcDriver).newInstance();
        DriverManager.registerDriver(driver);//注册驱动器
        //创建保存连接的向量
        this.connections = new Vector<PooledConnection>();
        //创建数据库连接
        this.createConnections(this.initialConnectionNum);
    }

    /**
     * 创建数据库连接
     * @param num
     * @throws SQLException
     */
    public void createConnections(int num)throws SQLException{
        //循环创建连接，需要先检查当前连接数是否已经超过连接池的最大连接数
        for(int i=0; i<num; i++){
            if(this.connections.size() >= this.maxConnectionNum){
                return;
            }
            //创建连接
            this.connections.addElement(new PooledConnection(newConnection()));
        }
    }

    /**
     * 建立一个数据库连接
     * @return
     * @throws SQLException
     */
    public Connection newConnection()throws SQLException{
        Connection con = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
        //如果是第一次连接，则检查所有连接数据库的允许最大连接数是否小于我们所设定的最大连接数
        if(this.connections.size() == 0){
            DatabaseMetaData metaData = con.getMetaData();
            //得到数据库的最大连接数
            int dbMaxConnectionNum = metaData.getMaxConnections();
            //如果最大连接数更小，则更改我们所设定的连接池最大连接数
            if(dbMaxConnectionNum > 0 && this.maxConnectionNum > dbMaxConnectionNum){
                this.maxConnectionNum = dbMaxConnectionNum;
            }
        }
        return con;
    }

    /**
     * 得到一个可用连接
     * @return
     */
    public synchronized Connection getConnection(){
        Connection con = null;
        if(this.connections == null){
            return con;
        }
        //得到一个可用连接
        try{
            con = this.getFreeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        //如果没有找到合适的连接，循环等待、查找、直到找到合适的连接
        while(con == null){
            try {
                this.wait(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                con = this.getFreeConnection();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * 获得一个可用连接
     * @return
     * @throws SQLException
     */
    public Connection getFreeConnection()throws SQLException{
        Connection con = this.findFreeConnection();
        if(con == null){
            this.createConnections(this.incrementNum);
            con = this.findFreeConnection();//再次查找
        }
        return con;
    }

    /**
     * 从现有的连接中找一个可用的连接
     * 在现有的连接中找一个空闲的连接
     * 测试这个连接是否可用，若不可以用重新建立连接替换原来的连接
     * @return
     * @throws SQLException
     */
    private Connection findFreeConnection() throws SQLException{
        Connection con = null;
        for(int i=0; i<this.connections.size(); i++){
            PooledConnection pol = this.connections.get(i);
            if(!pol.isBusy()){
                //如果此连接没有被使用，则返回这个连接，设置正在使用标志
                con = pol.getCon();
                pol.setBusy(true);
                //测试链接是否可用
                if(!this.testCon(con)){
                    con = this.newConnection();
                    pol.setCon(con);
                }
                break;
            }
        }
        return con;
    }

    /**
     * 测试连接是否可用
     * @param con
     * @return
     */
    private boolean testCon(Connection con){
        boolean useable = true;
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from" + this.testTable);
            rs.next();
        }catch(SQLException e){
            useable = false;
            e.printStackTrace();
        }
        return useable;
    }

    /**
     * 使用完毕后将连接放回连接池中，并设置为空闲状态
     * @param con
     */
    public void returnConnection(Connection con){
        if(this.connections == null){
            return;
        }
        for(int i=0; i<this.connections.size(); i++){
            PooledConnection pool = this.connections.get(i);
            if(con == pool.getCon()){
                pool.setBusy(false);
            }
        }
    }

    /**
     * 刷新连接池中的连接
     * @throws SQLException
     * @throws InterruptedException
     */
    public synchronized void freshConnection()throws SQLException, InterruptedException{
        if(this.connections == null){
            return;
        }
        for(int i=0; i<this.connections.size(); i++){
            PooledConnection pool = this.connections.get(i);
            if(pool.isBusy()){
                this.wait(5000);
            }
            this.closeConnection(pool.getCon());
            pool.setCon(this.newConnection());
            pool.setBusy(false);
        }
    }

    /**
     * 关闭连接池
     * @param con
     * @throws InterruptedException
     */
    public void closeConnection(Connection con)throws InterruptedException{
        if(this.connections == null){
            return;
        }
        for(int i=0; i<this.connections.size(); i++){
            PooledConnection pool = this.connections.get(i);
            if(pool.isBusy()){
                this.wait(5000);
            }
            this.closeConnection(pool.getCon());
            this.connections.remove(i);
        }
        this.connections = null;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getTestTable() {
        return testTable;
    }

    public void setTestTable(String testTable) {
        this.testTable = testTable;
    }

    public int getInitialConnectionNum() {
        return initialConnectionNum;
    }

    public void setInitialConnectionNum(int initialConnectionNum) {
        this.initialConnectionNum = initialConnectionNum;
    }

    public int getMaxConnectionNum() {
        return maxConnectionNum;
    }

    public void setMaxConnectionNum(int maxConnectionNum) {
        this.maxConnectionNum = maxConnectionNum;
    }

    public int getIncrementNum() {
        return incrementNum;
    }

    public void setIncrementNum(int incrementNum) {
        this.incrementNum = incrementNum;
    }

    public Vector<PooledConnection> getConnections() {
        return connections;
    }

    public void setConnections(Vector<PooledConnection> connections) {
        this.connections = connections;
    }

    /**
     * 内部使用饿保存数据库连接的类
     * 两个成员变量：连接、是否正在使用
     */
    class PooledConnection{
        private Connection con = null;
        private boolean busy = false;

        public PooledConnection(Connection con) {
            this.con = con;
        }

        public Connection getCon() {
            return con;
        }

        public void setCon(Connection con) {
            this.con = con;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }
}
