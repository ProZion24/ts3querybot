package software.zion.ts3bot.commandmanager;

import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import software.zion.ts3bot.commands.SilenceCommand;
import software.zion.ts3bot.usermanager.UserManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zion
 * @created 19/11/2020 - 23:46
 * @project ts3querybot
 */
public class CommandManager {
    private final Map<Class<? extends  Command>, Command> commandExecMap;
    private final Map<String, Class<? extends  Command>> commandMap;
    private final Map<String, Class<? extends  Command>> aliasMap;
    private final UserManager userManager;

    public CommandManager(UserManager userManager) {
        this.userManager = userManager;
        this.commandExecMap = new HashMap<>();
        this.commandMap = new HashMap<>();
        this.aliasMap = new HashMap<>();
        this.registerCommands();
    }

    private void registerCommands() {
        addCommand(new SilenceCommand(this));
    }

    public void addCommand(Command command) {
        this.commandExecMap.put(command.getClass(), command);
        this.commandMap.put(command.getName().toLowerCase(), command.getClass());
        for (String alias : command.getAliases()) {
            this.aliasMap.put(alias.toLowerCase(), command.getClass());
        }
    }

    public void onMessage(TextMessageEvent event) {
        String msg = event.getMessage();
        if (!msg.startsWith("!")) {
            return;
        }
        msg = msg.replaceAll("\\s+", " ");
        String[] args = msg.substring(1).split( " ");
        String cmdName = args[0].toLowerCase();
        Class<? extends Command> cmdClazz = commandMap.get(cmdName);
        if (cmdClazz == null) {
            cmdClazz = aliasMap.get(cmdName);
        }
        if (cmdClazz == null) {
            userManager.getUser(event.getInvokerId()).sendPrivateTextMessage("cmd.notFound");
            return;
        }
        Command command = this.commandExecMap.get(cmdClazz);
        String[] outArgs;
        if (args.length > 1) {
            outArgs = new String[args.length - 1];
            System.arraycopy(args, 1, outArgs, 0, args.length - 1);
        } else outArgs = new String[0];
        final ClientInfo clientInfo = getUserManager().getBot().getTs3Api().getClientInfo(event.getInvokerId());
        if (!command.hasAccess(clientInfo)) return;
        System.out.printf("(%s) %s executed: %s%n", clientInfo.getUniqueIdentifier(), clientInfo.getNickname(), msg);
        command.onCommand(getUserManager().getBot().getTs3Api().getClientInfo(event.getInvokerId()), outArgs);
    }


    public Map<Class<? extends Command>, Command> getCommandExecMap() {
        return commandExecMap;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
