package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    static String MQTTHOST = "tcp://tailor.cloudmqtt.com:13582";
    //static String MQTTHOST = "tcp://broker.hivemq.com:1883";
    static String USERNAME = "ffbqwwfa";
    static String PASSWORD = "PAC8F72zynig";

    MqttAndroidClient client;

    TextView subTextD1,subTextD2,subTextD3,subTextD4,subTextD5,subTextD6,subTextD7,subTextD8;

    String Status,pubString="esp/test",subString="esp/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subTextD1 = (TextView)findViewById(R.id.statusD1);
        subTextD2 = (TextView)findViewById(R.id.statusD2);
        subTextD3 = (TextView)findViewById(R.id.statusD3);
        subTextD4 = (TextView)findViewById(R.id.statusD4);
        subTextD5 = (TextView)findViewById(R.id.statusD5);
        subTextD6 = (TextView)findViewById(R.id.statusD6);
        subTextD7 = (TextView)findViewById(R.id.statusD7);
        subTextD8 = (TextView)findViewById(R.id.statusD8);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();

        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                //subTextD1.setText(new String(message.getPayload()));
                Status = new String(message.getPayload());
                if(Status.equals("#relay1on")){
                    subTextD1.setText("D1 On");
                }
                if(Status.equals("#relay1off")){
                    subTextD1.setText("D1 Off");
                }
                if(Status.equals("#relay2on")){
                    subTextD2.setText("D2 On");
                }
                if(Status.equals("#relay2off")){
                    subTextD2.setText("D2 Off");
                }
                if(Status.equals("#relay3on")){
                    subTextD3.setText("D3 On");
                }
                if(Status.equals("#relay3off")){
                    subTextD3.setText("D3 Off");
                }
                if(Status.equals("#relay4on")){
                    subTextD4.setText("D4 On");
                }
                if(Status.equals("#relay4off")){
                    subTextD4.setText("D4 Off");
                }
                if(Status.equals("#relay5on")){
                    subTextD5.setText("D5 On");
                }
                if(Status.equals("#relay5off")){
                    subTextD5.setText("D5 Off");
                }
                if(Status.equals("#relay6on")){
                    subTextD6.setText("D6 On");
                }
                if(Status.equals("#relay6off")){
                    subTextD6.setText("D6 Off");
                }
                if(Status.equals("#relay7on")){
                    subTextD7.setText("D7 On");
                }
                if(Status.equals("#relay7off")){
                    subTextD7.setText("D7 Off");
                }
                if(Status.equals("#relay8on")){
                    subTextD8.setText("D8 On");
                }
                if(Status.equals("#relay8off")){
                    subTextD8.setText("D8 Off");
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        /*
        subText = (TextView) findViewById(R.id.status);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subText.setText("D1 On");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subText.setText("D1 Off");
            }
        });
        */
    }

    public void d1_on(View v){
        String topic = pubString;
        String message = "#relay1on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD1.setText("D1 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d1_off(View v){
        String topic = pubString;
        String message = "#relay1off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD1.setText("D1 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d2_on(View v){
        String topic = pubString;
        String message = "#relay2on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD2.setText("D2 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d2_off(View v){
        String topic = pubString;
        String message = "#relay2off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD2.setText("D2 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d3_on(View v){
        String topic = pubString;
        String message = "#relay3on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD3.setText("D3 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d3_off(View v){
        String topic = pubString;
        String message = "#relay3off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD3.setText("D3 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d4_on(View v){
        String topic = pubString;
        String message = "#relay4on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD4.setText("D4 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d4_off(View v){
        String topic = pubString;
        String message = "#relay4off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD4.setText("D4 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d5_on(View v){
        String topic = pubString;
        String message = "#relay5on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD5.setText("D5 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d5_off(View v){
        String topic = pubString;
        String message = "#relay5off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD5.setText("D5 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d6_on(View v){
        String topic = pubString;
        String message = "#relay6on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD6.setText("D6 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d6_off(View v){
        String topic = pubString;
        String message = "#relay6off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD6.setText("D6 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d7_on(View v){
        String topic = pubString;
        String message = "#relay7on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD7.setText("D7 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d7_off(View v){
        String topic = pubString;
        String message = "#relay7off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD7.setText("D7 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d8_on(View v){
        String topic = pubString;
        String message = "#relay8on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD8.setText("D8 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d8_off(View v){
        String topic = pubString;
        String message = "#relay8off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD8.setText("D8 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void setSubscription(){
        try{
            client.subscribe(subString,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}
