package ProjectUtils.JDBC_conectionPool;

import ProjectUtils.JDBC_conectionPool.pool.CreationIF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Þðà on 22.06.2016.
 */
public class ConnectionCreator implements CreationIF<Connection> {

    private final String JDBC_PASSWORD;
    private final String JDBC_USER_NAME;
    private final String JDBC_URL;

    public ConnectionCreator(String jdbcURL, String DB_Username, String DB_Password) {
        this.JDBC_URL = jdbcURL;
        this.JDBC_PASSWORD = DB_Password;
        this.JDBC_USER_NAME = DB_Username;
    }


    @Override
    public Connection create() throws SQLException {
        Connection newConnection = DriverManager.getConnection( JDBC_URL, JDBC_USER_NAME,  JDBC_PASSWORD);
        return newConnection;
    }
}
