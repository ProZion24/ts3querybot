package software.zion.ts3bot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import software.zion.ts3bot.commandmanager.CommandManager;
import software.zion.ts3bot.configs.Config;
import software.zion.ts3bot.usermanager.User;
import software.zion.ts3bot.usermanager.UserManager;

/**
 * @author Zion
 * @created 18/11/2020 - 16:02
 * @project ts3querybot
 */
public class Bot {
    private final TS3Api ts3Api;
    private final Config config;
    private final CommandManager commandManager;
    private final UserManager userManager;
    private Channel defaultChannel;

    /**
     *
     * @param config
     */
    public Bot(Config config) {
        this.config = config;
        final TS3Config ts3conf = new TS3Config();
        ts3conf.setHost(config.getTeamspeakConfig().getAddress().getHost());
        ts3conf.setQueryPort(config.getTeamspeakConfig().getAddress().getPort());
        ts3conf.setFloodRate(TS3Query.FloodRate.custom(config.getTeamspeakConfig().getFloodRate().getFloodRate()));

        final TS3Query query = new TS3Query(ts3conf);

        query.connect();

        ts3Api = query.getApi();
        ts3Api.login(config.getTeamspeakConfig().getUser(), config.getTeamspeakConfig().getPassword());
        ts3Api.selectVirtualServerByPort(config.getTeamspeakConfig().getPort());
        ts3Api.setNickname(config.getTeamspeakConfig().getNickname());
        System.out.println("Bot connected");

        this.userManager = new UserManager(this);
        this.commandManager = new CommandManager(userManager);

        ts3Api.getChannels().forEach(c -> {if (c.isDefault()) defaultChannel = c;});
        ts3Api.registerAllEvents();
        ts3Api.registerEvent(TS3EventType.TEXT_PRIVATE);
        ts3Api.addTS3Listeners(new BotTs3Listener(this));
    }

    public Config getConfig() {
        return config;
    }

    public TS3Api getTs3Api() {
        return ts3Api;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
