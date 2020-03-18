package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/11.
 * 类描述
 */
public class Store {

    /**
     * Datas : [{"StoreId":"4703","StoreName":"半成品仓"},{"StoreId":"5976","StoreName":"碧茜仓"},{"StoreId":"279","StoreName":"不良品仓"},{"StoreId":"275","StoreName":"材料仓"},{"StoreId":"4236","StoreName":"残次品仓"},{"StoreId":"276","StoreName":"成品仓"},{"StoreId":"6147","StoreName":"大前台试用装"},{"StoreId":"277","StoreName":"呆滞品仓"},{"StoreId":"278","StoreName":"待检仓"},{"StoreId":"3674","StoreName":"电商仓"},{"StoreId":"3943","StoreName":"柜台仓"},{"StoreId":"7546","StoreName":"深圳办仓"},{"StoreId":"4357","StoreName":"生产损耗仓"},{"StoreId":"274","StoreName":"原料仓"}]
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
         * StoreId : 4703
         * StoreName : 半成品仓
         */

        private String StoreId;
        private String StoreName;

        public String getStoreId() {
            return StoreId;
        }

        public void setStoreId(String StoreId) {
            this.StoreId = StoreId;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }
    }
}
