package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import project.mvp.com.socket.adapter.PackingAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Packing;
import project.mvp.com.socket.model.Printer;
import project.mvp.com.socket.model.PrinterModel;
import project.mvp.com.socket.utils.ShowToastTime;

public class PackingActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private ArrayList<Packing.DatasBean> mDatasBeans;
    private String mTargetIp;
    private Button btn_CustomOptions;
    private OptionsPickerView pvCustomOptions;
    private ArrayList<PrinterModel> printerItem = new ArrayList<>();
    private Packing mPacking;
    private String mGoodNumber;
    private String mLeave;
    private int mCompanyId;
    private int mPosition;
    private String mTx;
    private int mMachineId;
    private String mScanningName;
    private String mProductName;
    private String mBillNumber;
    private String mProductModel;
    private String mShumeipai;
    private String mCompanyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing);
        ButterKnife.bind(this);
        printerItem.clear();
        final Intent intent = getIntent();
        mGoodNumber = intent.getStringExtra("goodNumber");
        mLeave = intent.getStringExtra("leave");
        mCompanyId = intent.getIntExtra("companyId", 0);
        mTargetIp = intent.getStringExtra("targetIp");
        mMachineId = intent.getIntExtra("machineId",-1);
        mScanningName = intent.getStringExtra("scanningName");
        mProductName = intent.getStringExtra("productName");
        mBillNumber = intent.getStringExtra("billNumber");
        mProductModel = intent.getStringExtra("productModel");
        mShumeipai = intent.getStringExtra("shumeipai");
        mCompanyName = intent.getStringExtra("companyName");
        recyclerView.addItemDecoration(new DividerItemDecoration(PackingActivity.this, DividerItemDecoration.VERTICAL));
        mDatasBeans = new ArrayList<>();
        ArrayList<Packing.DatasBean> packing = intent.getParcelableArrayListExtra("packing");
        if (packing != null) {
            mDatasBeans.addAll(packing);
            tbMain.setTitle(packing.get(packing.size()-1).getUnitScaler()+""+packing.get(packing.size()-1).getUnitName()+""+packing.get(packing.size()-1).getPrinterName());
        }else{
            tbMain.setTitle(mProductName);
        }
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(PackingActivity.this));

        final Gson gson = new Gson();
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Unit/" + mLeave + "?companyid=" + mCompanyId + "&goodnumber=" + mGoodNumber).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Toast.makeText(PackingActivity.this, "错误" + e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response) {
                mPacking = gson.fromJson(response, Packing.class);
                if (mPacking.getCode() == 200) {
                    PackingAdapter adapter = new PackingAdapter(PackingActivity.this, mPacking.getDatas());

                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(new PackingAdapter.onItemClickListener() {
                        @Override
                        public void clickItem(int position) {
                            mPosition = position;
                            loadView.setVisibility(View.VISIBLE);
                            printerItem.clear();
                            OkHttpUtils.get().url(MyApplication.baseUrl+"api/Print?targetip=" + mTargetIp).build().execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e) {
                                    loadView.setVisibility(View.GONE);
                                    ShowToastTime.showTextToast(e.toString());
                                }

                                @Override
                                public void onResponse(String response) {
                                    loadView.setVisibility(View.GONE);
                                    Printer printer = gson.fromJson(response, Printer.class);

                                    for (int i = 0; i < printer.getDatas().size(); i++) {

                                        PrinterModel model = new PrinterModel();
                                        model.setPrinterName(printer.getDatas().get(i));
                                        printerItem.add(model);
                                    }
                                    initCustomOptionPicker();
                                    pvCustomOptions.show();
                                }
                            });
                        }
                    });
                } else if(mPacking.getCode() == 400){
                    finish();
                    Toast.makeText(PackingActivity.this, mPacking.getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent1=new Intent(PackingActivity.this,AddUnitActivity.class);
                    intent1.putExtra("goodNumbers",mGoodNumber);
                    intent1.putExtra("CompanyId",mCompanyId);
                    intent1.putExtra("ProductName",mProductName);
                    startActivity(intent1);
                }else if(mPacking.getCode() == 404){
                    finish();
                    Toast.makeText(PackingActivity.this, mPacking.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initCustomOptionPicker() {
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                if(printerItem.size()==0){
                    return;
                }
                //返回的分别是三个级别的选中位置
                mTx = printerItem.get(options1).getPickerViewText();
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
                                if (mPacking.getDatas().get(mPosition).isIsKingDee()) {
                                    mPacking.getDatas().get(mPosition).setPrinterName(mTx);
                                    mDatasBeans.add(mPacking.getDatas().get(mPosition));
                                    Intent intent1 = new Intent(MyApplication.getContext(), WarehouseActivity.class);
                                    intent1.putExtra("goodNumber", mGoodNumber);
                                    intent1.putExtra("leave", mPacking.getDatas().get(mPosition).getDetailId());
                                    intent1.putExtra("companyId", mCompanyId);
                                    intent1.putExtra("targetIp", mTargetIp);
                                    intent1.putParcelableArrayListExtra("packing", mDatasBeans);
                                    intent1.putExtra("machineId",mMachineId);
                                    intent1.putExtra("scanningName",mScanningName);
                                    intent1.putExtra("productName",mProductName);
                                    intent1.putExtra("billNumber",mBillNumber);
                                    intent1.putExtra("productModel",mProductModel);
                                    intent1.putExtra("shumeipai",mShumeipai);
                                    intent1.putExtra("companyName",mCompanyName);
                                    startActivity(intent1);
                                } else {
                                    mPacking.getDatas().get(mPosition).setPrinterName(mTx);
                                    mDatasBeans.add(mPacking.getDatas().get(mPosition));

                                    Intent intent1 = new Intent(MyApplication.getContext(), PackingActivity.class);
                                    intent1.putExtra("goodNumber", mGoodNumber);
                                    intent1.putExtra("leave", mPacking.getDatas().get(mPosition).getDetailId());
                                    intent1.putExtra("companyId", mCompanyId);
                                    intent1.putParcelableArrayListExtra("packing", mDatasBeans);
                                    intent1.putExtra("targetIp", mTargetIp);
                                    intent1.putExtra("machineId",mMachineId);
                                    intent1.putExtra("scanningName",mScanningName);
                                    intent1.putExtra("productName",mProductName);
                                    intent1.putExtra("billNumber",mBillNumber);
                                    intent1.putExtra("productModel",mProductModel);
                                    intent1.putExtra("shumeipai",mShumeipai);
                                    intent1.putExtra("companyName",mCompanyName);
                                    startActivity(intent1);
                                }
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

        pvCustomOptions.setPicker(printerItem);//添加数据
    }
}
