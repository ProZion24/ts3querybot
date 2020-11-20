package software.zion.ts3bot.commands;

import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import software.zion.ts3bot.commandmanager.Command;
import software.zion.ts3bot.commandmanager.CommandManager;

/**
 * @author Zion
 * @created 19/11/2020 - 23:53
 * @project ts3querybot
 */
public class SilenceCommand extends Command {

    private Integer sGId;

    /**
     *
     * @param commandManager
     */
    public SilenceCommand(CommandManager commandManager) {
        super(commandManager, "silence");
        setAliases(new String[]{"ruhe"});
        this.sGId = getBot().getConfig().getPropertiesConfig().getGroups().get("silence");
    }

    /**
     *
     * @param clientInfo
     * @param args
     */
    @Override
    public void onCommand(ClientInfo clientInfo, String[] args) {
        int dbCId = clientInfo.getDatabaseId();
        if(clientInfo.isInServerGroup(sGId)) {
            getApi().removeClientFromServerGroup(sGId, dbCId);
            getUserManager().getUser(clientInfo.getId()).sendPrivateTextMessage("cmd.silence.remove");
            return;
        }
        getApi().addClientToServerGroup(sGId, dbCId);
        getUserManager().getUser(clientInfo.getId()).sendPrivateTextMessage("cmd.silence.add");
    }
}
