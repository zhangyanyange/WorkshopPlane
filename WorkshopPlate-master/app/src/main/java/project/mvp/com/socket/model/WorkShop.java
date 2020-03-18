package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/13.
 * 类描述
 */

public class WorkShop {

    /**
     * Datas : [{"WorkShopId":3497,"WorkShopName":"总经办"},{"WorkShopId":3499,"WorkShopName":"财务部"},{"WorkShopId":3500,"WorkShopName":"销售部"},{"WorkShopId":3501,"WorkShopName":"市场部"},{"WorkShopId":3502,"WorkShopName":"行政部"},{"WorkShopId":3503,"WorkShopName":"采购部"},{"WorkShopId":3504,"WorkShopName":"品控部"},{"WorkShopId":3505,"WorkShopName":"设计部"},{"WorkShopId":3506,"WorkShopName":"开发部"},{"WorkShopId":3507,"WorkShopName":"材料仓"},{"WorkShopId":3508,"WorkShopName":"培训部"},{"WorkShopId":3509,"WorkShopName":"生产车间"},{"WorkShopId":3511,"WorkShopName":"珠海亚拓"},{"WorkShopId":3532,"WorkShopName":"江门信和"},{"WorkShopId":3533,"WorkShopName":"广州森然"},{"WorkShopId":3534,"WorkShopName":"上海欧润"},{"WorkShopId":3535,"WorkShopName":"中山真那"},{"WorkShopId":3536,"WorkShopName":"北京东彩"},{"WorkShopId":3537,"WorkShopName":"广州兴胜"},{"WorkShopId":3538,"WorkShopName":"昆山缘鑫"},{"WorkShopId":4235,"WorkShopName":"浙江华盛"},{"WorkShopId":4237,"WorkShopName":"广州乐雅"},{"WorkShopId":4388,"WorkShopName":"广东碧茜"},{"WorkShopId":4460,"WorkShopName":"线上推广"},{"WorkShopId":4461,"WorkShopName":"品牌推广"},{"WorkShopId":4462,"WorkShopName":"退货部"},{"WorkShopId":4463,"WorkShopName":"商务部"},{"WorkShopId":4707,"WorkShopName":"广州新雅"},{"WorkShopId":4751,"WorkShopName":"陈列道具设计"},{"WorkShopId":4752,"WorkShopName":"促销推广部"},{"WorkShopId":4753,"WorkShopName":"成品仓"},{"WorkShopId":4755,"WorkShopName":"生产计划部"},{"WorkShopId":4757,"WorkShopName":"沈阳办"},{"WorkShopId":4758,"WorkShopName":"大客户部"},{"WorkShopId":4999,"WorkShopName":"珠海中远"},{"WorkShopId":5045,"WorkShopName":"广州威顿"},{"WorkShopId":5184,"WorkShopName":"中山海辉"},{"WorkShopId":5880,"WorkShopName":"上海永力"},{"WorkShopId":6846,"WorkShopName":"动销部"},{"WorkShopId":7290,"WorkShopName":"广州顺生"},{"WorkShopId":7426,"WorkShopName":"中山市嘉德精密包装有限公司"},{"WorkShopId":7442,"WorkShopName":"江苏豪鑫"}]
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
         * WorkShopId : 3497
         * WorkShopName : 总经办
         */

        private int WorkShopId;
        private String WorkShopName;

        public int getWorkShopId() {
            return WorkShopId;
        }

        public void setWorkShopId(int WorkShopId) {
            this.WorkShopId = WorkShopId;
        }

        public String getWorkShopName() {
            return WorkShopName;
        }

        public void setWorkShopName(String WorkShopName) {
            this.WorkShopName = WorkShopName;
        }
    }
}
