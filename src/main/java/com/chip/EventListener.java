package com.chip;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.ArrayList;

/**
 * Created by blake on 9/17/16.
 */
public class EventListener {

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        String[] split = event.getMessage().getContent().split(" ");
        String[] args = event.getMessage().getContent().substring(event.getMessage().getContent().indexOf(" ")+1).split(" ");
        Main.callMsgEvent(split[0].replaceFirst("-", ""), args, event);

    }

    @EventSubscriber
    public void onReady(ReadyEvent e){
        Main.ready(e.getClient());
        System.out.println("Loaded, ready.");
    }


}
