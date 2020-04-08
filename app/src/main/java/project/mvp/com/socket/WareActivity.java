package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.PrintUnit;
import project.mvp.com.socket.model.WarePrint;
import project.mvp.com.socket.model.WarehousingBean;
import project.mvp.com.socket.utils.FileUtils;
import project.mvp.com.socket.utils.ShowToastTime;


public class WareActivity extends AppCompatActivity {

    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private PrintUnit unit;
    private PrintUnit.DatasBean.UnitsBean unitsBean;
    private Gson gson;
    private String wareHouse;
    private int productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        productID = intent.getIntExtra("productID", -1);
        gson = new Gson();
        OkHttpUtils
                .get()
                .url(MyApplication.baseUrl + "api/WorkBrenchConfig/ConfList/" + productID).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        unit = gson.fromJson(response, PrintUnit.class);
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadView.setVisibility(View.VISIBLE);
                int number = Integer.parseInt(et.getText().toString());
                int totalPackages = 1;
                List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
                for (int i = 0; i < unit.getDatas().getUnits().size(); i++) {
                    totalPackages = totalPackages * (int) units.get(i).getUnitScaler();
                }
                int mag = number / totalPackages;
                ArrayList<WarePrint.PackageInfoBean> list = new ArrayList<>();
                ArrayList<WarePrint.PackageInfoBean>wareHousing=new ArrayList<>();
                for (int z = 1; z <= mag; z++) {
                    WarePrint.PackageInfoBean packageInfoBean1 = null;
                    WarePrint.PackageInfoBean infoBean = scanRecur(packageInfoBean1, z);
                    WarePrint.PackageInfoBean packageInfoBean2=null;
                    WarePrint.PackageInfoBean infoBean2 = recur(packageInfoBean2, z);
                    list.add(infoBean);
                    wareHousing.add(infoBean2);
                }
                int Remaining = number % totalPackages;

