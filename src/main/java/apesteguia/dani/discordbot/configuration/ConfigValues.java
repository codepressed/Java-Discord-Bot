package apesteguia.dani.discordbot.configuration;

public class ConfigValues {

    public static String BOT_TOKEN;
    public static String BOT_STATUS;
    public static String[] DESCRIPTION;

    public static void loadValues(){
        BOT_TOKEN = ConfigManager.getKeys("token");
        BOT_STATUS = ConfigManager.getKeys("status");
        DESCRIPTION  = ConfigManager.getKeys("description").split(",");
    }
}
