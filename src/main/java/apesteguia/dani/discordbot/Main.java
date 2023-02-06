package apesteguia.dani.discordbot;

import apesteguia.dani.discordbot.configuration.ConfigFile;
import apesteguia.dani.discordbot.configuration.ConfigValues;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Main extends ListenerAdapter {



    public static JDABuilder jdaBuilder;

    public static void main(String[] args) {

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.competing(ConfigValues.BOT_STATUS));
        jdaBuilder.addEventListeners(new Main());
        jdaBuilder.build();
    }

    private static void setStatus(){
        switch(ConfigValues.BOT_STATUS){
            case "online":
                jdaBuilder.setStatus(OnlineStatus.ONLINE);
                break;
            case "offline":
                jdaBuilder.setStatus(OnlineStatus.OFFLINE);
                break;
            case "idle":
                jdaBuilder.setStatus(OnlineStatus.IDLE);
                break;
            case "busy":
                jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] arguments = event.getMessage().getContentRaw().split(" ");
        if(arguments[0].equals("!mention")){
            Member member = event.getMessage().getMentions().getMembers().get(0);
            if(!member.getUser().isBot()){
                event.getChannel().sendMessage("Have a good day "+member.getUser().getAsMention()).queue();
            }else{
                event.getChannel().sendMessage("Sorry, I can't mention bots").queue();
            }
            //event.getChannel().sendMessage("How are you doing today").queue();
        }
    }
}

