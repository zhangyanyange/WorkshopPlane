package project.mvp.com.socket.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人 张岩
 * 时间   2019/6/10.
 * 类描述
 */


public class ViewWorkConfig implements Parcelable {

    /**
     * Code : 200
     * Datas : [{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"MachineIp":"192.168.21.117","MachineName":"raspberrypi","Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0","WorkShopWorkBrenchUnitsConfig":[{"EntryId":1,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"343720f1-4a0e-4f11-acd4-417ec17f860d"},{"EntryId":2,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"4616787d-4a59-4b86-b680-1de3a6708967"},{"EntryId":3,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"13f52ef8-667a-4749-9df2-115c68829bab"}]}]
     * Message : Success
     * PageIndex : 0
     * Success : true
     * Total : 1
     */

    private int Code;
    private String Message;
    private int PageIndex;
    private boolean Success;
    private int Total;
    private List<DatasBean> Datas;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<DatasBean> getDatas() {
        return Datas;
    }

    public void setDatas(List<DatasBean> Datas) {
        this.Datas = Datas;
    }

    public static class DatasBean implements Parcelable {
        /**
         * BatchNo : aaa
         * CompanyId : 2
         * CreateTime : 2019-07-17T14:07:20.947
         * CreateUserId : 0
         * FicmobillNo : PRC2019061806
         * Flag : true
         * FproductModel : PR-EL22
         * FproductName : 全天不脱妆银河流星眼线液笔2#
         * FworkShopId : 3499
         * GoodNumber : 2503.02.2302
         * Id : 1018
         * MachineId : 7
         * MachineIp : 192.168.21.117
         * MachineName : raspberrypi
         * Remark : 测试三
         * ScanComName : /dev/ttyACM0
         * StoreId : 4703
         * StorePlaceId : 0
         * WorkShopWorkBrenchUnitsConfig : [{"EntryId":1,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"343720f1-4a0e-4f11-acd4-417ec17f860d"},{"EntryId":2,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"4616787d-4a59-4b86-b680-1de3a6708967"},{"EntryId":3,"P":{"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"},"Pid":1018,"PrintName":"print1","UnitId":"13f52ef8-667a-4749-9df2-115c68829bab"}]
         */

        private String BatchNo;
        private int CompanyId;
        private String CreateTime;
        private int CreateUserId;
        private String FicmobillNo;
        private boolean Flag;
        private String FproductModel;
        private String FproductName;
        private int FworkShopId;
        private String GoodNumber;
        private int Id;
        private int MachineId;
        private String MachineIp;
        private String MachineName;
        private String Remark;
        private String ScanComName;
        private String StoreId;
        private String StorePlaceId;
        private List<WorkShopWorkBrenchUnitsConfigBean> WorkShopWorkBrenchUnitsConfig;

        public String getBatchNo() {
            return BatchNo;
        }

