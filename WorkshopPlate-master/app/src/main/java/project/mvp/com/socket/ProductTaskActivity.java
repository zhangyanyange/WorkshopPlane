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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.CompanyAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.fragment.ProductTaskFragment;
import project.mvp.com.socket.model.Company;
import project.mvp.com.socket.model.LoadDone;
import project.mvp.com.socket.model.MessageEvent;

public class ProductTaskActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
    @Bind(R.id.tb_main)
    Toolbar tbMain;

    private CompanyAdapter adapter;
    private ProductTaskFragment myFragment;
    public static int mPosition = 0;
    private Gson mGson;
    private Company mCompany;
    private MessageEvent mEvent;

    private boolean isDoneFirst = false;
    private String mTargetIp;
    private int mMachineId;
    private String mScanningName;
    private String mShumeipai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_task);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mTargetIp = intent.getStringExtra("targetIp");
        mMachineId = intent.getIntExtra("machineId",-1);
        mScanningName = intent.getStringExtra("scanningName");
        mShumeipai = intent.getStringExtra("shumeipai");
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tbMain.setTitle(mScanningName);
        mPosition = 0;
        EventBus.getDefault().register(this);
        mGson = new Gson();
        mEvent = new MessageEvent();
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Company").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.INVISIBLE);
                Toast.makeText(ProductTaskActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.INVISIBLE);
                mCompany = mGson.fromJson(response, Company.class);

                adapter = new CompanyAdapter(ProductTaskActivity.this, mCompany);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(ProductTaskActivity.this);

                //创建MyFragment对象
                myFragment = new ProductTaskFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, myFragment);
                //通过bundle传值给MyFragment
                Bundle bundle = new Bundle();
                bundle.putInt(ProductTaskFragment.TAG, mCompany.getDatas().get(mPosition).getId());
                bundle.putString("companyName",mCompany.getDatas().get(mPosition).getCompanyName());
                bundle.putString("targetIp",mTargetIp);
                bundle.putInt("machineId",mMachineId);
                bundle.putString("scanningName",mScanningName);
                bundle.putString("shumeipai",mShumeipai);
                myFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoadDone event) {
        isDoneFirst = event.isLoadDone();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (isDoneFirst) {
            //拿到当前位置
            mPosition = position;
            //即使刷新adapter
            adapter.notifyDataSetChanged();
            mEvent.setCpmpanyId(mCompany.getDatas().get(position).getId());
            mEvent.setCompanyName(mCompany.getDatas().get(position).getCompanyName());
            EventBus.getDefault().post(mEvent);
        } else {
            Toast.makeText(ProductTaskActivity.this, "请等第一个视图加载完毕", Toast.LENGTH_SHORT).show();
        }

    }
}

