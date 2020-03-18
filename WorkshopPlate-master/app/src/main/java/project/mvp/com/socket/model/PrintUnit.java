package project.mvp.com.socket.model;

import java.util.List;

public class PrintUnit {

    /**
     * Datas : {"ProductName":"全天不脱妆银河流星眼线液笔1#","BatchNo":"20190930","CompanyId":2,"SourceBillNo":"PRC2019092301","ModelCode":"PR-EL22","StoreId":"276","WorkPalceId":"0","Units":[{"UnitName":"箱","UnitScaler":27,"GoodNumber":"2503.02.2301","IsLeaf":false,"Id":"c41ab337-78c1-4cb6-a800-8a359b458a69","PName":"192.168.88.248/Gprinter GP-9025T","Level":2},{"UnitName":"盒","UnitScaler":12,"GoodNumber":"2503.02.2301","IsLeaf":false,"Id":"bd62bf9c-1eaa-45ac-bb33-78d31fec27c4","PName":"192.168.88.248/Gprinter GP-9025T","Level":1},{"UnitName":"个","UnitScaler":1,"IsLeaf":true,"Id":"13f52ef8-667a-4749-9df2-115c68829bab","PName":"192.168.88.248/Gprinter GP-9025T","Level":0}]}
     * PageIndex : 0
     * Total : 1
     * Success : true
     * Message : Success
     * Code : 200
     */

    private DatasBean Datas;
    private int PageIndex;
    private int Total;
    private boolean Success;
    private String Message;
    private int Code;

    public DatasBean getDatas() {
        return Datas;
    }

    public void setDatas(DatasBean Datas) {
        this.Datas = Datas;
    }

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

    public static class DatasBean {
        /**
         * ProductName : 全天不脱妆银河流星眼线液笔1#
         * BatchNo : 20190930
         * CompanyId : 2
         * SourceBillNo : PRC2019092301
         * ModelCode : PR-EL22
         * StoreId : 276
         * WorkPalceId : 0
         * Units : [{"UnitName":"箱","UnitScaler":27,"GoodNumber":"2503.02.2301","IsLeaf":false,"Id":"c41ab337-78c1-4cb6-a800-8a359b458a69","PName":"192.168.88.248/Gprinter GP-9025T","Level":2},{"UnitName":"盒","UnitScaler":12,"GoodNumber":"2503.02.2301","IsLeaf":false,"Id":"bd62bf9c-1eaa-45ac-bb33-78d31fec27c4","PName":"192.168.88.248/Gprinter GP-9025T","Level":1},{"UnitName":"个","UnitScaler":1,"IsLeaf":true,"Id":"13f52ef8-667a-4749-9df2-115c68829bab","PName":"192.168.88.248/Gprinter GP-9025T","Level":0}]
         */
        private boolean isCan;

        public boolean isCan() {
            return isCan;
        }

        public void setCan(boolean can) {
            isCan = can;
        }

        private String ProductName;
        private String BatchNo;
        private int CompanyId;
        private String SourceBillNo;
        private String ModelCode;
        private String StoreId;
        private String WorkPalceId;
        private List<UnitsBean> Units;

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

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

        public String getSourceBillNo() {
            return SourceBillNo;
        }

        public void setSourceBillNo(String SourceBillNo) {
            this.SourceBillNo = SourceBillNo;
        }

        public String getModelCode() {
            return ModelCode;
        }

        public void setModelCode(String ModelCode) {
            this.ModelCode = ModelCode;
        }

        public String getStoreId() {
            return StoreId;
        }

        public void setStoreId(String StoreId) {
            this.StoreId = StoreId;
        }

        public String getWorkPalceId() {
            return WorkPalceId;
        }

        public void setWorkPalceId(String WorkPalceId) {
            this.WorkPalceId = WorkPalceId;
        }

        public List<UnitsBean> getUnits() {
            return Units;
        }

        public void setUnits(List<UnitsBean> Units) {
            this.Units = Units;
        }

        public static class UnitsBean {
            /**
             * UnitName : 箱
             * UnitScaler : 27.0
             * GoodNumber : 2503.02.2301
             * IsLeaf : false
             * Id : c41ab337-78c1-4cb6-a800-8a359b458a69
             * PName : 192.168.88.248/Gprinter GP-9025T
             * Level : 2
             */

            private String UnitName;
            private double UnitScaler;
            private String GoodNumber;
            private boolean IsLeaf;
            private String Id;
            private String PName;
            private int Level;
            private String mark;


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

            public String getGoodNumber() {
                return GoodNumber;
            }

            public void setGoodNumber(String GoodNumber) {
                this.GoodNumber = GoodNumber;
            }

            public boolean isIsLeaf() {
                return IsLeaf;
            }

            public void setIsLeaf(boolean IsLeaf) {
                this.IsLeaf = IsLeaf;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getPName() {
                return PName;
            }

            public void setPName(String PName) {
                this.PName = PName;
            }

            public int getLevel() {
                return Level;
            }

            public void setLevel(int Level) {
                this.Level = Level;
            }
        }
    }
}
