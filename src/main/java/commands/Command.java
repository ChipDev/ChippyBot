package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by blake on 9/17/16.
 */
public interface Command {

    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException;
    public String name();

}
