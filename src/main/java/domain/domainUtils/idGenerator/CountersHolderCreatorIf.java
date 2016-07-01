package domain.domainUtils.idGenerator;

        import domain.domainUtils.DomainEntityMarker;

        import java.sql.Connection;
        import java.util.HashMap;
        import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Þðà on 29.06.2016.
 */
public interface CountersHolderCreatorIf<T,DomainEntityMarker> {
    public HashMap<Class<DomainEntityMarker>,AtomicInteger> createHolder(T t);

}
