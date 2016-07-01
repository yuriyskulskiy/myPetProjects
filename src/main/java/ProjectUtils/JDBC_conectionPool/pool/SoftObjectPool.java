package ProjectUtils.JDBC_conectionPool.pool;

import ProjectUtils.JDBC_conectionPool.pool.CreationIF;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by Þðà on 21.06.2016.
 */
public class SoftObjectPool {
    private final static int MAX_POOL_OBJECT_COUNT = 100;

        private ArrayList pool;

        private CreationIF creator;

            private int instancesCount;

    private int maxInstances;

    private Class poolClass;

    public SoftObjectPool(Class poolClass, CreationIF creator) {
        this(poolClass, creator, 100);
    }

    public SoftObjectPool(Class poolClass, CreationIF creator, int maxInstances) {
        this.creator = creator;
        this.poolClass = poolClass;
        this.maxInstances = maxInstances;
        pool = new ArrayList();
    }

    public int getSize() {
        synchronized (pool) {
            return pool.size();

        }
    }

    ;

    public int getInstancesCount() {
        return instancesCount;
    }

    public int getMaxInstances() {
        return maxInstances;
    }

    public void setMaxInstances(int maxInstances) {
        this.maxInstances = maxInstances;
    }

    public Object getObject() {
        synchronized (pool) {
            Object thisObject = removeObject();
            if (thisObject != null) {
                return thisObject;
            }
            if (getInstancesCount() < getMaxInstances()) {
                return createObject();
            } else {
                return null;
            }
        }
    }

    public Object waitAndgetObject() throws InterruptedException {
        synchronized (pool) {
            Object thisObject = removeObject();
            if (thisObject != null) {
                return thisObject;
            }
            if (getInstancesCount() < getMaxInstances()) {
                return createObject();
            } else {
                do {
                    pool.wait();
                    thisObject = removeObject();
                } while (thisObject == null);
                    return thisObject;
                }
            }
        }

    private Object removeObject() {
        while(pool.size()>0){
            SoftReference thisRef = (SoftReference)pool.remove(pool.size()-1);
            Object thisObject = thisRef.get();
            if(thisObject!=null){
                return thisObject;
            }
            instancesCount--;

        }
        return null;
    }
//todo comment back
    private Object createObject(){
//        Object newObject = creator.create();
//        instancesCount++;
//        return newObject;
        return null;
    }

    public void release(Object obj){
        if(obj == null){
            throw new NullPointerException();
        }
        if(!poolClass.isInstance(obj)){
            String actualClassname = obj.getClass().getName();
            throw new ArrayStoreException(actualClassname);
        }
        synchronized (pool){
            pool.add(obj);
            pool.notify();
        }
    }
}
