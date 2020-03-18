package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */

public class Company {

    /**
     * Datas : [{"Id":1,"CompanyName":"高柏诗","KingDeeConnection":null,"CreateTime":null},{"Id":2,"CompanyName":"柏瑞美","KingDeeConnection":null,"CreateTime":null},{"Id":5,"CompanyName":"深圳办事处（柏瑞美）","KingDeeConnection":null,"CreateTime":null},{"Id":6,"CompanyName":"一代柏瑞美（日本）","KingDeeConnection":null,"CreateTime":null},{"Id":8,"CompanyName":"米约","KingDeeConnection":null,"CreateTime":null},{"Id":9,"CompanyName":"配料部","KingDeeConnection":null,"CreateTime":null},{"Id":12,"CompanyName":"维纳卡","KingDeeConnection":null,"CreateTime":null},{"Id":14,"CompanyName":"柏蕊诗","KingDeeConnection":null,"CreateTime":null},{"Id":15,"CompanyName":"0","KingDeeConnection":null,"CreateTime":null},{"Id":16,"CompanyName":"1","KingDeeConnection":null,"CreateTime":null},{"Id":17,"CompanyName":"2","KingDeeConnection":null,"CreateTime":null},{"Id":18,"CompanyName":"3","KingDeeConnection":null,"CreateTime":null},{"Id":19,"CompanyName":"高柏诗电商","KingDeeConnection":null,"CreateTime":null},{"Id":20,"CompanyName":"柏瑞美电商","KingDeeConnection":null,"CreateTime":null}]
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

    public List<DatasBean> getDatas() {
        return Datas;
    }

    public void setDatas(List<DatasBean> Datas) {
        this.Datas = Datas;
    }

    public static class DatasBean {
        /**
         * Id : 1
         * CompanyName : 高柏诗
         * KingDeeConnection : null
         * CreateTime : null
         */

        private int Id;
        private String CompanyName;
        private Object KingDeeConnection;
        private Object CreateTime;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public Object getKingDeeConnection() {
            return KingDeeConnection;
        }

        public void setKingDeeConnection(Object KingDeeConnection) {
            this.KingDeeConnection = KingDeeConnection;
        }

        public Object getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(Object CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
