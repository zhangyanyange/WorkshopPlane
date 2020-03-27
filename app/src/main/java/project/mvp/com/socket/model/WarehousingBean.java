package project.mvp.com.socket.model;

import java.util.ArrayList;

public class WarehousingBean {
    private ArrayList<WarePrint.PackageInfoBean> packages;
    private int brenchId;
    private int inStoreNumber;

    public ArrayList<WarePrint.PackageInfoBean> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<WarePrint.PackageInfoBean> packages) {
        this.packages = packages;
    }

    public int getBrenchId() {
        return brenchId;
    }

    public void setBrenchId(int brenchId) {
        this.brenchId = brenchId;
    }

    public int getInStoreNumber() {
        return inStoreNumber;
    }

    public void setInStoreNumber(int inStoreNumber) {
        this.inStoreNumber = inStoreNumber;
    }
}
