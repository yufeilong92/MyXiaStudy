package com.lawyee.apppublic.smack;

import android.content.Context;

import com.lawyee.apppublic.R;
import com.lawyee.apppublic.vo.LoginResult;
import com.lawyee.apppublic.vo.UserVO;
import com.nostra13.universalimageloader.utils.L;

import net.lawyee.mobilelib.app.AppContext;
import net.lawyee.mobilelib.utils.JavaLangUtil;
import net.lawyee.mobilelib.utils.StringUtil;

import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.NotLoggedInException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SmackManager {
    private static final String TAG = "SmackManager";

    public static final String MULTI_CHAT_ADDRESS_SPLIT = "@conference.";

    private static volatile SmackManager sSmackManager;
    /**
     * 连接
     */
    private XMPPTCPConnection mConnection;

    private SmackManager() {

    }

    /**
     * 获取操作实例
     *
     * @return
     */
    public static SmackManager getInstance() {
        if (sSmackManager == null) {
            synchronized (SmackManager.class) {
                if (sSmackManager == null) {
                    sSmackManager = new SmackManager();
                }
            }
        }
        return sSmackManager;
    }

    /**
     * 初始化连接服务器
     */
    public void initConnect()
    {
        this.mConnection = connect();
    }

    /**
     * 连接服务器
     *
     * @return
     */
    private XMPPTCPConnection connect() {
        //从配置文件中读取
        Context context = AppContext.context();
        try {
            SmackConfiguration.setDefaultPacketReplyTimeout(10000);
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    //是否开启安全模式
                    .setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled)
                    //服务器名称
                    .setServiceName(context.getString(R.string.url_openfire_servername))
                    .setHost(context.getString(R.string.url_openfire_host))//服务器IP地址
                    //服务器端口
                    .setPort(JavaLangUtil.StrToInteger(context.getString(R.string.url_openfire_port),5222))
                    //是否开启压缩
                    .setCompressionEnabled(false)
                    //开启调试模式
                    .setDebuggerEnabled(true).build();

            XMPPTCPConnection connection = new XMPPTCPConnection(config);
            ReconnectionManager reconnectionManager = ReconnectionManager.getInstanceFor(connection);
            reconnectionManager.enableAutomaticReconnection();//允许自动重连
            reconnectionManager.setFixedDelay(2);//重连间隔时间
            connection.addConnectionListener(new SmackConnectionListener());//连接监听
            connection.connect();
            return connection;
        } catch (Exception e) {
            L.e(TAG,"connect opernfire error:"+e.getMessage());
            return null;
        }
    }

    /**
     * 登陆
     *
     * @param userVO     用户账号
     * @return
     */
    public LoginResult login(UserVO userVO) {

        if(userVO==null|| StringUtil.isEmpty(userVO.getLoginId()))
        {
            return new LoginResult(false,"用户未登录");
        }
        try {
            if (!isConnected()) {
                throw new IllegalStateException("服务器断开，请先连接服务器");
            }
            mConnection.login(userVO.getOpenfireLoginId(), userVO.getOpenfirePassword(),AppContext.context().getString(R.string.url_openfire_ressource));
            return new LoginResult(userVO, true);
        } catch (Exception e) {
            L.e(TAG, "login failure",e);
            return new LoginResult(false, e.getMessage());
        }
    }

    /**
     * 获取XMPPTCPConnection，如果未连接,则返回null
     * @return
     */
    public XMPPTCPConnection getConnection() {

        if (mConnection == null||!isConnected() ) {
            return null;
        }
        return mConnection;
    }

    /**
     * 注销
     *
     * @return
     */
    public boolean logout() {

        if (!isConnected()) {
            return false;
        }
        try {
            mConnection.instantShutdown();
            return true;
        } catch (Exception e) {
            L.e(TAG,  "logout failure:"+e.getMessage());
            return false;
        }
    }

    public boolean isAuthenticated()
    {
        if(isConnected())
            return mConnection.isAuthenticated();
        return false;
    }

    /**
     * 是否连接成功
     *
     * @return
     */
    public boolean isConnected() {
        if(mConnection == null||!mConnection.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * 断开连接，注销
     *
     * @return
     */
    public boolean disconnect() {

        if (!isConnected()) {
            return false;
        }
        mConnection.disconnect();
        return true;
    }

    /**
     * 删除当前登录的用户信息(从服务器上删除当前用户账号)
     *
     * @return
     */
    public boolean deleteUser() {

        if (!isConnected()) {
            return false;
        }
        try {
            AccountManager.getInstance(mConnection).deleteAccount();//删除该账号
            return true;
        } catch (NoResponseException | XMPPErrorException
                | NotConnectedException e) {
            return false;
        }
    }

    /**
     * 注册用户信息
     *
     * @param username   账号
     * @param password   账号密码
     * @param attributes 账号其他属性，参考AccountManager.getAccountAttributes()的属性介绍
     * @return
     */
    public boolean registerUser(String username, String password, Map<String, String> attributes) {

        if (!isConnected()) {
            return false;
        }
        try {
            AccountManager.getInstance(mConnection).createAccount(username, password, attributes);
            return true;
        } catch (NoResponseException | XMPPErrorException
                | NotConnectedException e) {
            L.e(TAG, "register failure", e);
            return false;
        }
    }

    /**
     * 修改密码
     *
     * @param newpassword 新密码
     * @return
     */
    public boolean changePassword(String newpassword) {
        if (!isConnected()) {
            return false;
        }
        try {
            AccountManager.getInstance(mConnection).changePassword(newpassword);
            return true;
        } catch (NoResponseException | XMPPErrorException | NotConnectedException e) {
            L.e(TAG, "change password failure", e);
            return false;
        }
    }

    /**
     * 更新用户状态
     *
     * @param code 0 设置在线 1设置Q我吧，2设置忙碌 3 设置离开 4设置隐身 5设置离线
     * @return
     */
    public boolean updateUserState(StatusMode code) {
        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        try {
            Presence presence;
            if(code==StatusMode.available)
            {
                    presence = new Presence(Presence.Type.available);
                    presence.setMode(Presence.Mode.available);
                    mConnection.sendStanza(presence);
            }else if(code==StatusMode.chat) {
                presence = new Presence(Presence.Type.available);
                presence.setMode(Presence.Mode.chat);
                mConnection.sendStanza(presence);
            }else if(code==StatusMode.dnd) {
                presence = new Presence(Presence.Type.available);
                presence.setMode(Presence.Mode.chat);
                mConnection.sendStanza(presence);
            }else if(code==StatusMode.away) {

            }else if(code ==StatusMode.xa) {

            }else if(code==StatusMode.offline) {
                presence = new Presence(Presence.Type.unavailable);
                mConnection.sendStanza(presence);
            }
            return true;
        } catch (NotConnectedException e) {
            L.e(TAG,e.getMessage());
        }
        return false;
    }

    /**
     * 获取账户昵称
     *
     * @return
     */
    public String getAccountName() {

        if (isConnected()) {
            try {
                return AccountManager.getInstance(mConnection).getAccountAttribute("name");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new NullPointerException("服务器连接失败，请先连接服务器");
    }

    /**
     * 获取账户所有属性信息
     *
     * @return
     */
    public Set<String> getAccountAttributes() {

        if (isConnected()) {
            try {
                return AccountManager.getInstance(mConnection).getAccountAttributes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new NullPointerException("服务器连接失败，请先连接服务器");
    }

    /**
     * 创建聊天窗口
     *
     * @param jid 好友的JID
     * @return
     */
    public Chat createChat(String jid) {

        if (isConnected()) {
            ChatManager chatManager = ChatManager.getInstanceFor(mConnection);
            return chatManager.createChat(jid);
        }
        return null;
    }

    /**
     * 获取聊天对象管理器
     *
     * @return
     */
    public ChatManager getChatManager() {

        if (isConnected()) {
            ChatManager chatManager = ChatManager.getInstanceFor(mConnection);
            return chatManager;
        }
        return null;
    }/**
     * 获取聊天对象管理器
     *
     * @return
     */
    public Roster getRosterManager() {
        if (isConnected()) {
            Roster roster = Roster.getInstanceFor(mConnection);
            return roster;
        }
        return null;
    }

    public Presence getPresence(String user)
    {
        if (isConnected()) {
            return Roster.getInstanceFor(mConnection).getPresence(user);
        }
        return null;
    }

    /**
     * 获取当前登录用户的所有好友信息
     *
     * @return
     */
    public Set<RosterEntry> getAllFriends() {

        if (isConnected()) {
            if(!Roster.getInstanceFor(mConnection).isLoaded())
                try {
                    waitUntilLoaded();
                } catch (InterruptedException e) {
                    return null;
                }
            return Roster.getInstanceFor(mConnection).getEntries();
        }
        return null;
    }

    protected boolean waitUntilLoaded() throws InterruptedException {
        long waitTime = mConnection.getPacketReplyTimeout();
        long start = System.currentTimeMillis();
        while (!Roster.getInstanceFor(mConnection).isLoaded()) {
            if (waitTime <= 0) {
                break;
            }
            synchronized (this) {
                if (!Roster.getInstanceFor(mConnection).isLoaded()) {
                    wait(waitTime);
                }
            }
            long now = System.currentTimeMillis();
            waitTime -= now - start;
            start = now;
        }
        return Roster.getInstanceFor(mConnection).isLoaded();
    }

    /**
     * 获取指定账号的好友信息
     *
     * @param user 账号
     * @return
     */
    public RosterEntry getFriend(String user) {

        if (isConnected()) {
            return Roster.getInstanceFor(mConnection).getEntry(user);
        }
        return null;
    }

    /**
     * 添加好友
     *
     * @param user      用户账号
     * @param nickName  用户昵称
     * @param groupName 所属组名
     * @return
     */
    public boolean addFriend(String user, String nickName, String groupName) {

        if (isConnected()) {
            try {
                Roster.getInstanceFor(mConnection).createEntry(user, nickName, new String[]{groupName});
                return true;
            } catch (NotLoggedInException | NoResponseException
                    | XMPPErrorException | NotConnectedException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 获取聊天对象的Fully的jid值
     *
     * @param userName 用户账号
     * @return
     */
    public String getChatJid(String userName,String resource) {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        return userName + "@" + mConnection.getServiceName()+(StringUtil.isEmpty(resource)?"":"/"+resource);
    }

    /**
     * Jid The fully qualified jabber ID (i.e. full JID) with resource of the user
     *
     * @param userName
     * @return
     */
    public String getFullJid(String userName) {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        return userName + "@" + mConnection.getServiceName() + "/" + AppContext.context().getString(R.string.url_openfire_ressource);
    }

    /**
     * 获取文件传输的完全限定Jid The fully qualified jabber ID (i.e. full JID) with resource of the user to
     * send the file to.
     *
     * @param userName 用户名，也就是RosterEntry中的user
     * @return
     */
    public String getFileTransferJid(String userName) {

        return getFullJid(userName);
    }

    /**
     * 获取发送文件的发送器
     *
     * @param jid 一个完整的jid(如：laohu@192.168.0.108/Smack，后面的Smack应该客户端类型，不加这个会出错)
     * @return
     */
    public OutgoingFileTransfer getSendFileTransfer(String jid) {

        if (isConnected()) {
            return FileTransferManager.getInstanceFor(mConnection).createOutgoingFileTransfer(jid);
        }
        throw new NullPointerException("服务器连接失败，请先连接服务器");
    }

    /**
     * 添加文件接收的监听
     *
     * @param fileTransferListener
     */
    public void addFileTransferListener(FileTransferListener fileTransferListener) {

        if (isConnected()) {
            FileTransferManager.getInstanceFor(mConnection).addFileTransferListener(fileTransferListener);
            return;
        }
        throw new NullPointerException("服务器连接失败，请先连接服务器");
    }

    /*<iq from="6b74ad17-d2f6-47c8-98ec-46f45611cfeb@conference.121.42.13.79" id="92xQT-13" to="zhangsan@121.42.13.79/Smack" type="result">
        <query xmlns="http://jabber.org/protocol/muc#owner">
        <x type="form" xmlns="jabber:x:data">
        <title>房间配置</title>
        <instructions>已创建房间“6b74ad17-d2f6-47c8-98ec-46f45611cfeb”。要接受缺省配置，请单击“确定”按钮。或填写以下表单以完成设置：</instructions>
        <field type="hidden" var="FORM_TYPE">
        <value>http://jabber.org/protocol/muc#roomconfig</value>
        </field>
        <field label="房间名称" type="text-single" var="muc#roomconfig_roomname">
        <value>6b74ad17-d2f6-47c8-98ec-46f45611cfeb</value>
        </field>
        <field label="描述" type="text-single" var="muc#roomconfig_roomdesc">
        <value>6b74ad17-d2f6-47c8-98ec-46f45611cfeb</value>
        </field>
        <field label="允许占有者更改主题" type="boolean" var="muc#roomconfig_changesubject">
        <value>0</value>
        </field>
        <field label="最大房间占有者人数" type="list-single" var="muc#roomconfig_maxusers">
        <option label="10">
        <value>10</value>
        </option>
        <option label="20">
        <value>20</value>
        </option>
        <option label="30">
        <value>30</value>
        </option>
        <option label="40">
        <value>40</value>
        </option>
        <option label="50">
        <value>50</value>
        </option>
        <option label="无">
        <value>0</value>
        </option>
        <value>30</value>
        </field>
        <field label="其 Presence 是 Broadcast 的角色" type="list-multi" var="muc#roomconfig_presencebroadcast">
        <option label="主持者">
        <value>mode 01-24 17:30:26.801 15469-16510/cn.ittiger.im D/SMACK: RECV (0): rator</value>
        </option>
        <option label="参与者">
        <value>participant</value>
        </option>
        <option label="访客">
        <value>visitor</value>
        </option>
        <value>moderator</value>
        <value>participant</value>
        <value>visitor</value>
        </field>
        <field label="列出目录中的房间" type="boolean" var="muc#roomconfig_publicroom">
        <value>1</value>
        </field>
        <field label="房间是持久的" type="boolean" var="muc#roomconfig_persistentroom">
        <value>0</value>
        </field>
        <field label="房间是适度的" type="boolean" var="muc#roomconfig_moderatedroom">
        <value>0</value>
        </field>
        <field label="房间仅对成员开放" type="boolean" var="muc#roomconfig_membersonly">
        <value>0</value>
        </field>
        <field type="fixed">
        <value>注意：缺省情况下，只有管理员才可以在仅用于邀请的房间中发送邀请。</value>
        </field>
        <field label="允许占有者邀请其他人" type="boolean" var="muc#roomconfig_allowinvites">
        <value>0</value>
        </field>
        <field label="需要密码才能进入房间" type="boolean" var="muc#roomconfig_passwordprotectedroom">
        <value>0</value>
        </field>
        <field type="fixed">
        <value>如果需要密码才能进入房间，则您必须在下面指定密码。</value>
        </field>
        <field label="密码" type="text-private" var="muc#roomconfig_roomsecret"/>
        <field label="能够发现占有者真实 JID 的角色" type="list-single" var="muc#roomconfig_whois">
        <option label="主持者">
        <value>moderators</value>
        </option>
        <option label="任何人">
        <value>anyone</value>
        </option>
        <value>anyone</value>
        </field>
        <field label="登录房间对话" type="boolean" var="muc#roomconfig_enablelogging">
        <value>0</value>
        </field>
        <field label="仅允许注册的昵称登录" type="boolean" var="x-muc#roomconfig_reservednick">
        <value>0</value>
        </field>
        <field label="允许使用者修改昵称" type="boolean" var="x-muc#roomconfig_canchangenick">
        <value>1</value>
        </field>
        <field type="fixed">
        <value>允许用户注册房间</value>
        </field>
        <field label="允许用户注册房间" type="boolean" var="x-muc#roomconfig_registration">
        <value>1</value>
        </field>
        <field type="fixed">
        <value>您可以指定该房间的管理员。请在每行提供一个 JID。</value>
        </field>
        <field label="房间管理员" type="jid-multi" var="muc#roomconfig_roomadmins"/>
        <field type="fixed">
        <value>您可以指定该房间的其他拥有者。请在每行提供一个 JID。</value>
        </field>
        <field label="房间拥有者" type="jid-multi" var="muc#roomconfig_roomowners">
        <value>zhangsan@121.42.13.79</value>
        </field>
        </x>
        </query>
    </iq>*/

    /**
     * 创建群聊聊天室
     *
     * @param roomName 聊天室名字
     * @param nickName 创建者在聊天室中的昵称
     * @param password 聊天室密码
     * @return
     */
    public MultiUserChat createChatRoom(String roomName, String nickName, String password) throws Exception {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        MultiUserChat muc = getMultiChat(getMultiChatJid(roomName));
        // 创建聊天室
        boolean isCreated = muc.createOrJoin(nickName);
        if (isCreated) {
            // 获得聊天室的配置表单
            Form form = muc.getConfigurationForm();
            // 根据原始表单创建一个要提交的新表单。
            Form submitForm = form.createAnswerForm();
            // 向要提交的表单添加默认答复
            List<FormField> fields = form.getFields();
            for (int i = 0; fields != null && i < fields.size(); i++) {
                if (FormField.Type.hidden != fields.get(i).getType() && fields.get(i).getVariable() != null) {
                    // 设置默认值作为答复
                    submitForm.setDefaultAnswer(fields.get(i).getVariable());
                }
            }
            // 设置聊天室的新拥有者
            List<String> owners = new ArrayList<String>();
            owners.add(mConnection.getUser());// 用户JID
            submitForm.setAnswer("muc#roomconfig_roomowners", owners);
            // 设置聊天室是持久聊天室，即将要被保存下来
            submitForm.setAnswer("muc#roomconfig_persistentroom", true);
            // 房间仅对成员开放
            submitForm.setAnswer("muc#roomconfig_membersonly", false);
            // 允许占有者邀请其他人
            submitForm.setAnswer("muc#roomconfig_allowinvites", true);
            if (password != null && password.length() != 0) {
                // 进入是否需要密码
                submitForm.setAnswer("muc#roomconfig_passwordprotectedroom", true);
                // 设置进入密码
                submitForm.setAnswer("muc#roomconfig_roomsecret", password);
            }
            //不限制房间成员数
            List<String> list = new ArrayList<String>();
            list.add("0");
            submitForm.setAnswer("muc#roomconfig_maxusers", list);
            // 登录房间对话
            submitForm.setAnswer("muc#roomconfig_enablelogging", true);
            // 仅允许注册的昵称登录
            submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
            // 允许使用者修改昵称
            submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
            // 允许用户注册房间
            submitForm.setAnswer("x-muc#roomconfig_registration", false);
            // 发送已完成的表单（有默认值）到服务器来配置聊天室
            muc.sendConfigurationForm(submitForm);
        }
        return muc;
    }

    /**
     * 加入一个群聊聊天室
     *
     * @param roomName 聊天室名字
     * @param nickName 用户在聊天室中的昵称
     * @param password 聊天室密码
     * @return
     */
    public MultiUserChat joinChatRoom(String roomName, String nickName, String password) {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        try {
            // 使用XMPPConnection创建一个MultiUserChat窗口  
            MultiUserChat muc = getMultiChat(getMultiChatJid(roomName));
            // 聊天室服务将会决定要接受的历史记录数量  
            DiscussionHistory history = new DiscussionHistory();
            history.setMaxChars(0);
            // history.setSince(new Date());  
            // 用户加入聊天室  
            muc.join(nickName, password);
            return muc;
        } catch (XMPPException | SmackException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMultiChatJid(String roomName) {

        return roomName + MULTI_CHAT_ADDRESS_SPLIT + mConnection.getServiceName();
    }

    public MultiUserChat getMultiChat(String roomJid) {

        return getMultiUserChatManager().getMultiUserChat(roomJid);
    }

    public MultiUserChatManager getMultiUserChatManager() {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        return MultiUserChatManager.getInstanceFor(mConnection);
    }

    /**
     * 获取服务器上的所有群聊房间
     *
     * @return
     * @throws Exception
     */
    public List<HostedRoom> getHostedRooms() throws Exception {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        return getMultiUserChatManager().getHostedRooms(mConnection.getServiceName());
    }

    public ServiceDiscoveryManager getServiceDiscoveryManager() {

        if (!isConnected()) {
            throw new NullPointerException("服务器连接失败，请先连接服务器");
        }
        return ServiceDiscoveryManager.getInstanceFor(mConnection);
    }
}
