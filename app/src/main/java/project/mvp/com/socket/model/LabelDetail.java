package project.mvp.com.socket.model;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class LabelDetail {

    /**
     * Datas : [{"Pname":"Gprinter GP-1524D","Mark":"尾箱-1","PackageInfo":{"ID":"00000000-0000-0000-0000-000000000000","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":0,"Number":1,"CreateDate":"2019-11-05T09:03:14.8795228+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"个","SourceBillNo":"PRC2019091602","Description":"1测试盒零0个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-3","PackageInfo":{"ID":"f982c886-4e0b-4e4c-8903-bc302a369211","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T09:02:59.0956272+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-2","PackageInfo":{"ID":"cec07153-0a3d-48df-af9d-bd4d0db48f66","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:14:47.8138982+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-1","PackageInfo":{"ID":"2a011b5d-2efc-428b-9757-eaa8af4df9a8","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:14:46.8440982+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-3","PackageInfo":{"ID":"5925db3f-8baa-43cb-8c8c-aa26cac93b97","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:14:51.493613+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-1","PackageInfo":{"ID":"89d6f21b-b42e-4552-b335-4eb61b75a637","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:45:08.0151262+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-2","PackageInfo":{"ID":"8cb3e50a-bc80-4e98-bd4a-86847bedf2fd","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T09:02:58.0833687+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-3","PackageInfo":{"ID":"f59a064f-86b4-48b2-b6f8-ee2536382cd7","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:45:10.6960556+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"尾箱-1","PackageInfo":{"ID":"00000000-0000-0000-0000-000000000000","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":0,"Number":1,"CreateDate":"2019-11-05T05:45:22.3413983+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"个","SourceBillNo":"PRC2019091602","Description":"1测试盒零0个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-1","PackageInfo":{"ID":"06cbb5a1-57f4-41f6-b9c8-fc1e3c0a0641","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T09:02:57.1669188+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-1","PackageInfo":{"ID":"96e4ae8f-e0d6-43c4-ba80-02cdac03c663","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-06T01:25:29.9661471+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"},{"Pname":"Gprinter GP-1524D","Mark":"测试盒-2","PackageInfo":{"ID":"afecd351-34df-473a-b1e4-687079c24b14","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":2,"Number":1,"CreateDate":"2019-11-05T05:45:09.4819872+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"测试盒","SourceBillNo":"PRC2019091602","Description":"2.00个(2.00个)"},"PrintHost":"192.168.60.36"}]
     * PageIndex : 1
     * Total : 12
     * Success : true
     * Message : Success
     * ErrorInfo : null
     * Code : 200
     */

    private int PageIndex;
    private int Total;
    private boolean Success;
    private String Message;
    private String ErrorInfo;
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

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String ErrorInfo) {
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

    public static class DatasBean  {
        /**
         * Pname : Gprinter GP-1524D
         * Mark : 尾箱-1
         * PackageInfo : {"ID":"00000000-0000-0000-0000-000000000000","BatchCode":"11111","GoodNumbers":"2202.04.0603","GoodName":"修护唇乳bc","ModelCode":"PR-L06","Sacler":0,"Number":1,"CreateDate":"2019-11-05T09:03:14.8795228+00:00","StoreId":"276","WorkPalceId":"0","UnitName":"个","SourceBillNo":"PRC2019091602","Description":"1测试盒零0个(2.00个)"}
         * PrintHost : 192.168.60.36
         */

        private String Pname;
        private String Mark;
        private PackageInfoBean PackageInfo;
        private String PrintHost;

        public String getPname() {
            return Pname;
        }

        public void setPname(String Pname) {
            this.Pname = Pname;
        }

        public String getMark() {
            return Mark;
        }

        public void setMark(String Mark) {
            this.Mark = Mark;
        }

        public PackageInfoBean getPackageInfo() {
            return PackageInfo;
        }

        public void setPackageInfo(PackageInfoBean PackageInfo) {
            this.PackageInfo = PackageInfo;
        }

        public String getPrintHost() {
            return PrintHost;
        }

        public void setPrintHost(String PrintHost) {
            this.PrintHost = PrintHost;
        }



        public static class PackageInfoBean {
            /**
             * ID : 00000000-0000-0000-0000-000000000000
             * BatchCode : 11111
             * GoodNumbers : 2202.04.0603
             * GoodName : 修护唇乳bc
             * ModelCode : PR-L06
             * Sacler : 0
             * Number : 1
             * CreateDate : 2019-11-05T09:03:14.8795228+00:00
             * StoreId : 276
             * WorkPalceId : 0
             * UnitName : 个
             * SourceBillNo : PRC2019091602
             * Description : 1测试盒零0个(2.00个)
             */

            private String ID;
            private String BatchCode;
            private String GoodNumbers;
            private String GoodName;
            private String ModelCode;
            private int Sacler;
            private int Number;
            private String CreateDate;
            private String StoreId;
            private String WorkPalceId;
            private String UnitName;
            private String SourceBillNo;
            private String Description;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getBatchCode() {
                return BatchCode;
            }

            public void setBatchCode(String BatchCode) {
                this.BatchCode = BatchCode;
            }

            public String getGoodNumbers() {
                return GoodNumbers;
            }

            public void setGoodNumbers(String GoodNumbers) {
                this.GoodNumbers = GoodNumbers;
            }

            public String getGoodName() {
                return GoodName;
            }

            public void setGoodName(String GoodName) {
                this.GoodName = GoodName;
            }

            public String getModelCode() {
                return ModelCode;
            }

            public void setModelCode(String ModelCode) {
                this.ModelCode = ModelCode;
            }

            public int getSacler() {
                return Sacler;
            }

            public void setSacler(int Sacler) {
                this.Sacler = Sacler;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
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

            public String getUnitName() {
                return UnitName;
            }

            public void setUnitName(String UnitName) {
                this.UnitName = UnitName;
            }

            public String getSourceBillNo() {
                return SourceBillNo;
            }

            public void setSourceBillNo(String SourceBillNo) {
                this.SourceBillNo = SourceBillNo;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }
        }
    }
}
