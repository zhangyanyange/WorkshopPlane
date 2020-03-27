package project.mvp.com.socket.model;

/**
 * 创建人 张岩
 * 时间   2019/7/18.
 * 类描述
 */

public class LogInfo {

    /**
     * CType : Error
     * Datas : 12：二维码设备遗漏第12商品
     * SendTime : 2019-07-18 09:33:28.979
     */

    private String CType;
    private String Datas;
    private String SendTime;

    public String getCType() {
        return CType;
    }

    public void setCType(String CType) {
        this.CType = CType;
    }

    public String getDatas() {
        return Datas;
    }

    public void setDatas(String Datas) {
        this.Datas = Datas;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String SendTime) {
        this.SendTime = SendTime;
    }
}
