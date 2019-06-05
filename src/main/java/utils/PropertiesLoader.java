package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Dmytro Torlop
 * on 03.06.19
 */
public class PropertiesLoader {

    private static Logger logger = Logger.getLogger(PropertiesLoader.class.getName());

    public static Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            logger.error("Не вдалося завантажити properties [" + path + "]");
            e.printStackTrace();
        }
        return properties;
    }
}
