package service;

import DAO.ProductDaoImpl;
import ProjectUtils.transactionManager.TransactionManager;
import ProjectUtils.transactionManager.TransactionManagerImpl;

/**
 * Created by Þðà on 16.06.2016.
 */

//UDER CONSTRUCTION
public class Service {

    ProductDaoImpl productDAO = new ProductDaoImpl();
    TransactionManager txManager = new TransactionManagerImpl();


//      public void ServiceMethod(final long id) {
//          try {
//              Product product =txManager.doInTransaction(new UnitOfWork<Product, DaoExeption>() {
//                  public Product executeInOneTx() throws DaoExeption {
//                      return productDAO.getProductById(id);
//                  }
//              });
//          } catch ( DaoExeption | NumberFormatException e) {
//              e.printStackTrace();
//          } finally {
//          }
//      }








//    public void ServiceMethod() {
//        String param = request.getParameyer("KEY");
//        if (param != null) {
//            try{
//                Integer id =Integer.valueOf(param);
//                Product meProduct  = txManager.executeInOneTransaction(new Callable<Product>() {
//                    public Product call() throws NoSuchEntityExeption,DaoSystemExeption {
//                        return  productDAO.selectById(id);
//                    }
//                });
//            }catch (NumberFormatException|NoSuchEntityExeption|DaoSystemExeption){
//
//            }catch (Exception e){
//                    e.printStackTrace();
//            }
//        }
//    }

}
