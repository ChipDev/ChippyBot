package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class PingCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if(args[0].equalsIgnoreCase("help")) {
            event.getMessage().getChannel().sendMessage(help());
        }else {
            event.getMessage().getChannel().sendMessage("Pong!");
        }

    }

    @Override
    public String name() {
        return "ping";
    }

    @Override
    public String help() {
        return "```Help for 'ping': \nUsage: -ping : " + usage() + "." + varextra() + "```" ;
    }

    @Override
    public String usage() {
        return "Returns with a 'pong' as a test command!";
    }

    @Override
    public String varextra() {
        return "";
    }

}
