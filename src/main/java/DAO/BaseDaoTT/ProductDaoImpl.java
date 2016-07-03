package DAO.BaseDaoTT;

import domain.products.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Þðà on 03.07.2016.
 */
//TODO this is just half-made mock-class for factory
public class ProductDaoImpl implements ProductDao {
   Connection connection;
    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Product create() {
        return null;
    }

    @Override
    public Product read(String productName) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public List<Product> selectAll() throws SQLException {
        return null;
    }
}
