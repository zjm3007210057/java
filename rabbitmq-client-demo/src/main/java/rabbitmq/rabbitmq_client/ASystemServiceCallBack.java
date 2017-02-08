package rabbitmq.rabbitmq_client;

import com.message.handler.ServiceCallBack;

/**
 * Created by zhangjianming on 2016/8/4.
 */
public class ASystemServiceCallBack extends ServiceCallBack{

    public void doAction(Object object) {
        System.out.println(String.valueOf(object));
    }
}
