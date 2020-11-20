package software.zion.ts3bot.commandmanager;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import software.zion.ts3bot.Bot;
import software.zion.ts3bot.usermanager.UserManager;

/**
 * @author Zion
 * @created 19/11/2020 - 23:42
 * @project ts3querybot
 */
public abstract  class Command {
    private final CommandManager commandManager;
    private final String name;
    private String[] aliases;
    private String syntax;
    private boolean hidden;

    public Command(CommandManager commandManager, String name) {
        this.commandManager = commandManager;
        this.name = name;
        this.aliases = new String[0];
        this.syntax = new String();
        this.hidden = false;
    }

    public abstract void onCommand(ClientInfo clientInfo, String[] args);

    public boolean hasAccess(ClientInfo clientInfo) {
        return true;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getName() {
        return name;
    }

    public UserManager getUserManager() {
        return this.commandManager.getUserManager();
    }

    public Bot getBot() {
        return getUserManager().getBot();
    }

    public TS3Api getApi() {
        return getBot().getTs3Api();
    }
}
