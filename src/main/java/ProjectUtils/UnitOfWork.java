package ProjectUtils;

/**
 * Created by Þðà on 19.06.2016.
 */
public interface UnitOfWork<T,E extends Exception> {
  public T executeInOneTx()throws E;
}
