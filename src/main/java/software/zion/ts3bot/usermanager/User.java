package software.zion.ts3bot.usermanager;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Zion
 * @created 18/11/2020 - 16:25
 * @project ts3querybot
 */
public class User {
    private Locale locale;
    private int clientID;
    private transient ClientInfo clientInfo;
    private transient UserManager userManager;
    private transient ResourceBundle messages;

    public User(UserManager userManager, ClientInfo clientInfo, Locale locale) {
        this.locale = locale;
        this.clientInfo = clientInfo;
        this.clientID = clientInfo.getId();
        this.userManager = userManager;
        this.messages = ResourceBundle.getBundle("Messages", locale);
    }

    public void sendPrivateTextMessage(String msgKey) {
        sendPrivateTextMessage(msgKey, null);
    }

    public void sendPrivateTextMessage(String msgKey, Object... values) {
        this.userManager.getBot().getTs3Api().sendPrivateMessage(clientID, String.format(this.messages.getString(msgKey), values));
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public ResourceBundle getMessages() {
        return messages;
    }
}
