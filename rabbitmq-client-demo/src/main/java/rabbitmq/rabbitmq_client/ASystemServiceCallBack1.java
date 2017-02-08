package rabbitmq.rabbitmq_client;

import com.message.handler.ServiceCallBack;

/**
 * Created by zhangjianming on 2016/8/11.
 */
public class ASystemServiceCallBack1 extends ServiceCallBack{

    public void doAction(Object object) {
        System.out.println("哈哈," + ",原来还可以这样玩！！" + String.valueOf(object));
    }
}
