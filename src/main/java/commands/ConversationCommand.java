package commands;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.Random;

/**
 * Created by blake on 9/17/16.
 */
public class ConversationCommand implements AbstractCommand {


    @Override
    public void action(String[] args, MessageReceivedEvent event) throws RateLimitException, DiscordException, MissingPermissionsException {
        if(event.getMessage().getContent().toLowerCase().replace(" ", "").replace(",","").contains("hellochipbot") || event.getMessage().getContent().replace(" ", "").replace(",","").toLowerCase().contains("heychipbot")){
            event.getMessage().getChannel().sendMessage(getRandomGreeting() + " " + event.getMessage().getAuthor().getDisplayName(event.getMessage().getGuild()) + "!");
            Thread waiting = new Thread() {
                MessageReceivedEvent secondevent = event;
                public void run() {
                    try {
                        this.sleep(1000);
                        secondevent.getMessage().getChannel().sendMessage(getRandomByTheWay());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            waiting.start();
        }
        String replaced = event.getMessage().getContent().toLowerCase().replace("*", "").replace("'", "").replace(" ", "").replace(",","").toLowerCase();
        if(replaced.contains("chipbotyourestupid")
                || replaced.contains("chipbotyouarestupid")
                || replaced.contains("chipbotisstupid")){
            event.getMessage().getChannel().sendMessage(getRandomBlame());
        } 
        else if(replaced.contains("chipbotfuckoff")){
            event.getMessage().getChannel().sendMessage(getRandomfoff().replace("&&^", event.getMessage().getContent()).
                    replace("&&%", event.getMessage().getAuthor().getNicknameForGuild(event.getMessage().getGuild()).orElse(event.getMessage().getAuthor().getName())));
        }
        else if(replaced.contains("maybechipbotcan") || replaced.contains("chipbotmightbeable") || replaced.contains("chipbotcan")|| replaced.contains("maybechipbotisable")) {
            event.getMessage().getChannel().sendMessage(getRandomIcant());

        }
    }

    public String getRandomByTheWay() {
        String[] answers = {
                "By the way, you can do -commands to get the current commands of mine. They are pretty crappy but cool."
                ,"Oh, yeah, you can type -commands to see my commands. I guess it's kinda cool."
                ,"Whats up? Oh yeah, almost forgot.. you can type -commands to see my somewhat amazing possiblities."
                ,"Almost forgot to tell you- I'm kinda good at glitching out (I have a bad owner), you can type -commands to see my commands *cough* glitches *cough*..         robots dont cough..."
                ,"Wanna vc? I want to tell you a sto- wait, I'm a bot. Cant- Sorry. I can do less impressive things though. do -commands to see that crap."
                ,"*poke* m8, I can do tricks.. if my developer was smart enough (he isnt, *dont tell him though*), type -commands to see these good (*bad*) tricks"
        };
        Random r = new Random();
        String random = answers[r.nextInt(answers.length)];
        return random;
    }

    public String getRandomGreeting() {
        String[] greetings = {"Yo","ayy","hey","Hey","Ay", "Ayyy,", "herro dis is chipbot", "Hi"
        };
        Random r = new Random();
        String random = greetings[r.nextInt(greetings.length)];
        return random;
    }

    public String getRandomBlame() {
        String[] greetings = {"Just.. blame chipdev..","Im not bad, its chipdev who's bad.","Im only partially stupid, chip is mostly.","I was programmed to respond to this message not blaming chipdev.",
                "am I stupid? HUH! Well, uh, k. *blame chipdev*",";-; chip deserves #1 stupidest bot programmer", "Im smarterist than any coders in the wholes of flat earth!!!!", "Sadly you cant blame me... *chip*",
                "...", ";-;"
        };
        Random r = new Random();
        String random = greetings[r.nextInt(greetings.length)];
        return random;
    }
    public String getRandomfoff() {
        String[] greetings = {"Error: SelfESTEEM.exe corrupted, restarting system... System level : S.A.D.", "NPE at line &&%: Cannot read the statement: '&&^', too insulting.", "Error: Cannot execute command: '&&^', reason: SaDException at line 69;",
        "Sir, although I'd like to leave this premise as you've eloquently proposed, I really feel the need to tell you something beforehand; I'd really appreciate it if you'd perform autofellatio and help humanity and bots like me by not reproducing- Anyway, now that that's done, I shall take my leave as you wanted."};
        Random r = new Random();
        String random = greetings[r.nextInt(greetings.length)];
        return random;
    }
    public String getRandomIcant() {
        String[] greetings = {"I'm most likely not able to do ANYTHING you want, chipdev has bad taste!", "I'm 99% sure I cant...",
                "I cant most likely, I either dont know or wasn't programmed to do so.", "Unless its a command, or some really bad conversation-making, I can't do anything good."};
        Random r = new Random();
        String random = greetings[r.nextInt(greetings.length)];
        return random;
    }
}
