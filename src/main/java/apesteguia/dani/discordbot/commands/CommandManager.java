package apesteguia.dani.discordbot.commands;

import apesteguia.dani.discordbot.commands.commands.*;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter {

    private final BanCommand banCommand;
    private final ClearCommand clearCommand;
    private final CooldownCommand cooldownCommand;
    private final EditCommand editCommand;
    private final HelpCommand helpCommand;
    private final KickCommand kickCommand;
    private final RestartCommand restartCommand;
    private final TestCommand testCommand;
    private final UnbanCommand unbanCommand;

    public CommandManager() {
        this.banCommand = new BanCommand();
        this.clearCommand = new ClearCommand();
        this.cooldownCommand = new CooldownCommand();
        this.editCommand = new EditCommand();
        this.helpCommand = new HelpCommand();
        this.kickCommand = new KickCommand();
        this.restartCommand = new RestartCommand();
        this.testCommand = new TestCommand();
        this.unbanCommand = new UnbanCommand();
    }
}
