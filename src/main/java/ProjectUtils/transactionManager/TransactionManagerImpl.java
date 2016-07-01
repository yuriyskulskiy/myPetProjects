package ProjectUtils.transactionManager;

import ProjectUtils.JDBC_conectionPool.ConnectionCreator;

import ProjectUtils.JDBC_conectionPool.ConnectionManager;
import ProjectUtils.JDBC_conectionPool.pool.exeptions.First_call_createConnectionPoolInstanceMethod_Exeption;
import ProjectUtils.UnitOfWork;

import ProjectUtils.projectProperties.JDBC_connectionProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Þðà on 16.06.2016.
 */
public class TransactionManagerImpl extends baseDatasource implements TransactionManager {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/ ....";
    private static final String USER = "jdbc:mysql://127.0.0.1:3306/ ....";
    private static final String PASSWORD = "jdbc:mysql://127.0.0.1:3306/ ....";

    private boolean useConnectionPoolAndWrapper = true;

    public boolean useConnectionPoolandWrapperConstructions() {
        return useConnectionPoolAndWrapper;
    }

    public void setUseConnectionPoolPattern(boolean useConnectionPoolPattern) {
        this.useConnectionPoolAndWrapper = useConnectionPoolPattern;
    }


    protected static ThreadLocal<Connection> connectionKeeper = new ThreadLocal<Connection>();


    private Connection getNewConnectionFromDriverManager() throws SQLException {
        Properties connectionProperties = JDBC_connectionProperties.getConnectionProperties();
        String URL = connectionProperties.getProperty(JDBC_connectionProperties.DB_PASSWORD_KEY);
        String name = connectionProperties.getProperty(JDBC_connectionProperties.DB_USER_NAME_KEY);
        String pass = connectionProperties.getProperty(JDBC_connectionProperties.DB_PASSWORD_KEY);
        Connection newConnection = DriverManager.getConnection(URL, name, pass);

        return newConnection;
    }


    private Connection getFreeConnectionFromConnectionPoolManager() {

//            freeConnection = ConnectionManager.getConnectionWrapper(ConnectionPool.getConnectionPool_instance().getConnection());
        Connection freeConnection = ConnectionManager.getConnection();
        return freeConnection;
    }


    public <T, E extends Exception> T executeInOneTransaction(UnitOfWork<T, E> unitOfWork) throws E {
        Connection myConnection = null;
        try {
            if (useConnectionPoolAndWrapper) {
                //getting decorated(wrapped)-connection from connectionPool of real connection-instances ;
//
                myConnection = getFreeConnectionFromConnectionPoolManager();

            } else {
                //getting real NEW  java.sql.Connection;
                myConnection = getNewConnectionFromDriverManager();
            }
//
            myConnection.setAutoCommit(false);
            connectionKeeper.set(myConnection);


            T result = unitOfWork.executeInOneTx();
            myConnection.commit();
            return result;

        } catch (Exception e) {
            e.printStackTrace();

            try {
                myConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {


            connectionKeeper.remove();
            if (useConnectionPoolAndWrapper) {

            } else {
                try {
                    myConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

//    public <T> T executeInOneTransactionOldVrsion(Callable<T> unitOfWork) throws Exception {
//        Connection myConnection = DriverManager.getConnection(JDBC_URL);
//        myConnection.setAutoCommit(false);
//        connectionKeeper.set(myConnection);
//
//
//        try {
//
//            T result = unitOfWork.call();
//            myConnection.commit();
//            return result;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            myConnection.rollback();
//            throw e;
//        } finally {
////            JdbcUtils.closeQuetly(myConnection);
//            connectionKeeper.remove();
//        }
//
//    }
//


    @Override
    public Connection getConnection() {
        return connectionKeeper.get();
    }
}

class myThreadLocal<T> {
    private Map<Thread, T> keeper = new ConcurrentHashMap<Thread, T>();

    public T get() {
        return keeper.get(Thread.currentThread());
    }

    public void set(T foo) {
        keeper.put(Thread.currentThread(), foo);
    }
}
