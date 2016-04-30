package com.example.blade.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.blade.myapplication.componement.WifiAdmin;
import com.example.blade.myapplication.componement.WifiApAdmin;

public class JudgeActivity extends Activity {


    public static final String TAG = "JudgeActivity";

    //检测网络连接状态
    private ConnectivityManager manager;

    private LinearLayout ll;

    private Button clickBtn;

    private Button setUpBtn;

    private Context mContext = null;

    private WifiAdmin mWifiAdmin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        mContext = this;
        clickBtn = (Button) findViewById(R.id.connectBtn);

        clickBtn = (Button) findViewById(R.id.setUppBtn);


        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWifiAdmin = new WifiAdmin(mContext) {

                    @Override
                    public void myUnregisterReceiver(BroadcastReceiver receiver) {
                        // TODO Auto-generated method stub
                        JudgeActivity.this.unregisterReceiver(receiver);
                    }

                    @Override
                    public Intent myRegisterReceiver(BroadcastReceiver receiver,
                                                     IntentFilter filter) {
                        // TODO Auto-generated method stub
                        JudgeActivity.this.registerReceiver(receiver, filter);
                        return null;
                    }

                    @Override
                    public void onNotifyWifiConnected() {
                        // TODO Auto-generated method stub
                        Log.v(TAG, "have connected success!");
                        Log.v(TAG, "###############################");
                    }

                    @Override
                    public void onNotifyWifiConnectFailed() {
                        // TODO Auto-generated method stub
                        Log.v(TAG, "have connected failed!");
                        Log.v(TAG, "###############################");
                    }
                };
                mWifiAdmin.openWifi();
                mWifiAdmin.addNetwork(mWifiAdmin.createWifiInfo("YOU_WIFI", "MM123456", WifiAdmin.TYPE_WPA));

            }

        });

        clickBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            // TODO Auto-generated method stub
                                            WifiApAdmin wifiAp = new WifiApAdmin(mContext);
                                            wifiAp.startWifiAp("\"HotSpot\"", "hhhhhh123");

                                        }
                                    }
        );
    }

    /**
     *
     */
    public void createNetWork(){

        String tip = "无网络连接";
        if(this.isWiFiConnecting()){
            //TODO 直接启动service
           tip = "当前正在连接WiFi";
        }
        Toast.makeText(getApplicationContext(),
                tip, Toast.LENGTH_LONG).show();
    }


    public String getMobilePhoneName(){

        return null;
    }


    /***
     * 否是WiFi连接
     * @return
     */
    public boolean isWiFiConnecting() {

        boolean wifiConnected = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            //mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return wifiConnected;
    }


    /**
     * 检测网络是否连接
     * @return
     */
    private boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            setNetwork();
        } else {
            isNetworkAvailable();
        }

        return flag;
    }


    /**
     * 网络未连接时，调用设置方法
     */
    private void setNetwork(){
        Toast.makeText(this, "wifi is closed!", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     */
    private void isNetworkAvailable(){

        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING){
            Toast.makeText(this, "Network available! gprs", Toast.LENGTH_SHORT).show();
            setNetwork();
        }
        //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
        if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING){
            Toast.makeText(this, "Network available! wifi", Toast.LENGTH_SHORT).show();
            loadAdmob();
        }

    }

    /**
     * 在wifi状态下 加载admob广告
     */
    private void loadAdmob(){


    }

}
