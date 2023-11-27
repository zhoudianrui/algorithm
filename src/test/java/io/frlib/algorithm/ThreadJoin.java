package io.frlib.algorithm;

public class ThreadJoin {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
        for(int i = 0; i < 10000; i++) {
            if (i % 20 == 0) {
                try {
                    System.out.println("开始join");
                    thread.join();
                    /**
                     * synchronized(thread) {
                     *     thread.wait();
                     * }
                     */
                    System.out.println("结束join");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("main 结束");
    }
}

class MyThread extends Thread {
    Object object = new Object();
    @Override
    public void run() {
        synchronized (object) {
            for(int i = 0; i < 10000; i++) {
                System.out.println(this.hashCode() + " running");
            }
        }
    }
}
