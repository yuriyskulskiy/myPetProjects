package ProjectUtils.JDBC_conectionPool.pool;

/**
 * Created by ��� on 21.06.2016.
 */
public interface CreationIF<T> {
    public T create() throws Exception;

}
