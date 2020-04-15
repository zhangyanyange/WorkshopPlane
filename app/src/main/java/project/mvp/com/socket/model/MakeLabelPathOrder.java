package project.mvp.com.socket.model;

import java.util.List;

public class MakeLabelPathOrder {

    /**
     * Datas : [{"Id":"1120fe9f-4716-45e9-a088-3739d46def55","OrderNo":"PRC2019112207","CreateTime":"2020-04-10T08:47:42.957","BatchNo":"GP200401A","GoodNumbers":"323100015","Name":""},{"Id":"9defea97-2e33-4e60-96af-38fd34940638","OrderNo":"PRC2019112201","CreateTime":"2020-04-08T11:21:33.55","BatchNo":"GP200327A","GoodNumbers":"323100016","Name":""},{"Id":"4b269343-b0fb-410f-b409-54874dc88d66","OrderNo":"PRC2019112207","CreateTime":"2020-04-09T14:34:57.297","BatchNo":"GP200401A","GoodNumbers":"323100015","Name":""},{"Id":"ad721f9d-a742-4271-b6d5-a619524736f5","OrderNo":"WD2004080001","CreateTime":"2020-04-08T13:58:23.323","BatchNo":"GP200408A","GoodNumbers":"312070008","Name":""},{"Id":"c6ae707b-0579-48b8-ac83-e7faf86c4a72","OrderNo":"PRC2019112201","CreateTime":"2020-04-08T11:33:48.69","BatchNo":"GP200327A","GoodNumbers":"323100016","Name":""}]
     * PageIndex : 1
     * Total : 5
     * Success : true
     * Message : Success
     * Code : 200
     */

    private int PageIndex;
    private int Total;
    private boolean Success;
    private String Message;
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
         * Id : 1120fe9f-4716-45e9-a088-3739d46def55
         * OrderNo : PRC2019112207
         * CreateTime : 2020-04-10T08:47:42.957
         * BatchNo : GP200401A
         * GoodNumbers : 323100015
         * Name :
         */

        private String Id;
        private String OrderNo;
        private String CreateTime;
        private String BatchNo;
        private String GoodNumbers;
        private String Name;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getBatchNo() {
            return BatchNo;
        }

        public void setBatchNo(String BatchNo) {
            this.BatchNo = BatchNo;
        }

        public String getGoodNumbers() {
            return GoodNumbers;
        }

        public void setGoodNumbers(String GoodNumbers) {
            this.GoodNumbers = GoodNumbers;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
