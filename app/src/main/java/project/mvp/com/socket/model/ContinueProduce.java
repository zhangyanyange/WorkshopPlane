package project.mvp.com.socket.model;

public class ContinueProduce {

    /**
     * Datas : {"BillNo":"PRC2019091602","Brand":"2","CurrentNumber":0,"Unit":"个","Remark":""}
     * Success : true
     * Message : Success
     * ErrorInfo : null
     * Code : 200
     */

    private DatasBean Datas;
    private boolean Success;
    private String Message;
    private String ErrorInfo;
    private int Code;

    public DatasBean getDatas() {
        return Datas;
    }

    public void setDatas(DatasBean Datas) {
        this.Datas = Datas;
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

    public static class DatasBean {
        /**
         * BillNo : PRC2019091602
         * Brand : 2
         * CurrentNumber : 0
         * Unit : 个
         * Remark :
         */

        private String BillNo;
        private String Brand;
        private int CurrentNumber;
        private String Unit;
        private String Remark;

        public String getBillNo() {
            return BillNo;
        }

        public void setBillNo(String BillNo) {
            this.BillNo = BillNo;
        }

        public String getBrand() {
            return Brand;
        }

        public void setBrand(String Brand) {
            this.Brand = Brand;
        }

        public int getCurrentNumber() {
            return CurrentNumber;
        }

        public void setCurrentNumber(int CurrentNumber) {
            this.CurrentNumber = CurrentNumber;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }
    }
}
