package com.chip;

import commands.*;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by blake on 9/17/16.
 */
public class Main {

    public static ArrayList<Command> commands = new ArrayList<>();
    public static ArrayList<AbstractCommand> abstractCommands = new ArrayList<>();

    public static void main(String[] args) {
        IDiscordClient client = getClient("MjI2MTQyNjA4Njg5MzMyMjI1.Cr9GAA._GHw0NqDeiv9YP266AaN8_n46sg", true); // Gets the client object (from the first example)
        EventDispatcher dispatcher = client.getDispatcher(); // Gets the EventDispatcher instance for this client instance
        dispatcher.registerListener(new EventListener());
    }

    private static void initCommands() {
        //abstract
        abstractCommands.add(new ConversationCommand());
        commands.add(new PingCommand());
        commands.add(new CommandsCommand());
        commands.add(new MemeCommand());
        //real
    }

    public static void ready(IDiscordClient client) {
        client.changeStatus(Status.game("-commands ! =) "));
        initCommands();
    }

    public static IDiscordClient getClient(String token, boolean login) { // Returns an instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        if (login) {
            try {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            } catch (DiscordException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //AbstractCommands are not called via a -, but more like real conversation.

    public static void callMsgEvent(String invoke, String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        System.out.println("Passed to main, doing for loop for commands...");
        boolean called = false;
        if (event.getMessage().getContent().startsWith("-")) {
            System.out.println("Starts with -. Commands.size = " + (commands.size()) + ".");
            for(int i = 1; i <= commands.size(); i++) {
                System.out.println("Looping: " + i);
                Command cmd = commands.get(i - 1);
                System.out.println("Checking if " + cmd.name() + " is equal to " + invoke);
                if (cmd.name().equalsIgnoreCase(invoke)) {
                    cmd.action(args, event);
                    System.out.println("Yes! Called is etting to true..");
                    called = true;
                }
            }
        }
        System.out.println("Now to do abstract commands, checking if called.");
        if (!called) {
            System.out.println("Has NOT been called.");
            for(int i = 1; i <= abstractCommands.size(); i++) {
                    AbstractCommand abstractCommand = abstractCommands.get(i - 1);
                    abstractCommand.action(args, event);
                }
            }
        }
    }
