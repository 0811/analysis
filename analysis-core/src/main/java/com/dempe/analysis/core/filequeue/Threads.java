package com.dempe.analysis.core.filequeue;

import org.apache.log4j.Logger;

import java.util.concurrent.*;


public class Threads {
    protected static final Logger LOG = Logger.getLogger(Threads.class.getName());
    private static ScheduledExecutorService scheduler;
    private static ThreadPoolExecutor executor;

    protected static synchronized ThreadPoolExecutor getThreadPoolExecutor() {
        if (executor == null) {
            executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), makeThreadFactory("executor"));
        }
        return executor;
    }

    protected static synchronized ScheduledExecutorService getScheduledExecutorService() {
        if (scheduler == null) {
            scheduler = Executors.newSingleThreadScheduledExecutor(makeThreadFactory("scheduler"));
        }
        return scheduler;
    }

    public static Thread runDaemon(Thread t, String name) {
        return runDaemon(t, name, null);
    }

    public static Thread runDaemon(Thread t, String name, Thread.UncaughtExceptionHandler handler) {
        t.setName(name);
        if (handler != null) {
            t.setUncaughtExceptionHandler(handler);
        }
        t.setDaemon(true);
        t.start();
        return t;
    }

    public static Thread runDaemon(Runnable runnable, String name) {
        return runDaemon(new Thread(runnable), name);
    }

    public static Thread runDaemon(Runnable runnable, String name, Thread.UncaughtExceptionHandler handler) {
        return runDaemon(new Thread(runnable), name, handler);
    }

    public static void shutdown(Thread t) {
        shutdown(t, 0L);
    }

    public static void shutdown(Thread t, long joinwait) {
        while (t.isAlive()) {
            try {
                t.interrupt();
                t.join(joinwait);
            } catch (InterruptedException e) {
                LOG.warn(t.getName() + "; joinwait=" + joinwait, e);
            }
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Thread startup(Runnable runnable) {
        Thread t = new Thread(runnable);
        t.start();
        return t;
    }

    public static Thread startup(Runnable runnable, String name) {
        Thread t = new Thread(runnable, name);
        t.start();
        return t;
    }

    public static java.util.concurrent.ThreadFactory makeThreadFactory(String name) {
        return new ThreadFactory(name);
    }

    public static ThreadGroup getTopThreadGroup() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        return topGroup;
    }

    public static int getActiveCount() {
        ThreadGroup group = getTopThreadGroup();
        return group.activeCount();
    }

    public static Thread[] getAllThreads() {
        ThreadGroup topGroup = getTopThreadGroup();
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        int actualSize = topGroup.enumerate(slackList);
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        return list;
    }

    public static ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return getScheduledExecutorService().schedule(command, delay, unit);
    }

    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        return getScheduledExecutorService().scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    public static ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return getScheduledExecutorService().scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    public static <T> Future<T> submit(Runnable task, T result) {
        return getThreadPoolExecutor().submit(task, result);
    }

    public static Future<?> submit(Runnable task) {
        return getThreadPoolExecutor().submit(task);
    }

}
