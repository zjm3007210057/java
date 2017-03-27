package com.design.future;

/**
 * Future模式主要包括：
 * 1、Main--系统启动，调用Client发出请求
 * 2、Client--返回Data对象，立即返回FutureData，并开启ClientThread线程装配RealData
 * 3、Data--返回数据的接口
 * 4、FutureData--Future数据，构造很快，但是是一个虚假的数据，需要装配RealData
 * 5、RealData--真实数据，其构造比较慢
 * Created by zhjm on 2017/2/27.
 */
public class FutureMain {

    public static void main(String[] args) {
//        Client client = new Client();
    }
}
