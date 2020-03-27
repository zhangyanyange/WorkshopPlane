package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/5/31.
 * 类描述
 */

public class Machine {

    /**
     * Datas : [{"Id":0,"MachineName":"DESKTOP-0NAK5JO","MachineIp":"127.0.0.1","CreateTime":"2019-05-27T14:28:54.6","UpdateTime":null,"Remark":"123ssaas","CreateUserId":1,"UpdateUserId":null},{"Id":6,"MachineName":"DESKTOP-0NAK5JO","MachineIp":"192.168.20.181","CreateTime":"2019-05-27T17:34:31.58","UpdateTime":null,"Remark":"123","CreateUserId":1,"UpdateUserId":null}]
     * PageIndex : 0
     * Total : 2
     * Success : true
     * Message : Success
     * ErrorInfo : null
     * Code : 200
     */

    private int PageIndex;
    private int Total;
    private boolean Success;
    private String Message;
    private String ErrorInfo;
    private int Code;
    private List<DatasBean> Datas;

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

    public List<DatasBean> getDatas() {
        return Datas;
    }

    public void setDatas(List<DatasBean> Datas) {
        this.Datas = Datas;
    }

    public static class DatasBean {
        /**
         * Id : 0
         * MachineName : DESKTOP-0NAK5JO
         * MachineIp : 127.0.0.1
         * CreateTime : 2019-05-27T14:28:54.6
         * UpdateTime : null
         * Remark : 123ssaas
         * CreateUserId : 1
         * UpdateUserId : null
         */

        private int Id;
        private String MachineName;
        private String MachineIp;
        private String CreateTime;
        private Object UpdateTime;
        private String Remark;
        private int CreateUserId;
        private Object UpdateUserId;
        private boolean checkStatus;

        public boolean isCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(boolean checkStatus) {
            this.checkStatus = checkStatus;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getMachineName() {
            return MachineName;
        }

        public void setMachineName(String MachineName) {
            this.MachineName = MachineName;
        }

        public String getMachineIp() {
            return MachineIp;
        }

        public void setMachineIp(String MachineIp) {
            this.MachineIp = MachineIp;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public Object getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(Object UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public int getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(int CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        public Object getUpdateUserId() {
            return UpdateUserId;
        }

        public void setUpdateUserId(Object UpdateUserId) {
            this.UpdateUserId = UpdateUserId;
        }
    }
}
