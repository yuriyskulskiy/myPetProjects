package ProjectUtils.transactionManager;



import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by Þðà on 16.06.2016.
 */
public  class baseDatasource implements DataSource {
    public baseDatasource() {
    }
//    public baseDatasource() {
//           }

//    private final ConnectionPool connectionPool=null;
//
//    public baseDatasource(ConnectionPool connectionPool) {
//        this.connectionPool = connectionPool;
//    }

    public Connection getConnection() throws SQLException {

        return null;

    }

    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
//        return null;
    }

    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
//        return null;
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setLoginTimeout(int seconds) throws SQLException {
    throw new UnsupportedOperationException();
    }

    public int getLoginTimeout() throws SQLException {
//        throw new UnsupportedOperationException();
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
//        return null;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
//        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
//        return false;
    }
}
