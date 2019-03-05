package com.yufs.testsocket;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.yufs.testsocket.tcp.TcpManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendActivity extends Activity {
    @BindView(R.id.et_send)
    EditText etSend;
    @BindView(R.id.et_receive)
    EditText etReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        TcpManager.getInstance().setOnReceiveDataListener(new TcpManager.OnReceiveDataListener() {
            @Override
            public void onReceiveData(String str) {
                String receiveData = etReceive.getText().toString();
                StringBuffer sb=new StringBuffer(receiveData);
                sb.append(str).append(" ");
                etReceive.setText(sb.toString());
            }
        });
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String sendData = etSend.getText().toString();
        if(TextUtils.isEmpty(sendData)){
            return;
        }
        TcpManager.getInstance().sendData(sendData);
    }


}
