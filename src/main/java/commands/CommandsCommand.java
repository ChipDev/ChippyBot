package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.Random;

/**
 * Created by blake on 9/17/16.
 */
public class CommandsCommand implements Command {

    public String syntax = "```";
    public String[] waysToSay = { "Here's my commands: ", "my current Commands: ", "somewhat amazing Commands: ", "extremely buggy Executions: ", "new Commands(): ", "Commands are buggy! why would you thin010 110011011: "};

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if(args[0].equalsIgnoreCase("help")) {
            event.getMessage().getChannel().sendMessage(help());
        }else {
            Random r = new Random();
            int n = r.nextInt(waysToSay.length - 1);
            event.getMessage().getChannel().sendMessage(syntax + waysToSay[n] + "\n" + syntax);
        }

    }

    @Override
    public String name() {
        return "commands";
    }

    @Override
    public String help() {
       return "```Help for 'commands': \nUsage: -commands : " + usage() + "." + varextra() + "```" ;
    }

    @Override
    public String usage() {
        return "Lists current loaded commands";
    }

    @Override
    public String varextra() {
        return "\nNew commands are being made all the time!";
    }

}
