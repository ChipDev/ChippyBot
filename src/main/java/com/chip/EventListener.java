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
        ArrayList<String> splitArray = new ArrayList<String>();
        for(int i = 0; i < split.length; i++){
            splitArray.add(split[i]);
        }
        String[] args = new String[splitArray.size()-1];
        try {
            splitArray.subList(1, splitArray.size() - 1).toArray(args);
        }catch(Exception e) {
            args = null;
        }
        System.out.println("EventListener- Message recieved... passing to Main.");
        Main.callMsgEvent(split[0].replaceFirst("-", ""), args, event);
        System.out.println("----------------");
    }

    @EventSubscriber
    public void onReady(ReadyEvent e){
        Main.ready(e.getClient());
        System.out.println("Loaded, ready.");
    }


}
