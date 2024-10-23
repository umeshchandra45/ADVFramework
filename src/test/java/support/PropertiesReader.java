package support;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.WebDriverManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

public final class PropertiesReader {

    private static final Logger LOG = LoggerFactory.getLogger(WebDriverManager.class);
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(Files.newBufferedReader(Path.of("src/test/resources/ENVIRONMENTS_" +
                    System.getProperty("testEnv", "dev").toUpperCase(Locale.ROOT) + ".properties")));
        } catch (Exception e) {
            LOG.warn(e.getLocalizedMessage());
        }
    }

    private PropertiesReader() {
    }

    public static String getKey(String aKey) {
        return PROPERTIES.getProperty(aKey);
    }

    public static void setKey(String aKey, String value) {
        PROPERTIES.setProperty(aKey, value);
    }
}