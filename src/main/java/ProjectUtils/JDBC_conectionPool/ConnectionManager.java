package ProjectUtils.JDBC_conectionPool;

import ProjectUtils.JDBC_conectionPool.pool.ObjectPool;
import ProjectUtils.JDBC_conectionPool.pool.exeptions.First_call_createConnectionPoolInstanceMethod_Exeption;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Þðà on 22.06.2016.
 */

public class ConnectionManager {

    private ConnectionManager(){

    }
    public static boolean init_connection_pool(String URL,String username,String pass,int poolCapacity,int poolInstances){
       return ConnectionPool.createConnectionPool_Instance(new ConnectionCreator(URL,username,pass),poolCapacity,poolInstances);
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Connection realConnection = ConnectionPool.getConnectionPool_instance().getConnection();
            Connection wrappedConnection = getConnectionWrapper(realConnection);
            connection = wrappedConnection;
        } catch (First_call_createConnectionPoolInstanceMethod_Exeption first_call_createConnectionPoolInstanceMethod_exeption) {
            first_call_createConnectionPoolInstanceMethod_exeption.printStackTrace();
        }
        return connection;
    }

    public static ConnectionWrapper getConnectionWrapper(Connection realConnection) {
        return new ConnectionManager().new ConnectionWrapper(realConnection);
    }
     //inner class
    // wrapper-class for real java.sql.Connection overriding some methods and delegating rest of others;
    class ConnectionWrapper implements Connection {
        //TODO  unfortunally I can get and kill real Connection from Statement - so gotto wrap statement too(

        Connection refToRealConnection;

        public ConnectionWrapper(Connection refToRealConnection) {
            this.refToRealConnection = refToRealConnection;
        }

        //this method doesnt close connection, only kills reff to it and puts real connection back to the pool;
        @Override
        public void close() throws SQLException {
            try {
                ConnectionPool.getConnectionPool_instance().putBackConnection(refToRealConnection);
            } catch (First_call_createConnectionPoolInstanceMethod_Exeption first_call_createConnectionPoolInstanceMethod_exeption) {
                first_call_createConnectionPoolInstanceMethod_exeption.printStackTrace();
            }
            refToRealConnection = null;
        }

        //returns boolean that tells us whether ref-refToRealConnection is not  initialized or it is
        @Override
        public boolean isClosed() throws SQLException {
//        return refToRealConnection.isClosed();
            return (refToRealConnection == null);
        }

        @Override
        public Statement createStatement() throws SQLException {
            return refToRealConnection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return refToRealConnection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return refToRealConnection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return refToRealConnection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            refToRealConnection.setAutoCommit(autoCommit);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return refToRealConnection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            refToRealConnection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            refToRealConnection.rollback();
        }





        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return refToRealConnection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            refToRealConnection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return refToRealConnection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            refToRealConnection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return refToRealConnection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            refToRealConnection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return refToRealConnection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return refToRealConnection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            refToRealConnection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return refToRealConnection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return refToRealConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return refToRealConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return refToRealConnection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            refToRealConnection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            refToRealConnection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return refToRealConnection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return refToRealConnection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return refToRealConnection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            refToRealConnection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            refToRealConnection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return refToRealConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return refToRealConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return refToRealConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return refToRealConnection.prepareStatement(sql, autoGeneratedKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return refToRealConnection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return refToRealConnection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return refToRealConnection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return refToRealConnection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return refToRealConnection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return refToRealConnection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return refToRealConnection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            refToRealConnection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            refToRealConnection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return refToRealConnection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return refToRealConnection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return refToRealConnection.createArrayOf(typeName,elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return refToRealConnection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            refToRealConnection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return refToRealConnection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            refToRealConnection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            refToRealConnection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return refToRealConnection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return refToRealConnection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return refToRealConnection.isWrapperFor(iface);
        }


//    private final String JDBC_URL;
//    private final String name;
//    private final String password;


    }
}

class ConnectionPool extends ObjectPool<Connection> implements SingletonMarkerInterface {
    private static ConnectionPool single_instance = null;

    Connection getConnection() {
        Connection connection=null;
        try {
            //todo implement switcher for waitandgetObg() and getObject()
//             connection = super.getObject();
             connection = super.waitAndGetObject();

            System.out.println("------> One connection has been taken from ConnectionPool");

        } catch (Exception e) {
            e.printStackTrace();

        }
        // todo: What should I return in this case?
        return connection;
    }
    void putBackConnection(Connection connection){
        super.release(connection);
        System.out.println("------> One connection has been returned back to ConnectionPool");
    }


    private ConnectionPool(ConnectionCreator creator, int capacity, int maxInstances) {
        super(Connection.class, creator, capacity, maxInstances);
    }

    public static ConnectionPool getConnectionPool_instance() throws First_call_createConnectionPoolInstanceMethod_Exeption {
        if (single_instance == null) {

            throw new First_call_createConnectionPoolInstanceMethod_Exeption("You need to create ConnectionPool instanse  before using it ");
        }
        return single_instance;
    }

    public static synchronized boolean createConnectionPool_Instance(ConnectionCreator creator, int capacity, int maxInstances) {
        if (single_instance == null) {
            single_instance = new ConnectionPool(creator, capacity, maxInstances);
            return true;
        }

        return false;
    }
}


