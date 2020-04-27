package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/6.
 * 类描述
 */

public class Configure {

    /**
     * machineId : 0
     * scanName : string
     * ficmobillNo : string
     * goodNumber : string
     * fproductName : string
     * fproductModel : string
     * remark : string
     * unitInfos : [{"id":"string","printName":"string"}]
     */

    private int machineId;
    private String scanName;
    private String ficmobillNo;
    private String goodNumber;
    private String fproductName;
    private String fproductModel;
    private String remark;
    private String storeId;
    private String storePlaceId;
    private int companyId;
    private int fworkShopId;
    private String batchNo;
    private String configType;

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getFworkShopId() {
        return fworkShopId;
    }

    public void setFworkShopId(int fworkShopId) {
        this.fworkShopId = fworkShopId;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    private List<UnitInfosBean> unitInfos;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getScanName() {
        return scanName;
    }

    public void setScanName(String scanName) {
        this.scanName = scanName;
    }

    public String getFicmobillNo() {
        return ficmobillNo;
    }

    public void setFicmobillNo(String ficmobillNo) {
        this.ficmobillNo = ficmobillNo;
    }

    public String getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getFproductName() {
        return fproductName;
    }

    public void setFproductName(String fproductName) {
        this.fproductName = fproductName;
    }

    public String getFproductModel() {
        return fproductModel;
    }

    public void setFproductModel(String fproductModel) {
        this.fproductModel = fproductModel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<UnitInfosBean> getUnitInfos() {
        return unitInfos;
    }

    public void setUnitInfos(List<UnitInfosBean> unitInfos) {
        this.unitInfos = unitInfos;
    }


    public static class UnitInfosBean {
        /**
         * id : string
         * printName : string
         */

        private String id;
        private String printName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPrintName() {
            return printName;
        }

        public void setPrintName(String printName) {
            this.printName = printName;
        }
    }
}
