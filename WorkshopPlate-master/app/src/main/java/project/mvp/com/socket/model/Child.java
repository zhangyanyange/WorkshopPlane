package project.mvp.com.socket.model;

public class Child {
    private  String UnitName;
    private  String UnitScaler;
    private  String GoodNumber;
    private  boolean IsLeaf;
    private  String Id;
    private  String PName;

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getUnitScaler() {
        return UnitScaler;
    }

    public void setUnitScaler(String unitScaler) {
        UnitScaler = unitScaler;
    }

    public String getGoodNumber() {
        return GoodNumber;
    }

    public void setGoodNumber(String goodNumber) {
        GoodNumber = goodNumber;
    }

    public boolean isLeaf() {
        return IsLeaf;
    }

    public void setLeaf(boolean leaf) {
        IsLeaf = leaf;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }
}
