package com.fzt.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static Long getThreadLocal(){
        return threadLocal.get();
    }

    public static void setThreadLocal(Long id){
        threadLocal.set(id);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
