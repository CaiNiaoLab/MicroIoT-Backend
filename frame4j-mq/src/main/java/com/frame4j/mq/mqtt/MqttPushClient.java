package com.frame4j.mq.mqtt;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MqttPushClient {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout ;   //连接超时

    private MqttClient client;

    private static volatile MqttPushClient mqttPushClient = null;

    public static MqttPushClient getInstance(){

        if(null == mqttPushClient){
            synchronized (MqttPushClient.class){
                if(null == mqttPushClient){
                    mqttPushClient = new MqttPushClient();
                }
            }

        }
        return mqttPushClient;

    }

    private MqttPushClient() {
        connect();
    }

    private void connect(){
        try {
            client = new MqttClient("tcp://112.74.165.209:1885", "mqttId", new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName("guest");
            options.setPassword("guest".toCharArray());
            options.setConnectionTimeout(3000);
            options.setKeepAliveInterval(3000);
            try {
                //client.setCallback(new PushCallback());
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布，默认qos为0，非持久化
     * @param topic
     * @param msg
     */
    public void publish(String topic,String msg){
        publish(0, false, topic, msg);
    }

    /**
     * 发布
     * @param qos
     * @param retained
     * @param topic
     * @param msg
     */
    public void publish(int qos,boolean retained,String topic,String msg){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(msg.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if(null == mTopic){
            log.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     * @param topic
     */
    public void subscribe(String topic){
        subscribe(topic,0);
    }

    /**
     * 订阅某个主题
     * @param topic
     * @param qos
     */
    public void subscribe(String topic,int qos){
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        String kdTopic = "/cc3200/ToggleLEDCmdL2";
        String msg = "{ \"identifier\": \"MircoIoTData\",\"name\": \"Control\",\"desc\": \"Downlink\",\"type\": \"bool\",\"data\": \"0\"}";
        Map map = new HashMap();
        map.put("identifier","MircoIoTData");
        map.put("name","Control");
        map.put("desc","Downlink");
        map.put("type","bool");
        map.put("data","0");
        System.out.println(new Gson().toJson(msg));
        MqttPushClient.getInstance().publish(0, false, kdTopic, new Gson().toJson(msg));
    }
}