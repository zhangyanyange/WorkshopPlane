package project.mvp.com.socket.model;

import java.util.List;

public class MakeLabelDetail {

    /**
     * Datas : [{"description":"string","id":"4d3bf0ae-434c-4a21-9830-1855e4f8fb75","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-3个-2","isTail":true},{"description":"string","id":"1e97bd4a-2bd9-451a-8112-1c1bacbc86a2","batchCode":"GP200327A","modelCode":"","sacler":3,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"微感箱","goodNumbers":"323100016","goodName":"string","number":3,"unitId":"222c7952-7f3f-4c4f-a3af-b04816aec090","storeId":"1001","storePlaceId":"","mark":"微感箱-1","isTail":true},{"description":"string","id":"401c8188-35ae-463e-9b90-286c99bc2224","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-2","isTail":true},{"description":"string","id":"46944dbd-89e4-4145-b459-316c0697049c","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"尾箱测试盒-1个-2","isTail":true},{"description":"string","id":"9731cb89-8cec-4a8e-9f1d-3ac3cf0618d1","batchCode":"GP200327A","modelCode":"","sacler":3,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"微感箱","goodNumbers":"323100016","goodName":"string","number":3,"unitId":"222c7952-7f3f-4c4f-a3af-b04816aec090","storeId":"1001","storePlaceId":"","mark":"微感箱-2","isTail":true},{"description":"string","id":"a1dc8eb2-ddfc-46b9-b198-3b84f7196abf","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-1个-2","isTail":true},{"description":"string","id":"0a3f5b7a-bcec-447f-a8a9-40374889ab8f","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"尾箱个-1","isTail":true},{"description":"string","id":"951f2a53-862f-40e1-870d-51c226e19756","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-1","isTail":true},{"description":"string","id":"d3bc7454-2852-42fc-991a-5a6db923c15b","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-3测试盒-2","isTail":true},{"description":"string","id":"2332c1b8-2f9c-4a61-ab97-5d822aaa384e","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"尾箱测试盒-1个-1","isTail":true},{"description":"string","id":"378597e7-7fa5-41f0-93e9-6d74cda4c627","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-2个-1","isTail":true},{"description":"string","id":"dbe8b0be-014e-4352-981b-73d11e667aba","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-3","isTail":true},{"description":"string","id":"51b992c3-4c31-4511-aa96-7575b146e5de","batchCode":"GP200327A","modelCode":"","sacler":3,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"微感箱","goodNumbers":"323100016","goodName":"string","number":3,"unitId":"222c7952-7f3f-4c4f-a3af-b04816aec090","storeId":"1001","storePlaceId":"","mark":"尾箱","isTail":true},{"description":"string","id":"70859827-2fbf-47e8-a120-7c3e14f620dc","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-1个-1","isTail":true},{"description":"string","id":"f5767316-71e1-4364-9e84-8076011190c5","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-1个-2","isTail":true},{"description":"string","id":"267e04ca-a1ea-4b16-8b96-823708cb3ef7","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-3个-2","isTail":true},{"description":"string","id":"c9915980-0505-4cdc-a9ef-8295c4f905b5","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-1","isTail":true},{"description":"string","id":"96778263-8a03-4a4a-b4e1-93bc1c45ade5","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"尾箱测试盒-1","isTail":true},{"description":"string","id":"db764532-4dd8-4b27-af5a-a8e0cbb340e4","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-3","isTail":true},{"description":"string","id":"2a1c7c7f-c09c-40e9-950a-ae98cbfdee6f","batchCode":"GP200327A","modelCode":"","sacler":2,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"测试盒","goodNumbers":"323100016","goodName":"string","number":2,"unitId":"53752a36-cdaf-42fc-98c2-cb20d7b5c147","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-2","isTail":true},{"description":"string","id":"1ac02a87-117e-46e7-9f1e-c157486f8298","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-3个-1","isTail":true},{"description":"string","id":"83f8eb74-e145-4e9e-8fff-cdc07121c63d","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-2个-2","isTail":true},{"description":"string","id":"4af4040a-f0f3-44f8-b4df-d9a41984418e","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-2个-1","isTail":true},{"description":"string","id":"37b48cc8-21bc-46df-86dd-db09c0a46968","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-1个-1","isTail":true},{"description":"string","id":"bc56b480-f8e9-4fc8-bcad-dd376ee77aaf","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-1测试盒-2个-2","isTail":true},{"description":"string","id":"be6726fd-bf38-4c37-b747-f6f95214a93e","batchCode":"GP200327A","modelCode":"","sacler":1,"createDate":"2020-04-08 11:21:33","workPalceId":"","sourceBillNo":"PRC2019112201","unitName":"个","goodNumbers":"323100016","goodName":"string","number":1,"unitId":"13f52ef8-667a-4749-9df2-115c68829bab","storeId":"1001","storePlaceId":"","mark":"微感箱-2测试盒-3个-1","isTail":true}]
     * PageIndex : 1
     * Total : 26
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
         * description : string
         * id : 4d3bf0ae-434c-4a21-9830-1855e4f8fb75
         * batchCode : GP200327A
         * modelCode :
         * sacler : 1.0
         * createDate : 2020-04-08 11:21:33
         * workPalceId :
         * sourceBillNo : PRC2019112201
         * unitName : 个
         * goodNumbers : 323100016
         * goodName : string
         * number : 1.0
         * unitId : 13f52ef8-667a-4749-9df2-115c68829bab
         * storeId : 1001
         * storePlaceId :
         * mark : 微感箱-2测试盒-3个-2
         * isTail : true
         */

        private String description;
        private String id;
        private String batchCode;
        private String modelCode;
        private double sacler;
        private String createDate;
        private String workPalceId;
        private String sourceBillNo;
        private String unitName;
        private String goodNumbers;
        private String goodName;
        private double number;
        private String unitId;
        private String storeId;
        private String storePlaceId;
        private String mark;
        private boolean isTail;
        private String pname;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public String getModelCode() {
            return modelCode;
        }

        public void setModelCode(String modelCode) {
            this.modelCode = modelCode;
        }

        public double getSacler() {
            return sacler;
        }

        public void setSacler(double sacler) {
            this.sacler = sacler;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getWorkPalceId() {
            return workPalceId;
        }

        public void setWorkPalceId(String workPalceId) {
            this.workPalceId = workPalceId;
        }

        public String getSourceBillNo() {
            return sourceBillNo;
        }

        public void setSourceBillNo(String sourceBillNo) {
            this.sourceBillNo = sourceBillNo;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getGoodNumbers() {
            return goodNumbers;
        }

        public void setGoodNumbers(String goodNumbers) {
            this.goodNumbers = goodNumbers;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStorePlaceId() {
            return storePlaceId;
        }

        public void setStorePlaceId(String storePlaceId) {
            this.storePlaceId = storePlaceId;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public boolean isIsTail() {
            return isTail;
        }

        public void setIsTail(boolean isTail) {
            this.isTail = isTail;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }
    }
}
