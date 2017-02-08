package rabbitmq.rabbitmq_server;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhangjianming on 2016/8/3.
 */
public class RMMessage implements Serializable{

    /**
     * 生成序列号
     */
    private static final long serialVersionUID = 378958136460609410L;

    /**
     * 消息头--消息的说明
     */
    private String head;

    /**
     * 消息体--消息的内容
     */
    private Object body;

    /**
     * 其他参数
     */
    private Map<Object, Object> map;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
}
