package concurrent.chapter03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by zhjm on 2016/7/6.
 */
public class CyclicBarrierDemo {

    public static class Solider implements Runnable{
        private String name;
        private final CyclicBarrier cyclic;
        Solider(CyclicBarrier cyclic, String name){
            this.cyclic = cyclic;
            this.name = name;
        }
        public void run(){
            try{
                cyclic.await();
                dowork();
                cyclic.await();
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
        }
        void dowork(){
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name + "任务完成！");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;
        BarrierRun(boolean flag, int N){
            this.flag = flag;
            this.N = N;
        }
        public void run(){
            if(flag){
                System.out.println("司令，[士兵" + N + "个，任务完成！]");
            }else{
                System.out.println("司令，[士兵" + N + "个，集合完成！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args)throws InterruptedException{
        final int N = 10;
        Thread[] soliders = new Thread[10];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("集合队伍~");
        for(int i=0; i<N; i++){
            System.out.println("士兵" + i + "报到~");
            soliders[i] = new Thread(new Solider(cyclic, "士兵" + i));
            soliders[i].start();
        }
    }
}
