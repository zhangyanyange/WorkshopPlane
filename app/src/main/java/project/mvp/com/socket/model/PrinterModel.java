package project.mvp.com.socket.model;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * 创建人 张岩
 * 时间   2019/6/6.
 * 类描述
 */
public class PrinterModel implements IPickerViewData {
    private String PrinterName;

    public String getPrinterName() {
        return PrinterName;
    }

    public void setPrinterName(String printerName) {
        PrinterName = printerName;
    }

    @Override
    public String getPickerViewText() {
        return PrinterName;
    }
}
