package com.me.thread.interfaces;

/**
 * Created by zjm on 27/04/2017.
 */
public interface IFutureListener<V> {

    void operationCompleted(IFuture<V> future) throws Exception;

}
