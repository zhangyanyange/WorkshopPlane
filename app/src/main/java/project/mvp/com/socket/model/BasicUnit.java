package project.mvp.com.socket.model;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * 创建人 张岩
 * 时间   2019/8/1.
 * 类描述
 */
public class BasicUnit implements IPickerViewData {
    private String UnitName;
    private String Id;

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String getPickerViewText() {
        return  UnitName;
    }
}
