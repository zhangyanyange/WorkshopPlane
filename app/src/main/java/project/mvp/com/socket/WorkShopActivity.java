package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.WorkShopAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Packing;
import project.mvp.com.socket.model.WorkShop;
import project.mvp.com.socket.utils.ShowToastTime;

public class WorkShopActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private String mShumeipai;
    private String mGoodNumber;
    private String mTargetIp;
    private int mMachineId;
    private String mScanningName;
    private String mMProductName1;
    private String mBillNumber;
    private String mProductModel;
    private String mStorePlaceId;
    private String mStorePlaceName;
    private String mStoreId;
    private String mStoreName;
    private int mCompanyId;
    private ArrayList<Packing.DatasBean> mPacking;
    private Gson mGson;
    private String mCompanyName;
    private WorkShop mWorkShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_shop);
        ButterKnife.bind(this);
        tbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        mShumeipai = intent.getStringExtra("shumeipai");
        mGoodNumber = intent.getStringExtra("goodNumber");
        mTargetIp = intent.getStringExtra("targetIp");
        mMachineId = intent.getIntExtra("machineId", -1);
        mScanningName = intent.getStringExtra("scanningName");
        mMProductName1 = intent.getStringExtra("productName");
        mBillNumber = intent.getStringExtra("billNumber");
        mProductModel = intent.getStringExtra("productModel");
        mStorePlaceId = intent.getStringExtra("storePlaceId");
        mStorePlaceName = intent.getStringExtra("storePlaceName");
        mStoreId = intent.getStringExtra("storeId");
        mStoreName = intent.getStringExtra("storeName");
        mCompanyName = intent.getStringExtra("companyName");
        mCompanyId = intent.getIntExtra("companyId", -1);
        mPacking = intent.getParcelableArrayListExtra("packing");
        tbMain.setTitle(mStoreName+mStorePlaceName);
        mGson = new Gson();

        recyclerView.setLayoutManager(new LinearLayoutManager(WorkShopActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(WorkShopActivity.this, DividerItemDecoration.VERTICAL));
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Store/api/Store/WorkShop?companyid="+mCompanyId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                mWorkShop = mGson.fromJson(response, WorkShop.class);
                if(mWorkShop.getCode()==200){
                    WorkShopAdapter adapter=new WorkShopAdapter(WorkShopActivity.this, mWorkShop.getDatas());
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(new WorkShopAdapter.onItemClickListener() {
                        @Override
                        public void clickItem(int position) {
                            Intent intent = new Intent(MyApplication.getContext(), PreservationActivity.class);
                            intent.putExtra("goodNumber", mGoodNumber);
                            intent.putExtra("companyId", mCompanyId);
                            intent.putExtra("targetIp", mTargetIp);
                            intent.putParcelableArrayListExtra("packing", mPacking);
                            intent.putExtra("machineId",mMachineId);
                            intent.putExtra("scanningName",mScanningName);
                            intent.putExtra("productName",mMProductName1);
                            intent.putExtra("billNumber",mBillNumber);
                            intent.putExtra("productModel",mProductModel);
                            intent.putExtra("shumeipai",mShumeipai);
                            intent.putExtra("companyName",mCompanyName);
                            intent.putExtra("storeId",mStoreId);
                            intent.putExtra("storeName",mStoreName);
                            intent.putExtra("companyId",mCompanyId);
                            intent.putExtra("storePlaceId",mStorePlaceId);
                            intent.putExtra("storePlaceName",mStorePlaceName);
                            intent.putExtra("workshopId",mWorkShop.getDatas().get(position).getWorkShopId());
                            intent.putExtra("workshopName",mWorkShop.getDatas().get(position).getWorkShopName());
                            startActivity(intent);
                        }
                    });
                }else{
                    ShowToastTime.showTextToast(mWorkShop.getMessage());
                }
            }
        });
    }
}
