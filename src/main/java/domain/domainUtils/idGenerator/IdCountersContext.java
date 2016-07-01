package domain.domainUtils.idGenerator;

import domain.domainUtils.DomainEntityMarker;
import domain.users.GoldenUser;
import domain.users.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Þðà on 29.06.2016.
 */
// Singleton pattern example with Double checked Locking
public class IdCountersContext {
    private static volatile IdCountersContext singleInstance;


    private HashMap<Class<DomainEntityMarker>, AtomicInteger> countersKeeper = null;

    public static IdCountersContext getIdCountersContext(DataSource dataSource) {
        if (singleInstance == null) {
            synchronized (IdCountersContext.class) {
                if (singleInstance == null) {
                    try {
                        Connection con = dataSource.getConnection();
                        singleInstance = new IdCountersContext();

                        singleInstance.countersKeeper = new CountersHolderCreatorImpl().createHolder(dataSource.getConnection());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return singleInstance;
    }

    public int generate(DomainEntityMarker obj, String mappedTableName) {
        AtomicInteger atomicInteger = countersKeeper.get(obj.getClass());
        if (atomicInteger == null) {
            synchronized (countersKeeper) {
//              get max id from table named as mappedTablename
                if (atomicInteger == null) {
                    atomicInteger = new AtomicInteger(23);
                }
                atomicInteger.intValue();
            }
        }
        return atomicInteger.getAndIncrement();

    }

    ;

    public HashMap getCountersKeeper() {
        return countersKeeper;
    }

    public void setCountersKeeper(HashMap countersKeeper) {
        this.countersKeeper = countersKeeper;
    }
}
