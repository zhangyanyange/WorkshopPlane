package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Child;
import project.mvp.com.socket.model.Print;
import project.mvp.com.socket.model.PrintUnit;
import project.mvp.com.socket.model.WarePrint;
import project.mvp.com.socket.utils.FileUtils;
import project.mvp.com.socket.utils.LogUtils;

public class WareActivity extends AppCompatActivity {

    @Bind(R.id.et)
    EditText et;
    @Bind(R.id.btn)
    Button btn;
    private PrintUnit unit;
    private PrintUnit.DatasBean.UnitsBean unitsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int productID = intent.getIntExtra("productID", -1);

        OkHttpUtils
                .get()
                .url(MyApplication.baseUrl + "api/WorkBrenchConfig/ConfList/" + productID).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Gson gson=new Gson();
                        unit = gson.fromJson(response, PrintUnit.class);
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number=Integer.parseInt(et.getText().toString());
                int totalPackages=1;
                List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
                for(int i=0;i<unit.getDatas().getUnits().size();i++){
                    totalPackages=totalPackages*(int) units.get(i).getUnitScaler();
                }
                int mag=number/totalPackages;
                ArrayList<WarePrint.PackageInfoBean> list=new ArrayList<>();
                for(int z=1;z<=mag;z++){
                    WarePrint.PackageInfoBean packageInfoBean1=null;
                    WarePrint.PackageInfoBean infoBean = scanRecur(packageInfoBean1,z);
                    list.add(infoBean);
                }
               int Remaining= number%totalPackages;

                PrintUnit.DatasBean.UnitsBean unitsBean=null;
                int RemainingMag=1;
                for(int i=0;i<units.size();i++){
                    if(!(units.get(i).getLevel()==units.size()-1)){
                        RemainingMag=RemainingMag*(int) units.get(i).getUnitScaler();
                        if(units.get(i).getLevel()==0){
                            unitsBean=units.get(i);
                        }
                    }
                }
                //剩余循环次数
                if(Remaining!=0){
                    //生成尾箱数据
                    int magR= Remaining/RemainingMag;
                    int SmallestUnit=Remaining%RemainingMag;
                    WarePrint.PackageInfoBean packageInfoBean2=null;
                    WarePrint.PackageInfoBean infoBean =ScanTailBox(packageInfoBean2,magR);
                    list.add(infoBean);
                //生成剩余最小单位包装的个数
//                ArrayList<WarePrint.PackageInfoBean> packageInfoBeans = smallUnit(unitsBean, SmallestUnit);
//                infoBean.getChild().addAll(packageInfoBeans);
                }


                String x = new Gson().toJson(list);

