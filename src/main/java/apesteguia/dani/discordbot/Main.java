package apesteguia.dani.discordbot;

import apesteguia.dani.discordbot.configuration.ConfigFile;
import apesteguia.dani.discordbot.configuration.ConfigValues;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Main extends ListenerAdapter {



    public static JDABuilder jdaBuilder;

    public static void main(String[] args) {

        ConfigFile.loadConfig();
        ConfigValues.loadValues();

        jdaBuilder = JDABuilder.createDefault(ConfigValues.BOT_TOKEN);
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.competing(ConfigValues.BOT_STATUS));
        jdaBuilder.addEventListeners(new Main());
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
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
        //String[] arguments = event.getMessage().getContentRaw().split(" ");
        //if(arguments[0].equals("!mention")){
        List<String> words = Arrays.asList(event.getMessage().getContentRaw().split("\\s+"));
        if(words.contains("!mention")){
            Member member = event.getMessage().getMentions().getMembers().get(0);
            if(!member.getUser().isBot()){
                event.getChannel().sendMessage("Have a good day "+member.getUser().getAsMention()).queue();
            }else{
                event.getChannel().sendMessage("Sorry, I can't mention bots").queue();
            }

        }
        else if(words.contains("!sendembed")){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Embed message");
            embedBuilder.setColor(Color.MAGENTA);
            embedBuilder.setDescription("Embed description testing" +
                    "\n This is a 2nd line" +
                    "\n This is a 3rd line");
            embedBuilder.setFooter("Embed footer testing", event.getAuthor().getAvatarUrl());
            embedBuilder.setThumbnail(event.getAuthor().getAvatarUrl());
            embedBuilder.setImage(event.getAuthor().getAvatarUrl());
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();



        }
    }



    @Override
    public void onGuildUpdateName(@NotNull GuildUpdateNameEvent event) {
        TextChannel textChannel = event.getGuild().getSystemChannel();
        if(textChannel != null){
            textChannel.sendMessage("**Discord name was updated to: "+ event.getNewName()+"**").queue();

        }
    }
}

