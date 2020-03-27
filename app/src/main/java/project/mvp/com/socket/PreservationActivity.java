package project.mvp.com.socket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Configure;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.Packing;
import project.mvp.com.socket.utils.ShowToastTime;

public class PreservationActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.shumei)
    TextView shumei;
    @Bind(R.id.saomiao)
    TextView saomiao;
    @Bind(R.id.productName)
    TextView productName;
    @Bind(R.id.baozhuang)
    TextView baozhuang;
    @Bind(R.id.printer_Name)
    TextView printerName;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.baocun)
    Button baocun;
    @Bind(R.id.configure_name)
    EditText configureName;
    @Bind(R.id.store_name)
    TextView storeName1;
    @Bind(R.id.store_place)
    TextView storePlace;
    @Bind(R.id.work_shop)
    TextView workShop;
    @Bind(R.id.production_batch)
    TextView batchNumber;
    private Gson mGson;
    int  mYear;
    int  mMonth;
    int mDay;
    private Configure configure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preservation);
        ButterKnife.bind(this);
        //初始化日期
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        mGson = new Gson();
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        String shumeipai = intent.getStringExtra("shumeipai");
        String goodNumber = intent.getStringExtra("goodNumber");
        String targetIp = intent.getStringExtra("targetIp");
        int machineId = intent.getIntExtra("machineId", -1);
        String scanningName = intent.getStringExtra("scanningName");
        String productName1 = intent.getStringExtra("productName");
        String billNumber = intent.getStringExtra("billNumber");
        String productModel = intent.getStringExtra("productModel");
        String storePlaceId = intent.getStringExtra("storePlaceId");
        String storePlaceName = intent.getStringExtra("storePlaceName");
        String storeId = intent.getStringExtra("storeId");
        String storeName = intent.getStringExtra("storeName");
        int companyId = intent.getIntExtra("companyId", -1);
        ArrayList<Packing.DatasBean> packing = intent.getParcelableArrayListExtra("packing");
        int workshopId = intent.getIntExtra("workshopId", -1);
        String workshopName = intent.getStringExtra("workshopName");
        shumei.setText(shumeipai);
        saomiao.setText(scanningName);
        productName.setText(productName1);
        storePlace.setText(storePlaceName);
        storeName1.setText(storeName);
        workShop.setText(workshopName);
        String pack = "";
        String printer = "";


        ArrayList<Configure.UnitInfosBean> unitInfos = new ArrayList<>();
        for (int i = 0; i < packing.size(); i++) {
            Configure.UnitInfosBean bean = new Configure.UnitInfosBean();
            bean.setId(packing.get(i).getId());
            bean.setPrintName(packing.get(i).getPrinterName());
            unitInfos.add(bean);
            if (i == packing.size() - 1) {
                pack += packing.get(i).getUnitName();
                printer += packing.get(i).getPrinterName();
            } else {
                pack += packing.get(i).getUnitName() + "->";
                printer += packing.get(i).getPrinterName() + "->";
            }
        }
        baozhuang.setText(pack);
        printerName.setText(printer);
        configure = new Configure();
        configure.setGoodNumber(goodNumber);
        configure.setFicmobillNo(billNumber);
        configure.setFproductModel(productModel);
        configure.setMachineId(machineId);
        configure.setFproductName(productName1);
        configure.setScanName(scanningName);
        configure.setUnitInfos(unitInfos);
        configure.setStoreId(storeId);
        configure.setStorePlaceId(storePlaceId);
        configure.setCompanyId(companyId);
        configure.setFworkShopId(workshopId);
        Date date=new Date();//此时date为当前的时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyMMdd");
        String productNumber= "GP"+dateFormat.format(date)+"A";
        batchNumber.setText(productNumber);
        configure.setBatchNo(productNumber);
        batchNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PreservationActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
            }
        });
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String configureName1 = configureName.getText().toString();
                if (configureName1.equals("")) {
                    Toast.makeText(PreservationActivity.this, "请填写配置名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                configure.setRemark(configureName1);
                loadView.setVisibility(View.VISIBLE);
                OkHttpUtils.postString()
                        .url(MyApplication.baseUrl+"api/WorkBrenchConfig")
                        .content(new Gson().toJson(configure))
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
                                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                                if (configureOptions.getCode() == 200) {
                                    Intent intent1 = new Intent(PreservationActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                } else {
                                    ShowToastTime.showTextToast(configureOptions.getMessage());
                                }
                            }
                        });
            }
        });
    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year=Integer.parseInt(String.valueOf(year).substring(2));
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("0").
                            append(mMonth + 1).append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("0").
                            append(mMonth + 1).append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).
                            append(mMonth + 1).append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).
                            append(mMonth + 1).append(mDay).toString();
                }

            }
            days="GP"+days+"A";
            batchNumber.setText(days);
            configure.setBatchNo(days);
        }
    };
}
