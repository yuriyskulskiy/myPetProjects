package DAO.BaseDaoTT;

import domain.products.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Þðà on 03.07.2016.
 */
//CRUD operations
public interface ProductDao {
    Product create();

    Product read(String productName);

    void update(Product product);

    void delete(Product product);

    List<Product> selectAll()throws SQLException;


}
