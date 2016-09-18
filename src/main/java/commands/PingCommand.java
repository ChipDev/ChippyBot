package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class PingCommand implements Command {

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {

        event.getMessage().getChannel().sendMessage("Pong!");

    }

    @Override
    public String name() {
        return "ping";
    }

}
