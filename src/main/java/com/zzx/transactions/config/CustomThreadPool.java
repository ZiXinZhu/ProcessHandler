package com.zzx.transactions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class CustomThreadPool {

    @Bean
    public ThreadPoolExecutor executer() {
        //线程池中核心线程数的最大值
        int corePoolSize = 3;
        //线程池中能拥有最多线程数
        int maximumPoolSize = 5;
        //空闲线程的存活时间
        long keepAliveTime = 10;
        //keepAliveTime的单位
        TimeUnit unit = TimeUnit.SECONDS;
        //用于缓存任务的阻塞队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        //指定创建线程的工厂
        ThreadFactory threadFactory = new NameTreadFactory();
        //workQueue已满，且池中的线程数达到maximumPoolSize时，线程池拒绝添加新任务时采取的策略。
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
//        for (int i = 1; i <= 10; i++) {
//            MyTask task = new MyTask(String.valueOf(i));
//            executor.execute(task);
//        }
//
//        System.in.read(); //阻塞主线程
        return executor;
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }


    public static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
