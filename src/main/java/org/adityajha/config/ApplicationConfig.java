package org.adityajha.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ApplicationConfig {
    INSTANCE;
    private Properties properties;
    private ApplicationConfig(){
        this.properties = new Properties();
        loadProperties();
    }
    private void loadProperties(){
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
            if(input == null){
                System.out.println("Unable to find config.properties");
                return;
            }

            properties.load(input);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
