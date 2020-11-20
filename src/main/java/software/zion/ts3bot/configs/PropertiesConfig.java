package software.zion.ts3bot.configs;

import java.util.HashMap;

/**
 * @author Zion
 * @created 18/11/2020 - 16:11
 * @project ts3querybot
 */
public class PropertiesConfig {
    private String serverName;
    private HashMap<String, Integer> groups;
    private HashMap<String, Integer> supportGroups;

    public String getServerName() {
        return serverName;
    }

    public HashMap<String, Integer> getGroups() {
        return groups;
    }

    public HashMap<String, Integer> getSupportGroups() {
        return supportGroups;
    }
}
