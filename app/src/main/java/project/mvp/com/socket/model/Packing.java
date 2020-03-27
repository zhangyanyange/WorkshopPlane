package project.mvp.com.socket.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */



public class Packing {

    /**
     * Datas : [{"Id":"343720f1-4a0e-4f11-acd4-417ec17f860d","Pid":null,"UnitName":"箱","UnitScaler":27,"Remark":"系统自动添加","CreateTime":"2019-05-17T16:42:22.497","GoodNumber":"2503.02.2302","GoodId":null,"IsKingDee":false,"DetailId":"4616787d-4a59-4b86-b680-1de3a6708967","CompanyId":2,"UnitLevel":2,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":true},{"Id":"4405a0e6-8087-4c4b-8f2b-dad583314683","Pid":null,"UnitName":"箱","UnitScaler":28,"Remark":"系统自动添加","CreateTime":"2019-05-16T19:15:06.01","GoodNumber":"2503.02.2302","GoodId":null,"IsKingDee":false,"DetailId":"4616787d-4a59-4b86-b680-1de3a6708967","CompanyId":2,"UnitLevel":2,"WorkShopWorkBrenchUnitsConfig":[],"MaxUnit":true}]
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


    public static class DatasBean implements Parcelable {
        /**
         * Id : 343720f1-4a0e-4f11-acd4-417ec17f860d
         * Pid : null
         * UnitName : 箱
         * UnitScaler : 27.0
         * Remark : 系统自动添加
         * CreateTime : 2019-05-17T16:42:22.497
         * GoodNumber : 2503.02.2302
         * GoodId : null
         * IsKingDee : false
         * DetailId : 4616787d-4a59-4b86-b680-1de3a6708967
         * CompanyId : 2
         * UnitLevel : 2
         * WorkShopWorkBrenchUnitsConfig : []
         * MaxUnit : true
         */

        private String Id;
        private String Pid;
        private String UnitName;
        private double UnitScaler;
        private String Remark;
        private String CreateTime;
        private String GoodNumber;
        private String GoodId;
        private boolean IsKingDee;
        private String DetailId;
        private int CompanyId;
        private int UnitLevel;
        private boolean MaxUnit;
        private List<String> WorkShopWorkBrenchUnitsConfig;
        private String printerName;

        public String getPrinterName() {
            return printerName;
        }

        public void setPrinterName(String printerName) {
            this.printerName = printerName;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getPid() {
            return Pid;
        }

        public void setPid(String Pid) {
            this.Pid = Pid;
        }

        public String getUnitName() {
            return UnitName;
        }

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

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getGoodNumber() {
            return GoodNumber;
        }

        public void setGoodNumber(String GoodNumber) {
            this.GoodNumber = GoodNumber;
        }

        public String getGoodId() {
            return GoodId;
        }

        public void setGoodId(String GoodId) {
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

        public List<String> getWorkShopWorkBrenchUnitsConfig() {
            return WorkShopWorkBrenchUnitsConfig;
        }

        public void setWorkShopWorkBrenchUnitsConfig(List<String> WorkShopWorkBrenchUnitsConfig) {
            this.WorkShopWorkBrenchUnitsConfig = WorkShopWorkBrenchUnitsConfig;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.Id);
            dest.writeString(this.Pid);
            dest.writeString(this.UnitName);
            dest.writeDouble(this.UnitScaler);
            dest.writeString(this.Remark);
            dest.writeString(this.CreateTime);
            dest.writeString(this.GoodNumber);
            dest.writeString(this.GoodId);
            dest.writeByte(this.IsKingDee ? (byte) 1 : (byte) 0);
            dest.writeString(this.DetailId);
            dest.writeInt(this.CompanyId);
            dest.writeInt(this.UnitLevel);
            dest.writeByte(this.MaxUnit ? (byte) 1 : (byte) 0);
            dest.writeStringList(this.WorkShopWorkBrenchUnitsConfig);
            dest.writeString(this.printerName);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.Id = in.readString();
            this.Pid = in.readString();
            this.UnitName = in.readString();
            this.UnitScaler = in.readDouble();
            this.Remark = in.readString();
            this.CreateTime = in.readString();
            this.GoodNumber = in.readString();
            this.GoodId = in.readString();
            this.IsKingDee = in.readByte() != 0;
            this.DetailId = in.readString();
            this.CompanyId = in.readInt();
            this.UnitLevel = in.readInt();
            this.MaxUnit = in.readByte() != 0;
            this.WorkShopWorkBrenchUnitsConfig = in.createStringArrayList();
            this.printerName = in.readString();
        }

        public static final Parcelable.Creator<DatasBean> CREATOR = new Parcelable.Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };
    }
}
