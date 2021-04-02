package io.kinhoy.ws;

import com.alibaba.fastjson.JSON;
import io.kinhoy.util.RemoteControlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Date 2020-11-29 01:55
 * @Author kinhoy
 **/
@Slf4j
@Component
@ServerEndpoint("/ws")
public class WsServerEndpoint {

    /**
     * 静态变量，用来记录当前连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger();

    /**
     * concurrent线程安全set，用来存放每个客户端对应的MyWebSocketServer对象
     */
    private static CopyOnWriteArraySet<WsServerEndpoint> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 与每个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接成功调用的方法
     *
     * @param session 可选的参数。与某个客户端的连接会话
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        onlineCount.incrementAndGet();
        log.info("有新的连接加入！当前连接总数为[{}]", onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        onlineCount.decrementAndGet();
        log.info("有一连接关闭！当前连接总数为[{}]", onlineCount.get());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("WebSocket接收消息：sessionId为[{}],消息为：[{}]", this.session.getId(), message);

        try{
            Map msg = (Map) JSON.parse(message);
            String cmdType = (String) msg.get("cmd");
            String text = (String) msg.get("text");
            int x = (int) msg.get("x");
            int y = (int) msg.get("y");
            RemoteControlUtil.doRemoteAction(cmdType,x,y,text);
        }catch (Exception e){
            log.info("系统异常 [{}]",e.getMessage());
            e.printStackTrace();

        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket接收消息错误[{}],sessionId为[{}]", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WsServerEndpoint that = (WsServerEndpoint) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}