package software.zion.ts3bot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import software.zion.ts3bot.configs.Config;

/**
 * @author Zion
 * @created 18/11/2020 - 16:02
 * @project ts3querybot
 */
public class Bot {
    private final TS3Api ts3Api;
    private final Config config;
    private Channel privateChannel;

    public Bot(Config config) {
        this.config = config;
        final TS3Config ts3Config = new TS3Config();
        ts3Config.setHost(config.getTeamspeakConfig().getAddress().getHost());
        ts3Config.setQueryPort(config.getTeamspeakConfig().getPort());
        ts3Config.setFloodRate(TS3Query.FloodRate.custom(config.getTeamspeakConfig().getFloodRate().getFloodRate()));
        final TS3Query query = new TS3Query(ts3Config);

        query.connect();
        ts3Api = query.getApi();

        ts3Api.login(config.getTeamspeakConfig().getUser(), config.getTeamspeakConfig().getPassword());
        ts3Api.selectVirtualServerByPort(config.getTeamspeakConfig().getPort());
        ts3Api.setNickname(config.getTeamspeakConfig().getNickname());
        System.out.println("Bot connected");
    }

    public Config getConfig() {
        return config;
    }

    public TS3Api getTs3Api() {
        return ts3Api;
    }
}
