package project.mvp.com.socket.model;

import java.text.SimpleDateFormat;

public class LogBean {
    public String mTime;
    public String mLog;
    public String mWho;
    public int mType;

    public LogBean(long time, String log,int Type) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        mTime = format.format(time);
        mLog = log;
        mType=Type;
    }
}