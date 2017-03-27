package com.design.future;

/**
 * Created by zhjm on 2017/3/1.
 */
public class FutureTicket {

    private Object data = null;
    private ProcessData pd;
    private boolean isCompleted = false;

    public FutureTicket(ProcessData pd){
        this.pd = pd;
    }

    public synchronized void makeRealData(ProcessData pd){
        if(this.isCompleted){
            return;
        }
        try{
            //模拟耗时操作
            Thread.sleep(10000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        this.data = "返回的数据";
        this.isCompleted = true;
        notifyAll();
    }

    public synchronized void putData(){
        while(!this.isCompleted){
            try{
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
//        return this.data;
        this.pd.processData(this.data);
    }
}
