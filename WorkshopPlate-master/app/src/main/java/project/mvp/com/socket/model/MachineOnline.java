package project.mvp.com.socket.model;

/**
 * 创建人 张岩
 * 时间   2019/5/31.
 * 类描述
 */

public class MachineOnline {

    /**
     * Success : false
     * Message : 机器不在线
     * ErrorInfo : Error
     * Code : 405
     */

    private boolean Success;
    private String Message;
    private String ErrorInfo;
    private int Code;

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

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String ErrorInfo) {
        this.ErrorInfo = ErrorInfo;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}
