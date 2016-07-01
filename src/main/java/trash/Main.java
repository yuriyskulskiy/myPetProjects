package trash;

import ProjectUtils.JDBC_conectionPool.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Þðà on 27.06.2016.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("started");
        String url = "jdbc:mysql://skul.dpm.dn.ua:3306/skuldb";
        String username = "skul";
//        String username = "root";
        String pass = "qWeR9876";
//        String pass = "sys";
        Connection con1 = null;

        Connection con3 = null;
        Connection con4 = null;
        Connection con5 = null;
        Connection con6 = null;

//            Class.forName("mysql-connector-java-5.1.39-bin");
//            con = DriverManager.getConnection(url, username, pass);

        ConnectionManager.init_connection_pool(url, username, pass, 3, 3);

//        con1 = ConnectionManager.getConnection();
//        System.out.println("con1 -" + con1.isClosed());


        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con1 = null;
                con1 = ConnectionManager.getConnection();
                try {
                    System.out.println("con1 -" + con1.isClosed());
                    try {
                        System.out.println("=====con1 is going to sleep");

                        Thread.sleep(10000);
                        System.out.println("con1 has been awaken ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    con1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con2 = null;
                con2 = ConnectionManager.getConnection();
                try {
                    System.out.println("con2 -" + con2.isClosed());
                    try {
                        System.out.println("=====con2 is going to sleep");

                        Thread.sleep(10000);
                        System.out.println("con2 has waken up");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    con2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con3 = null;
                con3 = ConnectionManager.getConnection();
                try {
                    System.out.println("con3 -"+con3.isClosed());
                    try {
                        System.out.println("=====con3 is going to sleep");

                        Thread.sleep(10000);
                        System.out.println("con3 has waken up");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    con3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con4 = null;
                con4 = ConnectionManager.getConnection();
                System.out.println("-----4th connection has been taken already");
                try {
                    System.out.println("=====con4 is going to sleep");
                    Thread.sleep(10000);
                    System.out.println("con4 has waken up");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("con4 -" + con4.isClosed());
                    con4.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }).start();


//        con2 = ConnectionManager.getConnection();
//        System.out.println("con2 -"+con2.isClosed());

//        con3 = ConnectionManager.getConnection();
//        System.out.println("con3 -"+con3.isClosed());
//
//        con4 = ConnectionManager.getConnection();
//        System.out.println("con4 -"+con4.isClosed()   );
//
//        con5 = ConnectionManager.getConnection();
//        System.out.println("con5 -"+con5.isClosed());
//        con1.close();
//
//        con6 = ConnectionManager.getConnection();
//        System.out.println(con6.isClosed());

        System.out.println("conection(s) have(s) been created");


//        try {
//            con1.close();
//            con1.close();

//            con3.close();
//            con4.close();
//            con5.close();
//            System.out.println(" con.close() has been called");
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        }
        System.out.println("Connection has been closed");


    }
}
