package com.yufs.testsocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.yufs.testsocket.tcp.TcpManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.et_port)
    EditText etPort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_connect)
    public void onViewClicked() {
        String ip = "192.168.1.104";
        String port = "8080";
        TcpManager.getInstance().initSocket(ip, port).setOnSocketStatusListener(new TcpManager.OnSocketStatusListener() {
            @Override
            public void onConnectSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, SendActivity.class));
                    }
                });
            }
        });
//        TcpManager.getInstance().setOnReceiveDataListener(str -> Log.e("yufs","这是收到的消息："+str));
    }

}
