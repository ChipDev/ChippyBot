package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by blake on 9/17/16.
 */
public interface AbstractCommand {

    //AbstractCommands are not called via a -, but more like real conversation.

    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException;

}