                FileUtils.writeFile(x,FileUtils.getExternalStoragePath(),true);
                System.out.println(FileUtils.getExternalStoragePath());
            }
        });


    }
    //整箱入库处理方式
    private WarePrint.PackageInfoBean recur(WarePrint.PackageInfoBean info,int g){
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if(info==null){
            PrintUnit.DatasBean.UnitsBean unitsBean=null;
            for(int i=0;i<units.size();i++){
              if(units.get(i).getLevel()==units.size()-1){
                  unitsBean=units.get(i);
                  break;
              }
            }
               info=new WarePrint.PackageInfoBean();
                info.setMark(unitsBean.getUnitName()+"-"+g);

                info.setPname(unitsBean.getPName());
                info.setBatchCode(unit.getDatas().getBatchNo());
                info.setGoodName(unit.getDatas().getProductName());
                info.setModelCode(unit.getDatas().getModelCode());
                info.setStoreId(unit.getDatas().getStoreId());
                info.setWorkPalceId(unit.getDatas().getWorkPalceId());
                info.setSourceBillNo(unit.getDatas().getSourceBillNo());
                info.setId(getMyUUID());
                info.setUnitId(unitsBean.getId());
                info.setNumber(1);
                info.setGoodNumbers(unitsBean.getGoodNumber());
                info.setSacler((int)unitsBean.getUnitScaler());
                info.setUnitName(unitsBean.getUnitName());
                info.setIsLeaf(unitsBean.isIsLeaf());
                info.setLevel(unitsBean.getLevel());
                if(!info.isIsLeaf()){
                    recur(info,1);
                }
        }else{
            if(!info.isIsLeaf()){
                for(int i=1;i<=info.getSacler();i++){
                    PrintUnit.DatasBean.UnitsBean unitsBean=null;
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==info.getLevel()){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==unitsBean.getLevel()-1){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    WarePrint.PackageInfoBean packageInfoBean=new WarePrint.PackageInfoBean();
                    packageInfoBean.setMark(info.getMark()+unitsBean.getUnitName()+"-"+i);

                    packageInfoBean.setPname(unitsBean.getPName());
                    packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                    packageInfoBean.setGoodName(unit.getDatas().getProductName());
                    packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                    packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                    packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                    packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                    packageInfoBean.setId(getMyUUID());
                    packageInfoBean.setUnitId(unitsBean.getId());
                    packageInfoBean.setNumber(1);
                    packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                    packageInfoBean.setSacler((int)unitsBean.getUnitScaler());
                    packageInfoBean.setUnitName(unitsBean.getUnitName());
                    packageInfoBean.setLevel(unitsBean.getLevel());
                    packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                    if(info.getChild()==null){
                        ArrayList<WarePrint.PackageInfoBean>wp=new ArrayList<>();
                        info.setChild(wp);
                    }
                    info.getChild().add(packageInfoBean);
                    if(!packageInfoBean.isIsLeaf()){
                        recur(packageInfoBean,1);
                    }
                }
            }
        }
        return info;
    }

    //整箱扫码处理方式
    private WarePrint.PackageInfoBean scanRecur(WarePrint.PackageInfoBean info,int g){
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if(info==null){
            PrintUnit.DatasBean.UnitsBean unitsBean=null;
            for(int i=0;i<units.size();i++){
                if(units.get(i).getLevel()==units.size()-1){
                    unitsBean=units.get(i);
                    break;
                }
            }
            info=new WarePrint.PackageInfoBean();
            info.setMark(unitsBean.getUnitName()+"-"+g);

            info.setPname(unitsBean.getPName());
            info.setBatchCode(unit.getDatas().getBatchNo());
            info.setGoodName(unit.getDatas().getProductName());
            info.setModelCode(unit.getDatas().getModelCode());
            info.setStoreId(unit.getDatas().getStoreId());
            info.setWorkPalceId(unit.getDatas().getWorkPalceId());
            info.setSourceBillNo(unit.getDatas().getSourceBillNo());
            info.setId(getMyUUID());
            info.setUnitId(unitsBean.getId());
            info.setNumber(1);
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int)unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if(!info.isIsLeaf()){
                scanRecur(info,1);
            }
        }else{
            if(!info.isIsLeaf()){
                if(info.getLevel()!=1){//不是倒数第二等级
                for(int i=1;i<=info.getSacler();i++){
                    PrintUnit.DatasBean.UnitsBean unitsBean=null;
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==info.getLevel()){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==unitsBean.getLevel()-1){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    WarePrint.PackageInfoBean packageInfoBean=new WarePrint.PackageInfoBean();
                    packageInfoBean.setMark(info.getMark()+unitsBean.getUnitName()+"-"+i);

                    packageInfoBean.setPname(unitsBean.getPName());
                    packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                    packageInfoBean.setGoodName(unit.getDatas().getProductName());
                    packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                    packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                    packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                    packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                    packageInfoBean.setId(getMyUUID());
                    packageInfoBean.setUnitId(unitsBean.getId());
                    packageInfoBean.setNumber(1);
                    packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                    packageInfoBean.setSacler((int)unitsBean.getUnitScaler());
                    packageInfoBean.setUnitName(unitsBean.getUnitName());
                    packageInfoBean.setLevel(unitsBean.getLevel());
                    packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                    if(info.getChild()==null){
                        ArrayList<WarePrint.PackageInfoBean>wp=new ArrayList<>();
                        info.setChild(wp);
                    }
                    info.getChild().add(packageInfoBean);
                    if(!packageInfoBean.isIsLeaf()){
                        scanRecur(packageInfoBean,1);
                    }
                }
            }
          }
        }
        return info;
    }

    //尾箱入库处理方式
    private WarePrint.PackageInfoBean TailBox(WarePrint.PackageInfoBean info,int g){
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if(info==null){
            PrintUnit.DatasBean.UnitsBean unitsBean=null;
            for(int i=0;i<units.size();i++){
                if(units.get(i).getLevel()==units.size()-1){
                    unitsBean=units.get(i);
                    break;
                }
            }
            info=new WarePrint.PackageInfoBean();
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
            info.setNumber(1);
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int)unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if(!info.isIsLeaf()){
                TailBox(info,g);
            }
        }else{
            if(!info.isIsLeaf()){
                for(int i=1;i<=g;i++){
                    PrintUnit.DatasBean.UnitsBean unitsBean=null;
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==info.getLevel()){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    for(int q=0;q<units.size();q++){
                        if(units.get(q).getLevel()==unitsBean.getLevel()-1){
                            unitsBean=units.get(q);
                            break;
                        }
                    }
                    WarePrint.PackageInfoBean packageInfoBean=new WarePrint.PackageInfoBean();
                    packageInfoBean.setMark(info.getMark()+unitsBean.getUnitName()+"-"+i);

                    packageInfoBean.setPname(unitsBean.getPName());
                    packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                    packageInfoBean.setGoodName(unit.getDatas().getProductName());
                    packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                    packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                    packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                    packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                    packageInfoBean.setId(getMyUUID());
                    packageInfoBean.setUnitId(unitsBean.getId());
                    packageInfoBean.setNumber(1);
                    packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                    packageInfoBean.setSacler((int)unitsBean.getUnitScaler());
                    packageInfoBean.setUnitName(unitsBean.getUnitName());
                    packageInfoBean.setLevel(unitsBean.getLevel());
                    packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                    if(info.getChild()==null){
                        ArrayList<WarePrint.PackageInfoBean>wp=new ArrayList<>();
                        info.setChild(wp);
                    }
                    info.getChild().add(packageInfoBean);
                    if(!packageInfoBean.isIsLeaf()){
                        TailBox(packageInfoBean,packageInfoBean.getSacler());
                    }
                }
            }
        }
        return info;
    }

    //尾箱扫码处理方式
    private WarePrint.PackageInfoBean ScanTailBox(WarePrint.PackageInfoBean info,int g){
        List<PrintUnit.DatasBean.UnitsBean> units = unit.getDatas().getUnits();
        if(info==null){
            PrintUnit.DatasBean.UnitsBean unitsBean=null;
            for(int i=0;i<units.size();i++){
                if(units.get(i).getLevel()==units.size()-1){
                    unitsBean=units.get(i);
                    break;
                }
            }
            info=new WarePrint.PackageInfoBean();
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
            info.setNumber(1);
            info.setGoodNumbers(unitsBean.getGoodNumber());
            info.setSacler((int)unitsBean.getUnitScaler());
            info.setUnitName(unitsBean.getUnitName());
            info.setIsLeaf(unitsBean.isIsLeaf());
            info.setLevel(unitsBean.getLevel());
            if(!info.isIsLeaf()){
                ScanTailBox(info,g);
            }
        }else{
            if(!info.isIsLeaf()){
                if(info.getLevel()!=1){
                    for(int i=1;i<=g;i++){
                        PrintUnit.DatasBean.UnitsBean unitsBean=null;
                        for(int q=0;q<units.size();q++){
                            if(units.get(q).getLevel()==info.getLevel()){
                                unitsBean=units.get(q);
                                break;
                            }
                        }
                        for(int q=0;q<units.size();q++){
                            if(units.get(q).getLevel()==unitsBean.getLevel()-1){
                                unitsBean=units.get(q);
                                break;
                            }
                        }
                        WarePrint.PackageInfoBean packageInfoBean=new WarePrint.PackageInfoBean();
                        packageInfoBean.setMark(info.getMark()+unitsBean.getUnitName()+"-"+i);

                        packageInfoBean.setPname(unitsBean.getPName());
                        packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
                        packageInfoBean.setGoodName(unit.getDatas().getProductName());
                        packageInfoBean.setModelCode(unit.getDatas().getModelCode());
                        packageInfoBean.setStoreId(unit.getDatas().getStoreId());
                        packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
                        packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
                        packageInfoBean.setId(getMyUUID());
                        packageInfoBean.setUnitId(unitsBean.getId());
                        packageInfoBean.setNumber(1);
                        packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
                        packageInfoBean.setSacler((int)unitsBean.getUnitScaler());
                        packageInfoBean.setUnitName(unitsBean.getUnitName());
                        packageInfoBean.setLevel(unitsBean.getLevel());
                        packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
                        if(info.getChild()==null){
                            ArrayList<WarePrint.PackageInfoBean>wp=new ArrayList<>();
                            info.setChild(wp);
                        }
                        info.getChild().add(packageInfoBean);
                        if(!packageInfoBean.isIsLeaf()){
                            ScanTailBox(packageInfoBean,packageInfoBean.getSacler());
                        }
                    }
                }
            }
        }
        return info;
    }
    private ArrayList<WarePrint.PackageInfoBean> smallUnit(PrintUnit.DatasBean.UnitsBean unitsBean,int SmallestUnit){
        ArrayList<WarePrint.PackageInfoBean>wp=new ArrayList<>();
        for(int z=1;z<=SmallestUnit;z++){
            WarePrint.PackageInfoBean packageInfoBean=new WarePrint.PackageInfoBean();
            packageInfoBean.setMark("尾箱"+unitsBean.getUnitName()+"-"+z);

            packageInfoBean.setPname(unitsBean.getPName());
            packageInfoBean.setBatchCode(unit.getDatas().getBatchNo());
            packageInfoBean.setGoodName(unit.getDatas().getProductName());
            packageInfoBean.setModelCode(unit.getDatas().getModelCode());
            packageInfoBean.setStoreId(unit.getDatas().getStoreId());
            packageInfoBean.setWorkPalceId(unit.getDatas().getWorkPalceId());
            packageInfoBean.setSourceBillNo(unit.getDatas().getSourceBillNo());
            packageInfoBean.setId(getMyUUID());
            packageInfoBean.setUnitId(unitsBean.getId());
            packageInfoBean.setNumber(1);
            packageInfoBean.setGoodNumbers(unitsBean.getGoodNumber());
            packageInfoBean.setSacler((int)unitsBean.getUnitScaler());
            packageInfoBean.setUnitName(unitsBean.getUnitName());
            packageInfoBean.setLevel(unitsBean.getLevel());
            packageInfoBean.setIsLeaf(unitsBean.isIsLeaf());
            wp.add(packageInfoBean);
        }
        return wp;

    }
    private String getMyUUID() {

        UUID uuid = UUID.randomUUID();

        String uniqueId = uuid.toString();

        return uniqueId;

    }


}
