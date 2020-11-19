package software.zion.ts3bot.configs;

import com.mongodb.ServerAddress;
import software.zion.ts3bot.BotFloodRate;

/**
 * @author Zion
 * @created 18/11/2020 - 16:11
 * @project ts3querybot
 */
public class TeamspeakConfig {
    private ServerAddress address;
    private String user;
    private String password;
    private String nickname;
    private int serverPort;
    private int channelID;
    private BotFloodRate floodRate;

    public ServerAddress getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPort() {
        return serverPort;
    }

    public int getChannelID() {
        return channelID;
    }

    public BotFloodRate getFloodRate() {
        return floodRate;
    }
}