        public void setBatchNo(String BatchNo) {
            this.BatchNo = BatchNo;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(int CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        public String getFicmobillNo() {
            return FicmobillNo;
        }

        public void setFicmobillNo(String FicmobillNo) {
            this.FicmobillNo = FicmobillNo;
        }

        public boolean isFlag() {
            return Flag;
        }

        public void setFlag(boolean Flag) {
            this.Flag = Flag;
        }

        public String getFproductModel() {
            return FproductModel;
        }

        public void setFproductModel(String FproductModel) {
            this.FproductModel = FproductModel;
        }

        public String getFproductName() {
            return FproductName;
        }

        public void setFproductName(String FproductName) {
            this.FproductName = FproductName;
        }

        public int getFworkShopId() {
            return FworkShopId;
        }

        public void setFworkShopId(int FworkShopId) {
            this.FworkShopId = FworkShopId;
        }

        public String getGoodNumber() {
            return GoodNumber;
        }

        public void setGoodNumber(String GoodNumber) {
            this.GoodNumber = GoodNumber;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getMachineId() {
            return MachineId;
        }

        public void setMachineId(int MachineId) {
            this.MachineId = MachineId;
        }

        public String getMachineIp() {
            return MachineIp;
        }

        public void setMachineIp(String MachineIp) {
            this.MachineIp = MachineIp;
        }

        public String getMachineName() {
            return MachineName;
        }

        public void setMachineName(String MachineName) {
            this.MachineName = MachineName;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getScanComName() {
            return ScanComName;
        }

        public void setScanComName(String ScanComName) {
            this.ScanComName = ScanComName;
        }

        public String getStoreId() {
            return StoreId;
        }

        public void setStoreId(String StoreId) {
            this.StoreId = StoreId;
        }

        public String getStorePlaceId() {
            return StorePlaceId;
        }

        public void setStorePlaceId(String StorePlaceId) {
            this.StorePlaceId = StorePlaceId;
        }

        public List<WorkShopWorkBrenchUnitsConfigBean> getWorkShopWorkBrenchUnitsConfig() {
            return WorkShopWorkBrenchUnitsConfig;
        }

        public void setWorkShopWorkBrenchUnitsConfig(List<WorkShopWorkBrenchUnitsConfigBean> WorkShopWorkBrenchUnitsConfig) {
            this.WorkShopWorkBrenchUnitsConfig = WorkShopWorkBrenchUnitsConfig;
        }

        public static class WorkShopWorkBrenchUnitsConfigBean implements Parcelable {
            /**
             * EntryId : 1
             * P : {"BatchNo":"aaa","CompanyId":2,"CreateTime":"2019-07-17T14:07:20.947","CreateUserId":0,"FicmobillNo":"PRC2019061806","Flag":true,"FproductModel":"PR-EL22","FproductName":"全天不脱妆银河流星眼线液笔2#","FworkShopId":3499,"GoodNumber":"2503.02.2302","Id":1018,"MachineId":7,"Remark":"测试三","ScanComName":"/dev/ttyACM0","StoreId":"4703","StorePlaceId":"0"}
             * Pid : 1018
             * PrintName : print1
             * UnitId : 343720f1-4a0e-4f11-acd4-417ec17f860d
             */

            private int EntryId;
            private PBean P;
            private int Pid;
            private String PrintName;
            private String UnitId;

            public int getEntryId() {
                return EntryId;
            }

            public void setEntryId(int EntryId) {
                this.EntryId = EntryId;
            }

            public PBean getP() {
                return P;
            }

            public void setP(PBean P) {
                this.P = P;
            }

            public int getPid() {
                return Pid;
            }

            public void setPid(int Pid) {
                this.Pid = Pid;
            }

            public String getPrintName() {
                return PrintName;
            }

            public void setPrintName(String PrintName) {
                this.PrintName = PrintName;
            }

            public String getUnitId() {
                return UnitId;
            }

            public void setUnitId(String UnitId) {
                this.UnitId = UnitId;
            }

            public static class PBean implements Parcelable {
                /**
                 * BatchNo : aaa
                 * CompanyId : 2
                 * CreateTime : 2019-07-17T14:07:20.947
                 * CreateUserId : 0
                 * FicmobillNo : PRC2019061806
                 * Flag : true
                 * FproductModel : PR-EL22
                 * FproductName : 全天不脱妆银河流星眼线液笔2#
                 * FworkShopId : 3499
                 * GoodNumber : 2503.02.2302
                 * Id : 1018
                 * MachineId : 7
                 * Remark : 测试三
                 * ScanComName : /dev/ttyACM0
                 * StoreId : 4703
                 * StorePlaceId : 0
                 */

                private String BatchNo;
                private int CompanyId;
                private String CreateTime;
                private int CreateUserId;
                private String FicmobillNo;
                private boolean Flag;
                private String FproductModel;
                private String FproductName;
                private int FworkShopId;
                private String GoodNumber;
                private int Id;
                private int MachineId;
                private String Remark;
                private String ScanComName;
                private String StoreId;
                private String StorePlaceId;

                public String getBatchNo() {
                    return BatchNo;
                }

                public void setBatchNo(String BatchNo) {
                    this.BatchNo = BatchNo;
                }

                public int getCompanyId() {
                    return CompanyId;
                }

                public void setCompanyId(int CompanyId) {
                    this.CompanyId = CompanyId;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }

                public int getCreateUserId() {
                    return CreateUserId;
                }

                public void setCreateUserId(int CreateUserId) {
                    this.CreateUserId = CreateUserId;
                }

                public String getFicmobillNo() {
                    return FicmobillNo;
                }

                public void setFicmobillNo(String FicmobillNo) {
                    this.FicmobillNo = FicmobillNo;
                }

                public boolean isFlag() {
                    return Flag;
                }

                public void setFlag(boolean Flag) {
                    this.Flag = Flag;
                }

                public String getFproductModel() {
                    return FproductModel;
                }

                public void setFproductModel(String FproductModel) {
                    this.FproductModel = FproductModel;
                }

                public String getFproductName() {
                    return FproductName;
                }

                public void setFproductName(String FproductName) {
                    this.FproductName = FproductName;
                }

                public int getFworkShopId() {
                    return FworkShopId;
                }

                public void setFworkShopId(int FworkShopId) {
                    this.FworkShopId = FworkShopId;
                }

                public String getGoodNumber() {
                    return GoodNumber;
                }

                public void setGoodNumber(String GoodNumber) {
                    this.GoodNumber = GoodNumber;
                }

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public int getMachineId() {
                    return MachineId;
                }

                public void setMachineId(int MachineId) {
                    this.MachineId = MachineId;
                }

                public String getRemark() {
                    return Remark;
                }

                public void setRemark(String Remark) {
                    this.Remark = Remark;
                }

                public String getScanComName() {
                    return ScanComName;
                }

                public void setScanComName(String ScanComName) {
                    this.ScanComName = ScanComName;
                }

                public String getStoreId() {
                    return StoreId;
                }

                public void setStoreId(String StoreId) {
                    this.StoreId = StoreId;
                }

                public String getStorePlaceId() {
                    return StorePlaceId;
                }

                public void setStorePlaceId(String StorePlaceId) {
                    this.StorePlaceId = StorePlaceId;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.BatchNo);
                    dest.writeInt(this.CompanyId);
                    dest.writeString(this.CreateTime);
                    dest.writeInt(this.CreateUserId);
                    dest.writeString(this.FicmobillNo);
                    dest.writeByte(this.Flag ? (byte) 1 : (byte) 0);
                    dest.writeString(this.FproductModel);
                    dest.writeString(this.FproductName);
                    dest.writeInt(this.FworkShopId);
                    dest.writeString(this.GoodNumber);
                    dest.writeInt(this.Id);
                    dest.writeInt(this.MachineId);
                    dest.writeString(this.Remark);
                    dest.writeString(this.ScanComName);
                    dest.writeString(this.StoreId);
                    dest.writeString(this.StorePlaceId);
                }

                public PBean() {
                }

                protected PBean(Parcel in) {
                    this.BatchNo = in.readString();
                    this.CompanyId = in.readInt();
                    this.CreateTime = in.readString();
                    this.CreateUserId = in.readInt();
                    this.FicmobillNo = in.readString();
                    this.Flag = in.readByte() != 0;
                    this.FproductModel = in.readString();
                    this.FproductName = in.readString();
                    this.FworkShopId = in.readInt();
                    this.GoodNumber = in.readString();
                    this.Id = in.readInt();
                    this.MachineId = in.readInt();
                    this.Remark = in.readString();
                    this.ScanComName = in.readString();
                    this.StoreId = in.readString();
                    this.StorePlaceId = in.readString();
                }

                public static final Creator<PBean> CREATOR = new Creator<PBean>() {
                    @Override
                    public PBean createFromParcel(Parcel source) {
                        return new PBean(source);
                    }

                    @Override
                    public PBean[] newArray(int size) {
                        return new PBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.EntryId);
                dest.writeParcelable(this.P, flags);
                dest.writeInt(this.Pid);
                dest.writeString(this.PrintName);
                dest.writeString(this.UnitId);
            }

            public WorkShopWorkBrenchUnitsConfigBean() {
            }

            protected WorkShopWorkBrenchUnitsConfigBean(Parcel in) {
                this.EntryId = in.readInt();
                this.P = in.readParcelable(PBean.class.getClassLoader());
                this.Pid = in.readInt();
                this.PrintName = in.readString();
                this.UnitId = in.readString();
            }

            public static final Creator<WorkShopWorkBrenchUnitsConfigBean> CREATOR = new Creator<WorkShopWorkBrenchUnitsConfigBean>() {
                @Override
                public WorkShopWorkBrenchUnitsConfigBean createFromParcel(Parcel source) {
                    return new WorkShopWorkBrenchUnitsConfigBean(source);
                }

                @Override
                public WorkShopWorkBrenchUnitsConfigBean[] newArray(int size) {
                    return new WorkShopWorkBrenchUnitsConfigBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.BatchNo);
            dest.writeInt(this.CompanyId);
            dest.writeString(this.CreateTime);
            dest.writeInt(this.CreateUserId);
            dest.writeString(this.FicmobillNo);
            dest.writeByte(this.Flag ? (byte) 1 : (byte) 0);
            dest.writeString(this.FproductModel);
            dest.writeString(this.FproductName);
            dest.writeInt(this.FworkShopId);
            dest.writeString(this.GoodNumber);
            dest.writeInt(this.Id);
            dest.writeInt(this.MachineId);
            dest.writeString(this.MachineIp);
            dest.writeString(this.MachineName);
            dest.writeString(this.Remark);
            dest.writeString(this.ScanComName);
            dest.writeString(this.StoreId);
            dest.writeString(this.StorePlaceId);
            dest.writeList(this.WorkShopWorkBrenchUnitsConfig);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.BatchNo = in.readString();
            this.CompanyId = in.readInt();
            this.CreateTime = in.readString();
            this.CreateUserId = in.readInt();
            this.FicmobillNo = in.readString();
            this.Flag = in.readByte() != 0;
            this.FproductModel = in.readString();
            this.FproductName = in.readString();
            this.FworkShopId = in.readInt();
            this.GoodNumber = in.readString();
            this.Id = in.readInt();
            this.MachineId = in.readInt();
            this.MachineIp = in.readString();
            this.MachineName = in.readString();
            this.Remark = in.readString();
            this.ScanComName = in.readString();
            this.StoreId = in.readString();
            this.StorePlaceId = in.readString();
            this.WorkShopWorkBrenchUnitsConfig = new ArrayList<WorkShopWorkBrenchUnitsConfigBean>();
            in.readList(this.WorkShopWorkBrenchUnitsConfig, WorkShopWorkBrenchUnitsConfigBean.class.getClassLoader());
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Code);
        dest.writeString(this.Message);
        dest.writeInt(this.PageIndex);
        dest.writeByte(this.Success ? (byte) 1 : (byte) 0);
        dest.writeInt(this.Total);
        dest.writeList(this.Datas);
    }

    public ViewWorkConfig() {
    }

    protected ViewWorkConfig(Parcel in) {
        this.Code = in.readInt();
        this.Message = in.readString();
        this.PageIndex = in.readInt();
        this.Success = in.readByte() != 0;
        this.Total = in.readInt();
        this.Datas = new ArrayList<DatasBean>();
        in.readList(this.Datas, DatasBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ViewWorkConfig> CREATOR = new Parcelable.Creator<ViewWorkConfig>() {
        @Override
        public ViewWorkConfig createFromParcel(Parcel source) {
            return new ViewWorkConfig(source);
        }

        @Override
        public ViewWorkConfig[] newArray(int size) {
            return new ViewWorkConfig[size];
        }
    };
}
