package ProjectUtils.transactionManager;
//import java.util.concurrent.Callable;

import ProjectUtils.UnitOfWork;

import java.util.concurrent.Callable;

/**
 * Created by Þðà on 15.06.2016.
 */
public interface TransactionManager {

//    public <T> T executeInOneTransaction(Callable<T> unitOfWork)throws Exception;
    public <T ,E extends Exception> T executeInOneTransaction(UnitOfWork<T,E> unitOfWork)throws E;

}
