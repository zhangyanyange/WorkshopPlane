package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.adapter.LabelDetailAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.LabelDetail;
import project.mvp.com.socket.pulltorefreshrecyclerview.OnRefreshListener;
import project.mvp.com.socket.pulltorefreshrecyclerview.PullToRefreshRecyclerView;
import project.mvp.com.socket.utils.ShowToastTime;

public class LabelDetailActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.pulltoRefresh)
    PullToRefreshRecyclerView pulltoRefresh;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    private int pageindex = 1;
    private int pageSize = 10;
    private double count = 1;
    private Gson gson;
    private int machineId;
    private String orderNo;
    private String brand;
    private List<LabelDetail.DatasBean> datas;
    private LabelDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_detail_ac);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        machineId = intent.getIntExtra("machineId", -1);
        orderNo = intent.getStringExtra("orderNo");
        brand = intent.getStringExtra("brand");
        //搜索
        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LabelDetailActivity.this, SearchLabelDetailActivity.class);
                intent1.putExtra("machineId",machineId);
                intent1.putExtra("orderNo",orderNo);
                intent1.putExtra("brand",brand);
                startActivity(intent1);
            }
        });
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pulltoRefresh.setPullToRefreshEnabled(false);
        pulltoRefresh.loadingMoreFooter.setVisibility(View.GONE);
        pulltoRefresh.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pulltoRefresh.setLayoutManager(new LinearLayoutManager(this));
        pulltoRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pulltoRefresh.refreshComplete();
            }

            @Override
            public void onRefreshTimeOut() {

            }

            //上拉加载
            @Override
            public void onLoadMore() {
                pageindex++;
                int i = (int) count + 1;
                if (pageindex == i) {
                    pulltoRefresh.loadingMoreComplete();
                    pulltoRefresh.setNoMore(true);
                    return;
                }
                OkHttpUtils
                        .get()
                        .url(MyApplication.baseUrl+"api/Production/RePrint/" + machineId + "?machineid=" + machineId + "&pageindex=" + pageindex + "&pagesize=" + pageSize + "&billno=" + orderNo + "&brand=" + brand)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                ShowToastTime.showTextToast(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                pulltoRefresh.loadingMoreFooter.setVisibility(View.VISIBLE);
                                LabelDetail labelDetail = gson.fromJson(response, LabelDetail.class);
                                if (labelDetail.getCode() == 200) {
                                    datas.addAll(labelDetail.getDatas());
                                    adapter.notifyDataSetChanged();
                                    pulltoRefresh.loadingMoreComplete();
                                } else {
                                    ShowToastTime.showTextToast(labelDetail.getMessage());
                                }

                            }
                        });
            }

            @Override
            public void onLoadMoreTimeOut() {

            }
        });

        gson = new Gson();
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils
                .get()//获取重打产品标签数据
                .url(MyApplication.baseUrl+"api/Production/RePrint/" + machineId + "?machineid=" + machineId + "&pageindex=" + pageindex + "&pagesize=" + pageSize + "&billno=" + orderNo + "&brand=" + brand)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loadView.setVisibility(View.GONE);
                        ShowToastTime.showTextToast(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        pulltoRefresh.loadingMoreFooter.setVisibility(View.VISIBLE);
                        loadView.setVisibility(View.GONE);
                        LabelDetail labelDetail = gson.fromJson(response, LabelDetail.class);
                        if (labelDetail.getCode() == 200) {
                            datas = labelDetail.getDatas();
                            adapter = new LabelDetailAdapter(LabelDetailActivity.this, datas);
                            pulltoRefresh.setAdapter(adapter);
                            int total = labelDetail.getTotal();
                            count = Math.ceil(total / (double) pageSize);
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
}
