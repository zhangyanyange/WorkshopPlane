package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/5/31.
 * 类描述
 */

public class Scanning {

    /**
     * Datas : ["COM8"]
     * PageIndex : 0
     * Total : 1
     * Success : true
     * Message : Success
     * ErrorInfo : null
     * Code : 200
     */

    private int PageIndex;
    private int Total;
    private boolean Success;
    private String Message;
    private Object ErrorInfo;
    private int Code;
    private List<String> Datas;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Object getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(Object ErrorInfo) {
        this.ErrorInfo = ErrorInfo;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public List<String> getDatas() {
        return Datas;
    }

    public void setDatas(List<String> Datas) {
        this.Datas = Datas;
    }
}
