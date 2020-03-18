package project.mvp.com.socket.model;

public class Print {

    /**
     * pname : string
     * mark : string
     * packageInfo : {"id":"string","batchCode":"string","goodNumbers":"string","goodName":"string","modelCode":"string","sacler":0,"number":0,"createDate":"2020-01-15T01:50:06.726Z","storeId":"string","workPalceId":"string","sourceBillNo":"string","unitName":"string","description":"string"}
     */

    private String pname;
    private String mark;
    private PackageInfoBean packageInfo;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public PackageInfoBean getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfoBean packageInfo) {
        this.packageInfo = packageInfo;
    }

    public static class PackageInfoBean {
        /**
         * id : string
         * batchCode : string
         * goodNumbers : string
         * goodName : string
         * modelCode : string
         * sacler : 0
         * number : 0
         * createDate : 2020-01-15T01:50:06.726Z
         * storeId : string
         * workPalceId : string
         * sourceBillNo : string
         * unitName : string
         * description : string
         */

        private String id;
        private String batchCode;
        private String goodNumbers;
        private String goodName;
        private String modelCode;
        private int sacler;
        private int number;
        private String createDate;
        private String storeId;
        private String workPalceId;
        private String sourceBillNo;
        private String unitName;
        private String description;

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

        public String getModelCode() {
            return modelCode;
        }

        public void setModelCode(String modelCode) {
            this.modelCode = modelCode;
        }

        public int getSacler() {
            return sacler;
        }

        public void setSacler(int sacler) {
            this.sacler = sacler;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
