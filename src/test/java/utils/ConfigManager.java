package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private final Properties configProperties;
    private final Properties browserStackProperties;

    private ConfigManager() {

        configProperties = loadProperties("config.properties");
        browserStackProperties = loadProperties("browserstack.properties");
    }

    private static class Holder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;
    }

    public String get(String key) {
        return configProperties.getProperty(key);
    }

    public String getBrowserStack(String key) {
        return browserStackProperties.getProperty(key);
    }

    private Properties loadProperties(String fileName) {

        Properties properties = new Properties();

        try (InputStream input =
                     ConfigManager.class.getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException(
                        fileName + " not found"
                );
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load " + fileName, e
            );
        }

        return properties;
    }
}