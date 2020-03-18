package project.mvp.com.socket.model;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class MessageEvent {
    public int cpmpanyId;
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCpmpanyId() {
        return cpmpanyId;
    }

    public void setCpmpanyId(int cpmpanyId) {
        this.cpmpanyId = cpmpanyId;
    }
}
