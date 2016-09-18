package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Created by blake on 9/18/16.
 */
public class MemeCommand implements Command {
    @Override
    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if(args[0].equalsIgnoreCase("help") && args.length == 1) {
            event.getMessage().getChannel().sendMessage(help());
        }else if(args.length <= 4) {
            if(Boolean.parseBoolean(args[2])) {
                createMeme(args[1], args[0], args[3], event);
            }else{
                createStandardMeme(args[1], args[0], args[3], event);
            }
        }else{
            event.getMessage().getChannel().sendMessage(help());
        }
    }

    @Override
    public String name() {
        return "meme";
    }

    @Override
    public String help() {
        return "```Help for 'meme': \nUsage: -meme (top) (bottom) (useURL) <URL> : " + usage() + "." + varextra() + "```" ;
    }

    @Override
    public String usage() {
        return "Creates a meme with top and bottom text, with a predefined or custom picture";
    }

    @Override
    public String varextra() {
        return "\nuseURL is a boolean to use a url, if false, URL should be formatted like: \nKappa, Chipdev, Walshy, Doge; (Predefined)";
    }

    public void createMeme(String bottom, String top, String url, MessageReceivedEvent event) {

    }

    public void createStandardMeme(String bottom, String top, String picture, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        String replacedTop = top.
                replace("?", "~q").
                replace("%", "~p").
                replace("/", "~s").
                replace("\"", "''");
        String replacedBottom = bottom.
                replace("?", "~q").
                replace("%", "~p").
                replace("/", "~s").
                replace("\"", "''");

        String doge = "https://pbs.twimg.com/profile_images/378800000822867536/3f5a00acf72df93528b6bb7cd0a4fd0c.jpeg";
        String kappa = "https://cdn.meme.am/images/100x100/9532042.jpg";
        String chipdev = "https://secure.gravatar.com/avatar/0cdac7cf2b6ecf54d937c64193ce238d?s=192&d=https%3A%2F%2Fbukkit.org%2Fstyles%2Fflexile%2Fxenforo%2Favatars%2Favatar_male_l.png";
        String walshy = "https://bukkit.org/data/avatars/l/90927/90927090.jpg";

        if(picture.equalsIgnoreCase("doge") || picture.equalsIgnoreCase("kappa") || picture.equalsIgnoreCase("chipdev") || picture.equalsIgnoreCase("walshy")) {
            String pic = "";
            if(picture.equalsIgnoreCase("doge")){
                pic=doge;
            }
            if(picture.equalsIgnoreCase("kappa")){
                pic=kappa;
            }
            if(picture.equalsIgnoreCase("chipdev")) {
                pic=chipdev;
            }
            if (picture.equalsIgnoreCase("walshy")) {
                pic=walshy;
            }
            event.getMessage().getChannel().sendMessage("http://memegen.link/custom/" + replacedTop + "/" + replacedBottom + ".jpg?alt=" + pic);
        }else{
            event.getMessage().getChannel().sendMessage("Predefined meme picture " + "```" + picture + "```" + "is not one of the predefined memes; do -meme help.");
        }
    }
}
