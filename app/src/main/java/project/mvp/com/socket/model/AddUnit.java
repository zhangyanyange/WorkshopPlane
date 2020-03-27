package project.mvp.com.socket.model;

/**
 * 创建人 张岩
 * 时间   2019/8/1.
 * 类描述
 */
public class AddUnit {

    private int CompanyId;
    private String DetailId;
    private String UnitName;
    private double UnitScaler;
    private String GoodNumber;
    private String Remark;

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getDetailId() {
        return DetailId;
    }

    public void setDetailId(String detailId) {
        DetailId = detailId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public double getUnitScaler() {
        return UnitScaler;
    }

    public void setUnitScaler(double unitScaler) {
        UnitScaler = unitScaler;
    }

    public String getGoodNumber() {
        return GoodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        GoodNumber = goodNumber;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
