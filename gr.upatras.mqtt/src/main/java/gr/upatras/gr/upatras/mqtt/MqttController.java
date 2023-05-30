package gr.upatras.gr.upatras.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class MqttController {

    private String mqttBroker="tcp://test.mosquitto.org:1883";

    private String mqttClientId="mqtt_client";

    private String mqttTopic="my_topic";

    @PostMapping("api/data")
    public ResponseEntity<String> postData(@RequestBody String payload) {
        try {
            // Create an MQTT client
            IMqttClient mqttClient = new MqttClient(mqttBroker, mqttClientId);

            // Connect to the MQTT broker
            mqttClient.connect();

            // Create an MQTT message
            MqttMessage mqttMessage = new MqttMessage(payload.getBytes());

            // Publish the MQTT message to the topic
            mqttClient.publish(mqttTopic, mqttMessage);

            // Disconnect from the MQTT broker
            mqttClient.disconnect();

            return ResponseEntity.ok("MQTT message sent successfully. Message: "+mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send MQTT message.");
        }
    }
}
