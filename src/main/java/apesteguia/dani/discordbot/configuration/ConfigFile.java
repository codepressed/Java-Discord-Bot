package apesteguia.dani.discordbot.configuration;


import apesteguia.dani.discordbot.Main;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigFile {

    public static void loadConfig(){
        String filename = "bot-settings.yml";
        ClassLoader classLoader = Main.class.getClassLoader();

        try(InputStream inputStream = classLoader.getResourceAsStream(filename)){
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
                bufferedWriter.write(result);
                bufferedWriter.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
