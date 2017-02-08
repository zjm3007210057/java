package rabbitmq.rabbitmq_server;

import java.io.Serializable;

public class RpcRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6391985703068777841L;
	
	/** rpc请求ID */
    private String traceId;

    /** 调用接口 */
    private Class serviceInterface;

    /** 方法名 */
    private String methodName;

    /** 方法参数 */
    private Object[] args;

    /**
     * 默认构造函数, 供FASTJSON序列化使用
     */
    private RpcRequest(){}

    /**
     * 构造函数
     * @param serviceInterface 服务接口
     * @param methodName       方法名
     * @param args             参数组
     */
    public RpcRequest(Class serviceInterface, String methodName, Object[] args) {
        this.serviceInterface = serviceInterface;
        this.methodName = methodName;
        this.args = args;
    }

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Class getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
