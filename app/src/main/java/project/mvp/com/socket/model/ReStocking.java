package project.mvp.com.socket.model;

import java.util.List;

public class ReStocking {

    /**
     * Datas : [{"BillNo":"PRC2019091602","filename":"log/FailedData/instore_failed_PRC2019091602_20191105054523.526.dat"}]
     * PageIndex : 1
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
         * BillNo : PRC2019091602
         * filename : log/FailedData/instore_failed_PRC2019091602_20191105054523.526.dat
         */

        private String BillNo;
        private String FileName;

        public String getBillNo() {
            return BillNo;
        }

        public void setBillNo(String BillNo) {
            this.BillNo = BillNo;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFilename(String filename) {
            this.FileName = filename;
        }
    }
}
