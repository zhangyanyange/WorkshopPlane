package project.mvp.com.socket.model;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/8/1.
 * 类描述
 */

public class Unit {

    /**
     * Datas : [{"Id":"4785e16e-577b-48c8-9199-0d67c7991033","Pid":null,"UnitName":"千克","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"32e254d2-bc64-4010-8a23-2a3661b5777e","Pid":null,"UnitName":"套","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"5286ff3e-fcee-48d0-8a70-2ed6185a22ee","Pid":null,"UnitName":"张","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"e07f0bad-d1b3-48ee-94c1-4f6ea054a227","Pid":null,"UnitName":"支","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"f30d4b40-54f6-4e75-9cc4-779ceb492a38","Pid":null,"UnitName":"付","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"33211d21-66e4-4b91-b529-8ef9e3596206","Pid":null,"UnitName":"瓶","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"b42813d9-aec7-4f59-8e87-94b68ce30410","Pid":null,"UnitName":"箱","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"4143a886-38f6-4b2b-95aa-a7534ee3cda4","Pid":null,"UnitName":"面","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"9ca3fd43-583d-4777-bbfc-ca77d020f8a7","Pid":null,"UnitName":"个","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"6494e21e-72ea-49c4-b30a-e6c333770886","Pid":null,"UnitName":"克","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0},{"Id":"8e6bd2bc-be68-4379-bba8-ebc1237c6cc8","Pid":null,"UnitName":"本","UnitScaler":1,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0}]
     * datas2: {"Datas":[{"Id":"4785e16e-577b-48c8-9199-0d67c7991033","Pid":null,"UnitName":"千克","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"32e254d2-bc64-4010-8a23-2a3661b5777e","Pid":null,"UnitName":"套","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"5286ff3e-fcee-48d0-8a70-2ed6185a22ee","Pid":null,"UnitName":"张","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"e07f0bad-d1b3-48ee-94c1-4f6ea054a227","Pid":null,"UnitName":"支","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"f30d4b40-54f6-4e75-9cc4-779ceb492a38","Pid":null,"UnitName":"付","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"33211d21-66e4-4b91-b529-8ef9e3596206","Pid":null,"UnitName":"瓶","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"b42813d9-aec7-4f59-8e87-94b68ce30410","Pid":null,"UnitName":"箱","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"4143a886-38f6-4b2b-95aa-a7534ee3cda4","Pid":null,"UnitName":"面","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"9ca3fd43-583d-4777-bbfc-ca77d020f8a7","Pid":null,"UnitName":"个","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"6494e21e-72ea-49c4-b30a-e6c333770886","Pid":null,"UnitName":"克","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0},{"Id":"8e6bd2bc-be68-4379-bba8-ebc1237c6cc8","Pid":null,"UnitName":"本","UnitScaler":1.00,"Remark":null,"CreateTime":"2019-03-04T14:11:31.4","GoodNumber":null,"GoodId":0,"IsKingDee":true,"DetailId":"00000000-0000-0000-0000-000000000000","CompanyId":1,"UnitLevel":0,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":false,"KingdeeScaler":0.0}],"
     * PageIndex":0,"Total":1,"Success":true,"Message":"Success","ErrorInfo":null,"Code":200}
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
         * Id : 4785e16e-577b-48c8-9199-0d67c7991033
         * Pid : null
         * UnitName : 千克
         * UnitScaler : 1.0
         * Remark : null
         * CreateTime : 2019-03-04T14:11:31.4
         * GoodNumber : null
         * GoodId : 0
         * IsKingDee : true
         * DetailId : 00000000-0000-0000-0000-000000000000
         * CompanyId : 1
         * UnitLevel : 0
         * WorkShopWorkBrenchUnitsConfig : []
         * MaxUnit : false
         * KingdeeScaler : 0.0
         */

        private String Id;
        private Object  Pid;
        private String UnitName;
        private double UnitScaler;
        private String Remark;
        private String CreateTime;
        private Object GoodNumber;
        private int GoodId;
        private boolean IsKingDee;
        private String DetailId;
        private int CompanyId;
        private int UnitLevel;
        private boolean MaxUnit;
        private double KingdeeScaler;
        private List<?> WorkShopWorkBrenchUnitsConfig;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public Object  getPid() {
            return Pid;
        }

        public void setPid(Object  Pid) {
            this.Pid = Pid;
        }

        public String getUnitName() {return UnitName;}

        public void setUnitName(String UnitName) {
            this.UnitName = UnitName;
        }

        public double getUnitScaler() {
            return UnitScaler;
        }

        public void setUnitScaler(double UnitScaler) {
            this.UnitScaler = UnitScaler;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
            if(Remark==null)
                this.Remark = "";
            else
                this.Remark = Remark.toString();
        }

        public String GetFullName()
        {
            if (getUnitScaler()>1)
                return this.getUnitName()+this.getRemark();

            return this.getUnitName();
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public Object getGoodNumber() {
            return GoodNumber;
        }

        public void setGoodNumber(Object GoodNumber) {
            this.GoodNumber = GoodNumber;
        }

        public int getGoodId() {
            return GoodId;
        }

        public void setGoodId(int GoodId) {
            this.GoodId = GoodId;
        }

        public boolean isIsKingDee() {
            return IsKingDee;
        }

        public void setIsKingDee(boolean IsKingDee) {
            this.IsKingDee = IsKingDee;
        }

        public String getDetailId() {
            return DetailId;
        }

        public void setDetailId(String DetailId) {
            this.DetailId = DetailId;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public int getUnitLevel() {
            return UnitLevel;
        }

        public void setUnitLevel(int UnitLevel) {
            this.UnitLevel = UnitLevel;
        }

        public boolean isMaxUnit() {
            return MaxUnit;
        }

        public void setMaxUnit(boolean MaxUnit) {
            this.MaxUnit = MaxUnit;
        }

        public double getKingdeeScaler() {
            return KingdeeScaler;
        }

        public void setKingdeeScaler(double KingdeeScaler) {
            this.KingdeeScaler = KingdeeScaler;
        }

        public List<?> getWorkShopWorkBrenchUnitsConfig() {
            return WorkShopWorkBrenchUnitsConfig;
        }

        public void setWorkShopWorkBrenchUnitsConfig(List<?> WorkShopWorkBrenchUnitsConfig) {
            this.WorkShopWorkBrenchUnitsConfig = WorkShopWorkBrenchUnitsConfig;
        }
    }
}
