package com.hobart.redis.common.redis;

import java.io.Serializable;
import java.util.Map;

/**
 * 考虑下面的接口定义。
 * 注意，尽管接口没有扩展MessageListener接口，仍然可以通过使用MessageListenerAdapter将其用作MDP
 *  类（容器中配置）。还要注意各种消息处理方法是如何根据
 *
 * 它们可以接收和处理的各种消息类型的内容
 *
 * @author niugang
 *
 */
public interface MessageDelegate {
    // 默认监听的方法就是handleMessage
    void handleMessage(String message);
    /*
     * void handleMessage(Map message); void handleMessage(byte[] message); void
     * handleMessage(Serializable message); // pass the channel/pattern as well
     * void handleMessage(Serializable message, String channel);
     */
}
