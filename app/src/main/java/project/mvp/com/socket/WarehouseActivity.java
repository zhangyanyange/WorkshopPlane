package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.StoreAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.fragment.StorePlaceFragment;
import project.mvp.com.socket.model.Packing;
import project.mvp.com.socket.model.Store;
import project.mvp.com.socket.model.StoreEvent;
import project.mvp.com.socket.model.StoreLoadDone;

public class WarehouseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private Gson mGson;

    public static int storePosition = 0;
    private String mShumeipai;
    private String mGoodNumber;
    private String mTargetIp;
    private int mMachineId;
    private String mScanningName;
    private String mProductName1;
    private String mBillNumber;
    private String mProductModel;
    private String mCompanyName;
    private ArrayList<Packing.DatasBean> mPacking;
    private int mCompanyId;
    private Store mStore;
    private StoreAdapter mAdapter;

    private boolean isDoneFirst;
    private StoreEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mEvent = new StoreEvent();
        Intent intent = getIntent();
        mShumeipai = intent.getStringExtra("shumeipai");
        mGoodNumber = intent.getStringExtra("goodNumber");
        mTargetIp = intent.getStringExtra("targetIp");
        mMachineId = intent.getIntExtra("machineId", -1);
        mScanningName = intent.getStringExtra("scanningName");
        mProductName1 = intent.getStringExtra("productName");
        mBillNumber = intent.getStringExtra("billNumber");
        mProductModel = intent.getStringExtra("productModel");
        mCompanyName = intent.getStringExtra("companyName");
        mPacking = intent.getParcelableArrayListExtra("packing");
        mCompanyId = intent.getIntExtra("companyId", -1);
        tbMain.setTitle(mCompanyName + "仓库");
        tbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        storePosition = 0;
        initView();
    }

    private void initView() {
        loadView.setVisibility(View.VISIBLE);
        mGson = new Gson();

        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Store/api/Store/Store?companyid=" + mCompanyId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.INVISIBLE);
                Toast.makeText(WarehouseActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.INVISIBLE);
                mStore = mGson.fromJson(response, Store.class);
                mAdapter = new StoreAdapter(WarehouseActivity.this, mStore);

                listview.setAdapter(mAdapter);

                listview.setOnItemClickListener(WarehouseActivity.this);

                //创建MyFragment对象
                StorePlaceFragment fragment = new StorePlaceFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                //通过bundle传值给MyFragment
                Bundle bundle = new Bundle();
                bundle.putString("shumeipai", mShumeipai);
                bundle.putString("goodNumber", mGoodNumber);
                bundle.putString("targetIp", mTargetIp);
                bundle.putInt("machineId", mMachineId);
                bundle.putString("scanningName", mScanningName);
                bundle.putString("productName", mProductName1);
                bundle.putString("billNumber", mBillNumber);
                bundle.putString("productModel", mProductModel);
                bundle.putString("companyName", mCompanyName);
                bundle.putInt("companyId", mCompanyId);
                bundle.putParcelableArrayList("packing", mPacking);
                bundle.putString("storeId", mStore.getDatas().get(0).getStoreId());
                bundle.putString("storeName", mStore.getDatas().get(0).getStoreName());
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StoreLoadDone event) {
        isDoneFirst = event.isLoadDone();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isDoneFirst) {
            //拿到当前位置
            storePosition = position;
            //即使刷新adapter
            mAdapter.notifyDataSetChanged();
            mEvent.setStoreId(mStore.getDatas().get(position).getStoreId());
            mEvent.setStoreName(mStore.getDatas().get(position).getStoreName());
            EventBus.getDefault().post(mEvent);
        } else {
            Toast.makeText(WarehouseActivity.this, "请等第一个视图加载完毕", Toast.LENGTH_SHORT).show();
        }

    }
}