                PrintUnit.DatasBean.UnitsBean unitsBean = null;
                int RemainingMag = 1;
                for (int i = 0; i < units.size(); i++) {
                    if (!(units.get(i).getLevel() == units.size() - 1)) {
                        RemainingMag = RemainingMag * (int) units.get(i).getUnitScaler();
                        if (units.get(i).getLevel() == 0) {
                            unitsBean = units.get(i);
                        }
                    }
                }
                //剩余循环次数
                if (Remaining != 0) {
      ;
                    //生成尾箱数据
                    int magR = Remaining / RemainingMag;
                    int SmallestUnit = Remaining % RemainingMag;
                    WarePrint.PackageInfoBean packageInfoBean2 = null;
                    mag=mag+1;
                    //尾箱
                    WarePrint.PackageInfoBean infoBean = ScanTailBox(packageInfoBean2, magR,Remaining,SmallestUnit,mag);
                    list.add(infoBean);
                    //生成类似尾盒的数据
                    if(SmallestUnit>0){
                        WarePrint.PackageInfoBean packageInfoBean = printSmallUnit(mag, magR, unitsBean, SmallestUnit);
                        if(infoBean.getChild()==null){
                            ArrayList< WarePrint.PackageInfoBean> arrayList=new ArrayList<>();
                            arrayList.add(packageInfoBean);
                            infoBean.setChild(arrayList);
                        }else{
                            infoBean.getChild().add(packageInfoBean);
                        }
                    }



                    WarePrint.PackageInfoBean packageInfoBean3 = null;
                    WarePrint.PackageInfoBean infoBean3 = TailBox(packageInfoBean3, magR);

                    wareHousing.add(infoBean3);
                    //生成剩余最小单位包装的个数
                    if(SmallestUnit>0){
                        WarePrint.PackageInfoBean packageInfoBeans = smallUnit(mag, magR, unitsBean, SmallestUnit);

                        if(infoBean3.getChild()!=null){
                            infoBean3.getChild().add(packageInfoBeans);
                        }else{
                            ArrayList< WarePrint.PackageInfoBean> arrayList=new ArrayList<>();
                            arrayList.add(packageInfoBeans);
                            infoBean3.setChild(arrayList);
                        }
                    }

                }
                WarehousingBean bean=new WarehousingBean();
                bean.setPackages(wareHousing);
                bean.setInStoreNumber(number);
                bean.setBrenchId(productID);
                //扫码标签
                String x = new Gson().toJson(list);
                //入库标签
                wareHouse = new Gson().toJson(bean);

//                FileUtils.writeFile(x,FileUtils.getExternalStoragePath(),true);
//                System.out.println(FileUtils.getExternalStoragePath());
                //打印条码
                OkHttpUtils.postString()
                        .url(MyApplication.baseUrl+"api/Print")
                        .content(x)
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                ShowToastTime.showTextToast(e.toString());
                                loadView.setVisibility(View.GONE);
                            }

                            @Override
                            public void onResponse(String response) {

                                ConfigureOptions options = gson.fromJson(response, ConfigureOptions.class);

                                if(options.getCode()==200){
                                   //入库
                                    OkHttpUtils.postString()
                                            .url(MyApplication.baseUrl+"api/Production/Store")
                                            .content(wareHouse)
                                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                            .build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e) {
                                                    loadView.setVisibility(View.GONE);
                                                    ShowToastTime.showTextToast(e.toString());
                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    loadView.setVisibility(View.GONE);
                                                    ConfigureOptions options = gson.fromJson(response, ConfigureOptions.class);
                                                    if(options.getCode()==200){
                                                        ShowToastTime.showTextToast("入库成功");
                                                        finish();
                                                    }else{
                                                        ShowToastTime.showTextToast(options.getMessage());
                                                    }
                                                }
                                            });
                                }else{
                                    loadView.setVisibility(View.GONE);
                                    ShowToastTime.showTextToast(options.getMessage());
                                }

                            }
                        });
            }
        });


    }

    //整箱入库处理方式
    private WarePrint.PackageInfoBean recur(WarePrint.PackageInfoBean info, int g) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if (info == null) {
            PrintUnit.DatasBean.UnitsBean unitsBean = null;
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getLevel() == units.size() - 1) {
                    unitsBean = units.get(i);
                    break;
                }
            }
            info = new WarePrint.PackageInfoBean();
            info.setMark(unitsBean.getUnitName() + "-" + g);

            info.setPname(unitsBean.getPName());
            info.setBatchCode(unit.getDatas().getBatchNo());
            info.setGoodName(unit.getDatas().getProductName());
            info.setModelCode(unit.getDatas().getModelCode());
            info.setStoreId(unit.getDatas().getStoreId());
            info.setWorkPalceId(unit.getDatas().getWorkPalceId());
            info.setSourceBillNo(unit.getDatas().getSourceBillNo());
            info.setId(getMyUUID());
            info.setUnitId(unitsBean.getId());
            info.setNumber((int) unitsBean.getUnitScaler());
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int) unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if (!info.isIsLeaf()) {
                recur(info, 1);
            }
        } else {
            if (!info.isIsLeaf()) {
                for (int i = 1; i <= info.getSacler(); i++) {
                    PrintUnit.DatasBean.UnitsBean unitsBean = null;
                    for (int q = 0; q < units.size(); q++) {
                        if (units.get(q).getLevel() == info.getLevel()) {
                            unitsBean = units.get(q);
                            break;
                        }
                    }
                    for (int q = 0; q < units.size(); q++) {
                        if (units.get(q).getLevel() == unitsBean.getLevel() - 1) {
                            unitsBean = units.get(q);
                            break;
                        }
                    }
                    WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();
                    packageInfoBean.setMark(info.getMark() + unitsBean.getUnitName() + "-" + i);

                    packageInfoBean.setPname(unitsBean.getPName());
                    packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                    packageInfoBean.setGoodName(unit.getDatas().getProductName());
                    packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                    packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                    packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                    packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                    packageInfoBean.setId(getMyUUID());
                    packageInfoBean.setUnitId(unitsBean.getId());
                    packageInfoBean.setNumber((int) unitsBean.getUnitScaler());
                    packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                    packageInfoBean.setSacler((int) unitsBean.getUnitScaler());
                    packageInfoBean.setUnitName(unitsBean.getUnitName());
                    packageInfoBean.setLevel(unitsBean.getLevel());
                    packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                    if (info.getChild() == null) {
                        ArrayList<WarePrint.PackageInfoBean> wp = new ArrayList<>();
                        info.setChild(wp);
                    }
                    info.getChild().add(packageInfoBean);
                    if (!packageInfoBean.isIsLeaf()) {
                        recur(packageInfoBean, 1);
                    }
                }
            }
        }
        return info;
    }

    //整箱扫码处理方式
    private WarePrint.PackageInfoBean scanRecur(WarePrint.PackageInfoBean info, int g) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if (info == null) {
            PrintUnit.DatasBean.UnitsBean unitsBean = null;
            PrintUnit.DatasBean.UnitsBean subUnitsBean=null;
            PrintUnit.DatasBean.UnitsBean lastUnitsBean=null;
            int totalPackages=1;
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getLevel() == units.size() - 1) {
                    unitsBean = units.get(i);
                }
                if (units.get(i).getLevel() == units.size() - 2) {
                    subUnitsBean = units.get(i);
                }
                if (units.get(i).getLevel() == 0) {
                    lastUnitsBean = units.get(i);
                }
                totalPackages = totalPackages * (int) units.get(i).getUnitScaler();
            }
            info = new WarePrint.PackageInfoBean();
            info.setMark(unitsBean.getUnitName() + "-" + g);

            info.setPname(unitsBean.getPName());
            info.setBatchCode(unit.getDatas().getBatchNo());
            info.setGoodName(unit.getDatas().getProductName());
            info.setModelCode(unit.getDatas().getModelCode());
            info.setStoreId(unit.getDatas().getStoreId());
            info.setWorkPalceId(unit.getDatas().getWorkPalceId());
            info.setSourceBillNo(unit.getDatas().getSourceBillNo());
            info.setId(getMyUUID());
            info.setUnitId(unitsBean.getId());
            info.setNumber((int)unitsBean.getUnitScaler());
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int) unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            info.setDescription((int) unitsBean.getUnitScaler()+subUnitsBean.getUnitName()+"("+totalPackages+lastUnitsBean.getUnitName()+")");
            if (!info.isIsLeaf()) {
                scanRecur(info, 1);
            }
        } else {
            if (!info.isIsLeaf()) {
                if (info.getLevel() != 1) {//不是倒数第二等级
                    for (int i = 1; i <= info.getSacler(); i++) {
                        PrintUnit.DatasBean.UnitsBean unitsBean = null;
                        PrintUnit.DatasBean.UnitsBean lastUnitsBean=null;
                        for (int q = 0; q < units.size(); q++) {
                            if (units.get(q).getLevel() == info.getLevel()) {
                                unitsBean = units.get(q);
                                break;
                            }
                        }
                        int totalPackages=1;

                        for(int q = 0; q < units.size(); q++){
                            if(units.get(q).getLevel() != unitsBean.getLevel()){ //除了相等换算率
                                totalPackages = totalPackages * (int) units.get(q).getUnitScaler();
                            }
                        }
                        for (int q = 0; q < units.size(); q++) {
                            if (units.get(q).getLevel() == unitsBean.getLevel() - 1) {
                                unitsBean = units.get(q);
                                break;
                            }
                        }
                        for (int q = 0; q < units.size(); q++) {
                            if(units.get(q).getLevel() == 0){
                                lastUnitsBean= units.get(q);
                            }
                        }


                        WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();
                        packageInfoBean.setMark(info.getMark() + unitsBean.getUnitName() + "-" + i);

                        packageInfoBean.setPname(unitsBean.getPName());
                        packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                        packageInfoBean.setGoodName(unit.getDatas().getProductName());
                        packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                        packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                        packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                        packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                        packageInfoBean.setId(getMyUUID());
                        packageInfoBean.setUnitId(unitsBean.getId());
                        info.setNumber((int)unitsBean.getUnitScaler());
                        packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                        packageInfoBean.setSacler((int) unitsBean.getUnitScaler());
                        packageInfoBean.setUnitName(unitsBean.getUnitName());
                        packageInfoBean.setLevel(unitsBean.getLevel());
                        packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                        packageInfoBean.setDescription("("+totalPackages+lastUnitsBean.getUnitName()+")");
                        if (info.getChild() == null) {
                            ArrayList<WarePrint.PackageInfoBean> wp = new ArrayList<>();
                            info.setChild(wp);
                        }
                        info.getChild().add(packageInfoBean);
                        if (!packageInfoBean.isIsLeaf()) {
                            scanRecur(packageInfoBean, 1);
                        }
                    }
                }
            }
        }
        return info;
    }

    //尾箱入库处理方式
    private WarePrint.PackageInfoBean TailBox(WarePrint.PackageInfoBean info, int g) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if (info == null) {
            PrintUnit.DatasBean.UnitsBean unitsBean = null;
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getLevel() == units.size() - 1) {
                    unitsBean = units.get(i);
                    break;
                }
            }
            info = new WarePrint.PackageInfoBean();
            info.setMark("尾箱");
            info.setTail(true);
            info.setPname(unitsBean.getPName());
            info.setBatchCode(unit.getDatas().getBatchNo());
            info.setGoodName(unit.getDatas().getProductName());
            info.setModelCode(unit.getDatas().getModelCode());
            info.setStoreId(unit.getDatas().getStoreId());
            info.setWorkPalceId(unit.getDatas().getWorkPalceId());
            info.setSourceBillNo(unit.getDatas().getSourceBillNo());
            info.setId(getMyUUID());
            info.setUnitId(unitsBean.getId());
            info.setNumber((int) unitsBean.getUnitScaler());
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int) unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if (!info.isIsLeaf()) {
                TailBox(info, g);
            }
        } else {
            if (!info.isIsLeaf()) {
                for (int i = 1; i <= g; i++) {
                    PrintUnit.DatasBean.UnitsBean unitsBean = null;
                    for (int q = 0; q < units.size(); q++) {
                        if (units.get(q).getLevel() == info.getLevel()) {
                            unitsBean = units.get(q);
                            break;
                        }
                    }
                    for (int q = 0; q < units.size(); q++) {
                        if (units.get(q).getLevel() == unitsBean.getLevel() - 1) {
                            unitsBean = units.get(q);
                            break;
                        }
                    }
                    WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();
                    packageInfoBean.setMark(info.getMark() + unitsBean.getUnitName() + "-" + i);

                    packageInfoBean.setPname(unitsBean.getPName());
                    packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                    packageInfoBean.setGoodName(unit.getDatas().getProductName());
                    packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                    packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                    packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                    packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                    packageInfoBean.setId(getMyUUID());
                    packageInfoBean.setUnitId(unitsBean.getId());
                    packageInfoBean.setNumber((int) unitsBean.getUnitScaler());
                    packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                    packageInfoBean.setSacler((int) unitsBean.getUnitScaler());
                    packageInfoBean.setUnitName(unitsBean.getUnitName());
                    packageInfoBean.setLevel(unitsBean.getLevel());
                    packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                    if (info.getChild() == null) {
                        ArrayList<WarePrint.PackageInfoBean> wp = new ArrayList<>();
                        info.setChild(wp);
                    }
                    info.getChild().add(packageInfoBean);
                    if (!packageInfoBean.isIsLeaf()) {
                        TailBox(packageInfoBean, packageInfoBean.getSacler());
                    }
                }
            }
        }
        return info;
    }

    //尾箱扫码处理方式
    private WarePrint.PackageInfoBean ScanTailBox(WarePrint.PackageInfoBean info, int g,int number,int branch,int maxUnitNumber) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();

        if (info == null) {
            PrintUnit.DatasBean.UnitsBean unitsBean = null;
            PrintUnit.DatasBean.UnitsBean lastUnitsBean=null;
            PrintUnit.DatasBean.UnitsBean  subUnitsBean=null;
            int totalPackages=1;
            for (int i = 0; i < units.size(); i++) {
                if (units.get(i).getLevel() == units.size() - 1) {
                    unitsBean = units.get(i);
                }
                if (units.get(i).getLevel() == units.size() - 2) {
                    subUnitsBean = units.get(i);
                }
                if (units.get(i).getLevel() == 0) {
                    lastUnitsBean = units.get(i);
                }
                totalPackages = totalPackages * (int) units.get(i).getUnitScaler();
            }

            info = new WarePrint.PackageInfoBean();
            info.setMark(unitsBean.getUnitName()+"-"+maxUnitNumber);
            info.setTail(true);
            info.setPname(unitsBean.getPName());
            info.setBatchCode(unit.getDatas().getBatchNo());
            info.setGoodName(unit.getDatas().getProductName());
            info.setModelCode(unit.getDatas().getModelCode());
            info.setStoreId(unit.getDatas().getStoreId());
            info.setWorkPalceId(unit.getDatas().getWorkPalceId());
            info.setSourceBillNo(unit.getDatas().getSourceBillNo());
            info.setId(getMyUUID());
            info.setUnitId(unitsBean.getId());
            info.setNumber((int) unitsBean.getUnitScaler());
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int) unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if(branch>0){
                info.setDescription((g+1)+subUnitsBean.getUnitName()+"("+number+lastUnitsBean.getUnitName()+")");
            }else{
                info.setDescription(g+subUnitsBean.getUnitName()+"("+number+lastUnitsBean.getUnitName()+")");
            }

            if (!info.isIsLeaf()) {
                ScanTailBox(info, g,number,branch,0);
            }
        } else {
            if (!info.isIsLeaf()) {
                if (info.getLevel() != 1) {
                    for (int i = 1; i <= g; i++) {
                        PrintUnit.DatasBean.UnitsBean unitsBean = null;
                        PrintUnit.DatasBean.UnitsBean subUnitsBean=null;
                        PrintUnit.DatasBean.UnitsBean lastUnitsBean=null;
                        for (int q = 0; q < units.size(); q++) {
                            if (units.get(q).getLevel() == info.getLevel()) {
                                unitsBean = units.get(q);
                                break;
                            }
                        }
                        int totalPackages=1;

                        for(int q = 0; q < units.size(); q++){
                            if(units.get(q).getLevel() != unitsBean.getLevel()){ //除了相等换算率
                                totalPackages = totalPackages * (int) units.get(q).getUnitScaler();
                            }
                        }
                        for (int q = 0; q < units.size(); q++) {
                            if (units.get(q).getLevel() == unitsBean.getLevel() - 1) {
                                unitsBean = units.get(q);
                                break;
                            }
                        }
                        for (int q = 0; q < units.size(); q++) {
                            if(units.get(q).getLevel() == 0){
                                lastUnitsBean= units.get(q);
                            }
                        }
                        WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();
                        packageInfoBean.setMark(info.getMark() + unitsBean.getUnitName() + "-" + i);

                        packageInfoBean.setPname(unitsBean.getPName());
                        packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                        packageInfoBean.setGoodName(unit.getDatas().getProductName());
                        packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                        packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                        packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                        packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                        packageInfoBean.setId(getMyUUID());
                        packageInfoBean.setUnitId(unitsBean.getId());
                        packageInfoBean.setNumber((int) unitsBean.getUnitScaler());
                        packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                        packageInfoBean.setSacler((int) unitsBean.getUnitScaler());
                        packageInfoBean.setUnitName(unitsBean.getUnitName());
                        packageInfoBean.setLevel(unitsBean.getLevel());
                        packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                        packageInfoBean.setDescription("("+totalPackages+lastUnitsBean.getUnitName()+")");
                        if (info.getChild() == null) {
                            ArrayList<WarePrint.PackageInfoBean> wp = new ArrayList<>();
                            info.setChild(wp);
                        }
                        info.getChild().add(packageInfoBean);
                        if (!packageInfoBean.isIsLeaf()) {
                            ScanTailBox(packageInfoBean, packageInfoBean.getSacler(),1,0,0);
                        }
                    }
                }
            }
        }
        return info;
    }
    //入库最小单位
    private WarePrint.PackageInfoBean smallUnit(int maxUnitNumber,int unitNumber,PrintUnit.DatasBean.UnitsBean unitsBean, int SmallestUnit) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        PrintUnit.DatasBean.UnitsBean packageUnit=null;
        PrintUnit.DatasBean.UnitsBean maxUnitsBean = null;
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).getLevel()==unitsBean.getLevel()+1){
                packageUnit=units.get(i);
            }
            if(units.get(i).getLevel()==units.size()-1){
                maxUnitsBean=units.get(i);
            }
        }
        WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();

        packageInfoBean.setMark(maxUnitsBean.getUnitName()+"-"+maxUnitNumber+packageUnit.getUnitName()+"-"+(unitNumber+1));

        packageInfoBean.setPname(packageUnit.getPName());
        packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
        packageInfoBean.setGoodName(unit.getDatas().getProductName());
        packageInfoBean.setModelCode(unit.getDatas().getModelCode());
        packageInfoBean.setStoreId(unit.getDatas().getStoreId());
        packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
        packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
        packageInfoBean.setId(getMyUUID());
        packageInfoBean.setUnitId(unitsBean.getId());
        packageInfoBean.setNumber(SmallestUnit);
        packageInfoBean.setGoodNumbers(packageUnit.getGoodNumber());
        packageInfoBean.setSacler((int) packageUnit.getUnitScaler());
        packageInfoBean.setUnitName(packageUnit.getUnitName());
        packageInfoBean.setLevel(packageUnit.getLevel());
        packageInfoBean.setIsLeaf(packageUnit.isIsLeaf());
        packageInfoBean.setDescription("("+SmallestUnit+unitsBean.getUnitName()+")");

        ArrayList<WarePrint.PackageInfoBean> wp = new ArrayList<>();
        for (int z = 1; z <= SmallestUnit; z++) {
            WarePrint.PackageInfoBean packageInfoBean1 = new WarePrint.PackageInfoBean();
            packageInfoBean1.setMark("尾箱" + unitsBean.getUnitName() + "-" + z);

            packageInfoBean1.setPname(unitsBean.getPName());
            packageInfoBean1.setBatchCode(unit.getDatas().getBatchNo());
            packageInfoBean1.setGoodName(unit.getDatas().getProductName());
            packageInfoBean1.setModelCode(unit.getDatas().getModelCode());
            packageInfoBean1.setStoreId(unit.getDatas().getStoreId());
            packageInfoBean1.setWorkPalceId(unit.getDatas().getWorkPalceId());
            packageInfoBean1.setSourceBillNo(unit.getDatas().getSourceBillNo());
            packageInfoBean1.setId(getMyUUID());
            packageInfoBean1.setUnitId(unitsBean.getId());
            packageInfoBean1.setNumber(1);
            packageInfoBean1.setGoodNumbers(unitsBean.getGoodNumber());
            packageInfoBean1.setSacler((int) unitsBean.getUnitScaler());
            packageInfoBean1.setUnitName(unitsBean.getUnitName());
            packageInfoBean1.setLevel(unitsBean.getLevel());
            packageInfoBean1.setIsLeaf(unitsBean.isIsLeaf());
            wp.add(packageInfoBean1);
        }
        packageInfoBean.setChild(wp);
        return packageInfoBean;
    }

    //打印最小单位
    private WarePrint.PackageInfoBean printSmallUnit(int maxUnitNumber,int unitNumber,PrintUnit.DatasBean.UnitsBean unitsBean, int SmallestUnit) {
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        PrintUnit.DatasBean.UnitsBean packageUnit=null;
        PrintUnit.DatasBean.UnitsBean maxUnitsBean = null;
        for (int i = 0; i < units.size(); i++) {
            if(units.get(i).getLevel()==unitsBean.getLevel()+1){
                packageUnit=units.get(i);
            }
            if(units.get(i).getLevel()==units.size()-1){
                maxUnitsBean=units.get(i);
            }
        }
        WarePrint.PackageInfoBean packageInfoBean = new WarePrint.PackageInfoBean();

        packageInfoBean.setMark(maxUnitsBean.getUnitName()+"-"+maxUnitNumber+packageUnit.getUnitName()+"-"+(unitNumber+1));

        packageInfoBean.setPname(packageUnit.getPName());
        packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
        packageInfoBean.setGoodName(unit.getDatas().getProductName());
        packageInfoBean.setModelCode(unit.getDatas().getModelCode());
        packageInfoBean.setStoreId(unit.getDatas().getStoreId());
        packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
        packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
        packageInfoBean.setId(getMyUUID());
        packageInfoBean.setUnitId(unitsBean.getId());
        packageInfoBean.setNumber(SmallestUnit);
        packageInfoBean.setGoodNumbers(packageUnit.getGoodNumber());
        packageInfoBean.setSacler((int) packageUnit.getUnitScaler());
        packageInfoBean.setUnitName(packageUnit.getUnitName());
        packageInfoBean.setLevel(packageUnit.getLevel());
        packageInfoBean.setIsLeaf(packageUnit.isIsLeaf());
        packageInfoBean.setDescription("("+SmallestUnit+unitsBean.getUnitName()+")");

        return packageInfoBean;
    }

    private String getMyUUID() {

        UUID uuid = UUID.randomUUID();

        String uniqueId = uuid.toString();

        return uniqueId;

    }
}