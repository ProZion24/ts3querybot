package software.zion.ts3bot.usermanager;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import software.zion.ts3bot.Bot;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author Zion
 * @created 18/11/2020 - 16:24
 * @project ts3querybot
 */
public class UserManager {
    private HashMap<Integer, User> users;
    private HashMap<String, Locale> locale;

    private Bot bot;

    public Bot getBot() {
        return bot;
    }

    public UserManager(Bot bot) {
        this.bot = bot;
        this.users = new HashMap<Integer, User>();
        this.locale = new HashMap<String, Locale>();

        locale.put("DE", Locale.GERMAN);
        locale.put("AT", Locale.GERMAN);
        locale.put("CH", Locale.GERMAN);

        this.bot.getTs3Api().getClients().forEach(c -> onJoin(c.getId()));
        Locale.setDefault(Locale.ENGLISH);
    }

    public void onJoin(int userID) {
        ClientInfo clientInfo = this.bot.getTs3Api().getClientInfo(userID);
        Locale locale = this.locale.get(clientInfo.getCountry());
        if(locale == null) locale = Locale.ENGLISH;
        User user = new User(this, clientInfo, locale);
        this.users.put(userID, user);
    }

    public void onQuit(int userID) {
        this.users.remove(userID);
    }

    public User getUser(int userID) {
        return this.users.get(userID);
    }
}
