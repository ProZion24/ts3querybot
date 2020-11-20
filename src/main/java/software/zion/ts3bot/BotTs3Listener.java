package software.zion.ts3bot;

import com.github.theholywaffle.teamspeak3.api.event.*;
import software.zion.ts3bot.usermanager.User;

import javax.jws.soap.SOAPBinding;

/**
 * @author Zion
 * @created 19/11/2020 - 23:38
 * @project ts3querybot
 */
public class BotTs3Listener implements TS3Listener {
    private final Bot bot;

    public BotTs3Listener(Bot bot) {
        this.bot = bot;
    }

    /**
     *
     * @param textMessageEvent
     */
    @Override
    public void onTextMessage(TextMessageEvent textMessageEvent) {
        if(this.bot.getTs3Api().getClientInfo(textMessageEvent.getInvokerId()).isServerQueryClient()) return;
        int clientId = textMessageEvent.getTargetClientId();
        this.bot.getCommandManager().onMessage(textMessageEvent);
    }

    /**
     *
     * @param clientJoinEvent
     */
    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        int clientId = clientJoinEvent.getClientId();
        this.bot.getUserManager().onJoin(clientId);
        User user = this.bot.getUserManager().getUser(clientId);
        user.sendPrivateTextMessage("JoinMSG", this.bot.getConfig().getPropertiesConfig().getServerName());
        user.sendPrivateTextMessage("JoinMSG.subline");
    }

    /**
     *
     * @param clientLeaveEvent
     */
    @Override
    public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
        int clientId = clientLeaveEvent.getClientId();
        this.bot.getUserManager().onQuit(clientId);
    }

    /**
     *
     * @param serverEditedEvent
     */
    @Override
    public void onServerEdit(ServerEditedEvent serverEditedEvent) {

    }

    /**
     *
     * @param channelEditedEvent
     */
    @Override
    public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

    }

    /**
     *
     * @param channelDescriptionEditedEvent
     */
    @Override
    public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

    }

    /**
     *
     * @param clientMovedEvent
     */
    @Override
    public void onClientMoved(ClientMovedEvent clientMovedEvent) {

    }

    /**
     *
     * @param channelCreateEvent
     */
    @Override
    public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

    }

    /**
     *
     * @param channelDeletedEvent
     */
    @Override
    public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

    }

    /**
     *
     * @param channelMovedEvent
     */
    @Override
    public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

    }

    /**
     *
     * @param channelPasswordChangedEvent
     */
    @Override
    public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

    }

    /**
     *
     * @param privilegeKeyUsedEvent
     */
    @Override
    public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

    }
}
