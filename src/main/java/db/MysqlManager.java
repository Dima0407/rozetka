package db;

import org.apache.log4j.Logger;
import utils.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by Dmytro Torlop
 * on 06.06.19
 */
public class MysqlManager {

    private final String PATH_TO_PROPERTIES = "src/main/resources/db.properties";
    private Logger logger = Logger.getLogger(MysqlManager.class.getName());

    private String url;
    private String user;
    private String pass;
    private Properties properties;
    private String driverClass;

    public MysqlManager() {
        this.properties = PropertiesLoader.loadProperties(PATH_TO_PROPERTIES);
        this.url = properties.getProperty("Url");
        this.user = properties.getProperty("User");
        this.pass = properties.getProperty("Pass");
        this.driverClass = properties.getProperty("DriverClass");
    }

    public void setResultToDB(HashMap<String, Long> products) {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(driverClass).newInstance();
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();

            String clearTableQuery = "DELETE FROM rozetka.products;";
            stmt.executeUpdate(clearTableQuery);
            for (String name : products.keySet()) {
                String query = "INSERT INTO rozetka.products (product_name,price) VALUES ('" + name.replace("'", "\\'") + "', " + products.get(name) + ");";
                stmt.executeUpdate(query);
            }
            logger.info("Results are exported to DB [" + url + "]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException e) {
                logger.error("Error exporting results to DB [" + url + "]");
                e.printStackTrace();
            }
        }
    }
}
