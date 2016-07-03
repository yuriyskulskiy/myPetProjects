package DAO.BaseDaoTT;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Юра on 03.07.2016.
 */
public interface DaoFactory {
    Connection getConnection() throws SQLException;
   //todo may be change arg to  datasourse...
   UserDao getUserDao(Connection  con);
   ProductDao getProductDao(Connection  con);

}
