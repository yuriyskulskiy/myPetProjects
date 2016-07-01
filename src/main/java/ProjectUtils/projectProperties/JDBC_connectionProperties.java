package ProjectUtils.projectProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Þðà on 25.06.2016.
 */
public class JDBC_connectionProperties {
    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";
//    FileInputStream fis;
//    Properties connectionProperties = new Properties();
   public static final String DB_URL_KEY = "db.URL";
   public static final String DB_USER_NAME_KEY = "db.login";
   public static final String DB_PASSWORD_KEY = "db.password";

    public static Properties getConnectionProperties() {
        FileInputStream fis;
        Properties connectionProperties = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            connectionProperties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectionProperties;
    }
}

