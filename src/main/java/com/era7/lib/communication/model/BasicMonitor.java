package com.era7.lib.communication.model;

public class BasicMonitor extends Object{

    private static BasicMonitor singleton = null;
    private static boolean lockTaken = false;

    private BasicMonitor() {
        
    }

    public static BasicMonitor getMonitor(){
        if(singleton == null){
            singleton = new BasicMonitor();
        }
        return singleton;
    }

    public synchronized void getLock() throws InterruptedException {
        System.out.println("Estoy en getLock(), lockTaken vale: " + lockTaken);
        if(lockTaken){
            System.out.println("Voy a hacer el wait()");
            this.wait();
            System.out.println("Ya he hecho el wait()!");
        }else{
            lockTaken = true;
            System.out.println("lockTaken puesto a true");
        }
    }

    public synchronized void releaseLock() {
        System.out.println("Estoy en releaseLock");
        lockTaken = false;
        System.out.println("lockTaken puesto a false");
        this.notify();
        System.out.println("Acabo de hacer un notify() !!");
    }
}
