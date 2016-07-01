package domain.domainUtils.idGenerator;

import domain.domainUtils.DomainEntityMarker;

import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Þðà on 29.06.2016.
 */
public class CountersHolderCreatorImpl implements CountersHolderCreatorIf<Connection,DomainEntityMarker > {
    @Override
    public HashMap<Class<DomainEntityMarker>,AtomicInteger> createHolder(Connection connection) {
        return new HashMap();
    }


//    @Override
//    public IdCountersContext contextCreate(Object o) {
//        return null;
//    }
}
