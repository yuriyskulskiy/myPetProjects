package ProjectUtils.JDBC_conectionPool.pool.exeptions;

/**
 * Created by Þðà on 26.06.2016.
 */
public class First_call_createConnectionPoolInstanceMethod_Exeption extends Exception {
    public First_call_createConnectionPoolInstanceMethod_Exeption() {
        super();
    }

    public First_call_createConnectionPoolInstanceMethod_Exeption(String msg) {
        super(msg);
    }
}

