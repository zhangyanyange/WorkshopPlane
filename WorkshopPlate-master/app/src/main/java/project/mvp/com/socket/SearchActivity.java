package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.ProductTaskAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ProductTask;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.tv_hint)
    EditText tvHint;
    @Bind(R.id.tv_confim)
    TextView tvConfim;
    @Bind(R.id.pulltoRefresh)
    RecyclerView pulltoRefresh;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.tb_main)
    Toolbar tbMain;
    private Gson mGson;
    private String mTargetIp;
    private String mCompanyName;
    private String mShumeipai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final int companyId = intent.getIntExtra("companyId", 0);
        mTargetIp = intent.getStringExtra("targetIp");
        final int machineId = intent.getIntExtra("machineId", -1);
        final String scanningName = intent.getStringExtra("scanningName");
        mCompanyName = intent.getStringExtra("companyName");
        mShumeipai = intent.getStringExtra("shumeipai");
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mGson = new Gson();
        pulltoRefresh.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        pulltoRefresh.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        tvConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadView.setVisibility(View.VISIBLE);
                String s = tvHint.getText().toString();
                if (s.equals("")) {
                    Toast.makeText(SearchActivity.this, "请在输入框中传值", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取所有生产单号
                OkHttpUtils.get().url(MyApplication.baseUrl+"api/ProductOrder/" + s + "?index=1&size=10000000&companyid=" + companyId).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loadView.setVisibility(View.GONE);
                        Toast.makeText(SearchActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        loadView.setVisibility(View.GONE);
                        final ProductTask productTask = mGson.fromJson(response, ProductTask.class);

                        if (productTask.getCode() == 200) {
                            ProductTaskAdapter mAdapter = new ProductTaskAdapter(MyApplication.getContext(), productTask.getDatas());

                            pulltoRefresh.setAdapter(mAdapter);
                            mAdapter.setClickListener(new ProductTaskAdapter.onItemClickListener() {
                                @Override
                                public void clickItem(int position) {
                                    //跳转到PackingActivity
                                    Intent intent = new Intent(MyApplication.getContext(), PackingActivity.class);
                                    intent.putExtra("goodNumber", productTask.getDatas().get(position).getGoodNumber());
                                    intent.putExtra("leave", "max");
                                    intent.putExtra("companyId", companyId);
                                    intent.putExtra("targetIp",mTargetIp);
                                    intent.putExtra("machineId",machineId);
                                    intent.putExtra("scanningName",scanningName);
                                    intent.putExtra("productName",productTask.getDatas().get(position).getFproductName());
                                    intent.putExtra("billNumber", productTask.getDatas().get(position).getFicmobillNo());
                                    intent.putExtra("productModel",productTask.getDatas().get(position).getFproductModel());
                                    intent.putExtra("companyName", mCompanyName);
                                    intent.putExtra("shumeipai",mShumeipai);
                                    startActivity(intent);
                                }
                            });

                            mAdapter.setAddPackingItem(new ProductTaskAdapter.addPackingItem(){
                                @Override
                                public void addPackingItem(int position) {
                                    ProductTask.DatasBean data = productTask.getDatas().get(position);
                                    Intent intent1=new Intent(MyApplication.getContext(),AddUnitActivity.class);
                                    intent1.putExtra("goodNumbers",data.getGoodNumber());
                                    intent1.putExtra("CompanyId",companyId);
                                    intent1.putExtra("ProductName",data.getFproductName());
                                    startActivity(intent1);
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}
