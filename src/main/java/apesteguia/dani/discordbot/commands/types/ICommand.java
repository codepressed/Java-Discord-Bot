package apesteguia.dani.discordbot.commands.types;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public interface ICommand {
    public void performCommand(String[] arguments, Guild guild, Member member, TextChannel textChannel, Message message);
}
