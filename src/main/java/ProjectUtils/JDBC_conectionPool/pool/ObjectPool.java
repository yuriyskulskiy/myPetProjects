package ProjectUtils.JDBC_conectionPool.pool;

import ProjectUtils.JDBC_conectionPool.pool.CreationIF;

import java.lang.reflect.Array;

/**
 * Created by ёра on 21.06.2016.
 */
public class ObjectPool<T> {
    //counts how many free connections are in the pool ready to be borrowed
    private int size;

    private T pool[];
    private Object lockObject = new Object();

    private CreationIF creator;
    private Class poolClass;
    //constrait on creating new connections
    private int maxInstances;
   //capacity - size of pool(array)
    public ObjectPool(Class poolClass, CreationIF creator, int capacity, int maxInstances) {
        this.size = 0;
//        this.poolClass=poolClass;
        this.creator = creator;
        this.maxInstances = maxInstances;
        //  pool= new T[capacity]; омг, так нельз€
//        pool = (Object[]) Array.newInstance(poolClass, capacity);

        pool = (T[]) Array.newInstance(poolClass, capacity);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return pool.length;
    }

    public void setCapacity(int newCapacity) {
        if (newCapacity <= 0) {
            String msg = "capacity must be greatter then zero: " + newCapacity;
            throw new IllegalArgumentException(msg);
        }
        synchronized (lockObject) {
            T[] newPool = (T[])new Object[newCapacity];
            System.arraycopy(pool, 0, newPool, 0, newCapacity);
            pool = newPool;

        }
    }

    public T getObject() throws Exception {
        synchronized (lockObject) {
            if (size > 0) {
                return removeObject();
            } else if (getInstancesCount() < maxInstances) {
                return createObject();
            } else {
                return null;
            }
        }
    }

    public T waitAndGetObject() throws Exception {
        System.out.println("instance count="+instancesCount);
        synchronized (lockObject) {
            System.out.println("in synch block instance count="+instancesCount);
            if (size > 0) {
                return removeObject();
            } else if (getInstancesCount() < maxInstances) {
                return createObject();
            } else {
                do {
                    System.out.println("----------------waiting for a free connection");
//                    wait();
// thats how it was in mark grand book - causes java.lang.IllegalMonitorStateException
//                    at java.lang.Object.wait(Native Method)
                    lockObject.wait();
                } while (size <= 0);
                return removeObject();
            }
        }

    }

    private T removeObject() {
        size--;
        return pool[size];
    }

    public void release(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        synchronized (lockObject) {
            if (getSize() < getCapacity()) {
                pool[size] = obj;
                size++;
                lockObject.notify();
            }
        }
    }

    private T createObject() throws Exception {
        T newObject = (T)creator.create(); //why downcast?
        instancesCount++;
        System.out.println("-->> connection factory: a new instance has been created. Number "+instancesCount);
        return newObject;
    }

    private int instancesCount;

    public int getInstancesCount() {
        return instancesCount;
    }


}