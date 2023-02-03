package apesteguia.dani.discordbot.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {



    public static String getKeys(String key){
        Properties properties = new Properties();
        try{
            InputStream inputStream = new FileInputStream("bot-settings.yml");
            properties.load(inputStream);
        }catch(IOException exception){
            exception.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
