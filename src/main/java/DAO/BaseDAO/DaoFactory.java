package DAO.BaseDAO;

import domain.users.User;

import java.sql.Connection;

/**
 * Created by Þðà on 03.07.2016.
 */
public interface DaoFactory {
    Connection getConnection();
   //todo mabe change arg to  datasourse...
   UserDao getUserDao(Connection  con);
   ProductDao getProductDao(Connection  con);

}
