package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */

public class ProductTask {

    /**
     * Datas : [{"FicmobillNo":"PRC2017020501","FproductName":"韩式免削眉笔1#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1301","FqtyDecimal":0},{"FicmobillNo":"PRC2017020501","FproductName":"韩式免削眉笔1#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1301","FqtyDecimal":5},{"FicmobillNo":"PRC2017020502","FproductName":"韩式免削眉笔1#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1301","FqtyDecimal":0},{"FicmobillNo":"PRC2017020502","FproductName":"韩式免削眉笔1#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1301","FqtyDecimal":5},{"FicmobillNo":"PRC2017020503","FproductName":"韩式免削眉笔2#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1302","FqtyDecimal":0},{"FicmobillNo":"PRC2017020503","FproductName":"韩式免削眉笔2#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1302","FqtyDecimal":5},{"FicmobillNo":"PRC2017020504","FproductName":"韩式免削眉笔2#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1302","FqtyDecimal":0},{"FicmobillNo":"PRC2017020504","FproductName":"韩式免削眉笔2#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1302","FqtyDecimal":5},{"FicmobillNo":"PRC2017020505","FproductName":"韩式免削眉笔3#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1303","FqtyDecimal":0},{"FicmobillNo":"PRC2017020505","FproductName":"韩式免削眉笔3#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1303","FqtyDecimal":5},{"FicmobillNo":"PRC2017020506","FproductName":"韩式免削眉笔3#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1303","FqtyDecimal":0},{"FicmobillNo":"PRC2017020506","FproductName":"韩式免削眉笔3#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1303","FqtyDecimal":5},{"FicmobillNo":"PRC2017020507","FproductName":"韩式免削眉笔4#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1304","FqtyDecimal":0},{"FicmobillNo":"PRC2017020507","FproductName":"韩式免削眉笔4#","FproductModel":"PR-EL13","GoodNumber":"2503.05.1304","FqtyDecimal":5},{"FicmobillNo":"PRC2017020508","FproductName":"韩式免削眉笔4#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1304","FqtyDecimal":0},{"FicmobillNo":"PRC2017020508","FproductName":"韩式免削眉笔4#试用装","FproductModel":"PR-EL13","GoodNumber":"2603.05.1304","FqtyDecimal":5},{"FicmobillNo":"PRC2017020509","FproductName":"持色眼彩笔1#","FproductModel":"PR-EL16","GoodNumber":"2503.03.1601","FqtyDecimal":0},{"FicmobillNo":"PRC2017020509","FproductName":"持色眼彩笔1#","FproductModel":"PR-EL16","GoodNumber":"2503.03.1601","FqtyDecimal":5},{"FicmobillNo":"PRC2017020510","FproductName":"持色眼彩笔1#试用装","FproductModel":"PR-EL16","GoodNumber":"2603.03.1601","FqtyDecimal":0},{"FicmobillNo":"PRC2017020510","FproductName":"持色眼彩笔1#试用装","FproductModel":"PR-EL16","GoodNumber":"2603.03.1601","FqtyDecimal":5}]
     * PageIndex : 1
     * Total : 207
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
         * FicmobillNo : PRC2017020501
         * FproductName : 韩式免削眉笔1#
         * FproductModel : PR-EL13
         * GoodNumber : 2503.05.1301
         * FqtyDecimal : 0
         */

        private String FicmobillNo;
        private String FproductName;
        private String FproductModel;
        private String GoodNumber;
        private float FqtyDecimal;

        public String getFicmobillNo() {
            return FicmobillNo;
        }

        public void setFicmobillNo(String FicmobillNo) {
            this.FicmobillNo = FicmobillNo;
        }

        public String getFproductName() {
            return FproductName;
        }

        public void setFproductName(String FproductName) {
            this.FproductName = FproductName;
        }

        public String getFproductModel() {
            return FproductModel;
        }

        public void setFproductModel(String FproductModel) {
            this.FproductModel = FproductModel;
        }

        public String getGoodNumber() {
            return GoodNumber;
        }

        public void setGoodNumber(String GoodNumber) {
            this.GoodNumber = GoodNumber;
        }

        public float getFqtyDecimal() {
            return FqtyDecimal;
        }

        public void setFqtyDecimal(float FqtyDecimal) {
            this.FqtyDecimal = FqtyDecimal;
        }
    }
}
