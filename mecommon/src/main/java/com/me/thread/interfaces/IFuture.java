package com.me.thread.interfaces;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * The result of an asynchronous operation.
 *
 * @author lixiaohui
 * @param <V> 执行结果的类型参数
 * Created by zjm on 27/04/2017.
 */
public interface IFuture<V> extends Future<V> {

    //是否成功
    boolean isSuccess();

    /**
     * 立即返回结果(不管Future是否处于完成状态)
     * @return
     */
    V getNow();

    /**
     * 执行失败时的原因
     * @return
     */
    Throwable cause();

    /**
     * 是否可以取消
     * @return
     */
    boolean isCancellable();

    /**
     * 等待Future的完成
     * @return
     * @throws InterruptedException
     */
    IFuture<V> await() throws InterruptedException;

    /**
     * 超时等待Future的完成
     * @param timeout
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    boolean await(long timeout, TimeUnit timeUnit)throws InterruptedException;

    /**
     * 超时等待Future的完成
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    boolean await(long timeout)throws InterruptedException;

    /**
     * 等待Future的完成，不响应中断
     * @return
     */
    IFuture<V> awaitUninterruptibly();

    /**
     * 等待Future的完成，不响应中断
     * @param timeoutMills
     * @return
     */
    boolean awaitUninterruptibly(long timeoutMills);

    /**
     * 等待Future的完成，不响应中断
     * @param timeout
     * @param timeunit
     * @return
     */
    boolean awaitUninterruptibly(long timeout, TimeUnit timeunit);


    IFuture<V> addListener(IFutureListener<V> l); //当future完成时，会通知这些加进来的监听器


    IFuture<V> removeListener(IFutureListener<V> l);


}
