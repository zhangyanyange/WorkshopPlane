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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.adapter.LabelDetailAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.LabelDetail;
import project.mvp.com.socket.utils.ShowToastTime;

public class SearchLabelDetailActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.tv_hint)
    EditText tvHint;
    @Bind(R.id.tv_confim)
    TextView tvConfim;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.pulltoRefresh)
    RecyclerView pulltoRefresh;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private int machineId;
    private String orderNo;
    private String brand;
    private Gson gson;
    private List<LabelDetail.DatasBean> datas;
    private LabelDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_label_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        machineId = intent.getIntExtra("machineId", -1);
        orderNo = intent.getStringExtra("orderNo");
        brand = intent.getStringExtra("brand");
        pulltoRefresh.setLayoutManager(new LinearLayoutManager(this));
        pulltoRefresh.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        gson = new Gson();
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvConfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = tvHint.getText().toString();
                if (s.equals("")) {
                    ShowToastTime.showTextToast("请输入包装名");
                }
                loadView.setVisibility(View.VISIBLE);
                OkHttpUtils
                        .get()
                        .url(MyApplication.baseUrl+"api/Production/RePrint/" + machineId + "?machineid=" + machineId + "&pageindex=1&pagesize=100000&billno=" + orderNo + "&brand=" + brand + "&packagename=" + s)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                loadView.setVisibility(View.GONE);
                                ShowToastTime.showTextToast("请输入包装名");
                            }

                            @Override
                            public void onResponse(String response) {

                                loadView.setVisibility(View.GONE);
                                LabelDetail labelDetail = gson.fromJson(response, LabelDetail.class);
                                if (labelDetail.getCode() == 200) {
                                    datas = labelDetail.getDatas();
                                    adapter = new LabelDetailAdapter(SearchLabelDetailActivity.this, datas);
                                    pulltoRefresh.setAdapter(adapter);
                                    adapter.setClickListener(new LabelDetailAdapter.onItemClickListener() {
                                        @Override
                                        public void clickItem(int position) {

                                            textView.setText("补打中");
                                            loadView.setVisibility(View.VISIBLE);
                                            OkHttpUtils
                                                    .postString()
                                                    .url(MyApplication.baseUrl+"api/Production/RePrint")
                                                    .content(gson.toJson(datas.get(position)))
                                                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Exception e) {
                                                            loadView.setVisibility(View.GONE);
                                                            ShowToastTime.showTextToast(e.getMessage());
                                                        }

                                                        @Override
                                                        public void onResponse(String response) {
                                                            loadView.setVisibility(View.GONE);
                                                            ConfigureOptions configureOptions = gson.fromJson(response, ConfigureOptions.class);
                                                            if (configureOptions.getCode() == 200) {
                                                                ShowToastTime.showTextToast("补打成功");
                                                            } else {
                                                                ShowToastTime.showTextToast("补打失败");
                                                            }
                                                        }
                                                    });
                                        }
                                    });
                                } else {
                                    ShowToastTime.showTextToast(labelDetail.getMessage());
                                }
                            }

                        });
            }
        });
    }
}
