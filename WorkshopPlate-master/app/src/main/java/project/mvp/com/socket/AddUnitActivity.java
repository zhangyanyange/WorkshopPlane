package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.AddUnit;
import project.mvp.com.socket.model.BasicUnit;
import project.mvp.com.socket.model.Unit;

public class AddUnitActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.new_packing_name)
    EditText newPackingName;
    @Bind(R.id.conversion_rate)
    EditText conversionRate;
    @Bind(R.id.basic_packaging)
    TextView basicPackaging;
    @Bind(R.id.ll_packaging)
    LinearLayout llPackaging;
    @Bind(R.id.baocun_jixu)
    Button baocunJixu;
    @Bind(R.id.baocun_end)
    Button baocunEnd;

    private OptionsPickerView pvCustomOptions;
    private ArrayList<BasicUnit> units = new ArrayList<>();
    private String mTx;
    private Unit mUnit;
    private BasicUnit mBasicUnit;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String productName = intent.getStringExtra("ProductName");
        final int companyId = intent.getIntExtra("CompanyId", -1);
        final String goodNumbers = intent.getStringExtra("goodNumbers");
        mGson = new Gson();
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tbMain.setTitle(productName + "添加包裝");

        baocunJixu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPacking = newPackingName.getText().toString();
                String rate = conversionRate.getText().toString();
                if(newPacking.equals("")||rate.equals("")){
                    Toast.makeText(AddUnitActivity.this, "新包装名称不能为空或者换算率不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddUnit addUnit=new AddUnit();
                addUnit.setCompanyId(companyId);
                addUnit.setDetailId(mBasicUnit.getId());
                addUnit.setGoodNumber(goodNumbers);
                addUnit.setUnitName(newPacking);
                addUnit.setUnitScaler(Double.parseDouble(rate));
                //添加包装单位
                textView.setText("添加单位");
                loadView.setVisibility(View.VISIBLE);
                OkHttpUtils.postString()
                        .url(MyApplication.baseUrl+"api/Unit")
                        .content(mGson.toJson(addUnit))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                loadView.setVisibility(View.GONE);
                                Toast.makeText(AddUnitActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response) {
                                loadView.setVisibility(View.GONE);
                                System.out.println(response);
                                finish();
                                Intent intent1=new Intent(AddUnitActivity.this,AddUnitActivity.class);
                                intent1.putExtra("goodNumbers",goodNumbers);
                                intent1.putExtra("CompanyId",companyId);
                                intent1.putExtra("ProductName",productName);
                                startActivity(intent1);
                            }
                        });
            }
        });

        baocunEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPacking = newPackingName.getText().toString();
                String rate = conversionRate.getText().toString();
                if(newPacking.equals("")||rate.equals("")){
                    Toast.makeText(AddUnitActivity.this, "新包装名称不能为空或者换算率不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                AddUnit addUnit=new AddUnit();
                addUnit.setCompanyId(companyId);
                addUnit.setDetailId(mBasicUnit.getId());
                addUnit.setGoodNumber(goodNumbers);
                addUnit.setUnitName(newPacking);
                addUnit.setUnitScaler(Double.parseDouble(rate));

                textView.setText("添加单位");
                loadView.setVisibility(View.VISIBLE);
                OkHttpUtils.postString()
                        .url(MyApplication.baseUrl+"api/Unit")
                        .content(mGson.toJson(addUnit))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                loadView.setVisibility(View.GONE);
                                Toast.makeText(AddUnitActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response) {
                                loadView.setVisibility(View.GONE);

                                finish();
                            }
                        });
            }
        });

        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Unit/CommUnits/" + companyId + "?goodnumber=" + goodNumbers).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(AddUnitActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                mUnit = mGson.fromJson(response, Unit.class);
                if(mUnit.getCode()==200){
                    BasicUnit unit=new BasicUnit();
                    unit.setId(mUnit.getDatas().get(0).getId());
                    unit.setUnitName(mUnit.getDatas().get(0).getUnitName());

                    mBasicUnit=unit;
                    basicPackaging.setText(mUnit.getDatas().get(0).getUnitName());

                    for (int i = 0; i <mUnit.getDatas().size(); i++) {
                        BasicUnit unit1=new BasicUnit();
                        unit1.setId(mUnit.getDatas().get(i).getId());
                        unit1.setUnitName(mUnit.getDatas().get(i).GetFullName());
                        units.add(unit1);
                    }
                }else{
                    Toast.makeText(AddUnitActivity.this, mUnit.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        llPackaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initCustomOptionPicker();
                pvCustomOptions.show();
            }
        });
    }


    private void initCustomOptionPicker() {
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if(units.size()==0){
                    return;
                }
                mBasicUnit = units.get(options1);
                System.out.println(mGson.toJson(mBasicUnit));
                //返回的分别是三个级别的选中位置
                mTx = units.get(options1).getPickerViewText();

                basicPackaging.setText(mTx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();

                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });


                    }
                })
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();

        pvCustomOptions.setPicker(units);//添加数据
    }
}
