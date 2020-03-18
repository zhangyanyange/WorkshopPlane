package project.mvp.com.socket.model;

/**
 * 创建人 张岩
 * 时间   2019/6/10.
 * 类描述
 */

public class ConfigureOptions {

    /**
     * Success : true
     * Message : Success
     * ErrorInfo : null
     * Code : 200
     */

    private boolean Success;
    private String Message;
    private Object ErrorInfo;
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
}
