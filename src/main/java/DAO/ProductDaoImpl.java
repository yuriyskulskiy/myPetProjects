package DAO;

import domain.Product;

import javax.sql.DataSource;

/**
 * Created by Þðà on 16.06.2016.
 */
public class ProductDaoImpl {
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    public Product getProductById(long id){
        return new Product();
    };
}
