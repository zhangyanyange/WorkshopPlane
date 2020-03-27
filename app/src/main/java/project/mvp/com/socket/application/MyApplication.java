package project.mvp.com.socket.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zy2 on 2016/12/26.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThreadId;


    private static MyApplication instance;



    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        //上下文
        mContext = getApplicationContext();
        //得到主线程中的handler
        mHandler = new Handler();
        //得到这个主线程中的线程id
        mMainThreadId = Process.myTid();

        initOkHttpUtils();


    }
    public static MyApplication getInstance(){
        if(instance==null) {
            instance = new MyApplication();
        }
        return instance;
    }
    public void initOkHttpUtils(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1,TimeUnit.HOURS)
                .readTimeout(1,TimeUnit.HOURS)
                .build();

        OkHttpUtils.getInstance(okHttpClient);//持久化存储cookie
    }

    public static String baseUrl="https://workshopapi.gopas.com.cn/";//http://192.168.12.247:8085/
}
