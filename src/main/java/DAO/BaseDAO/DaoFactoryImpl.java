package DAO.BaseDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Þðà on 03.07.2016.
 */
public class DaoFactoryImpl implements DaoFactory {
//    public DaoFactoryImpl(Connection connection) {
//    }

    protected DataSource dataSource;

    public DaoFactoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public UserDao getUserDao(Connection con) {
        return new UserDaoImpl(con);
    }

    @Override
    public ProductDao getProductDao(Connection con) {
        return new ProductDaoImpl(con);
    }
}
