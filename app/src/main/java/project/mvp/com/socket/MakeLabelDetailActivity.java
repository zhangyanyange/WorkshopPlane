package project.mvp.com.socket;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

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
import project.mvp.com.socket.adapter.MakeLabelDetailAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.LabelDetail;
import project.mvp.com.socket.model.MakeLabelDetail;
import project.mvp.com.socket.model.Printer;
import project.mvp.com.socket.pulltorefreshrecyclerview.OnRefreshListener;
import project.mvp.com.socket.pulltorefreshrecyclerview.PullToRefreshRecyclerView;
import project.mvp.com.socket.utils.ShowToastTime;

public class MakeLabelDetailActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.pulltoRefresh)
    RecyclerView pulltoRefresh;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    private Gson gson;
    private String ID;
    private List<MakeLabelDetail.DatasBean> datas;
    private MakeLabelDetailAdapter adapter;
    private String printerName;
    private ArrayList<MakeLabelDetail.DatasBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        ID = intent.getStringExtra("ID");
        printerName = intent.getStringExtra("printerName");
        list = new ArrayList<>();
        //搜索
        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MakeLabelDetailActivity.this, SearchMakeLabelDetailActivity.class);
                intent1.putExtra("ID",ID);
                intent1.putExtra("printerName", printerName);
                startActivity(intent1);
            }
        });
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pulltoRefresh.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        pulltoRefresh.setLayoutManager(new LinearLayoutManager(this));


        gson = new Gson();
        loadView.setVisibility(View.VISIBLE);

        OkHttpUtils
                .get()//获取重打产品标签数据
                .url(MyApplication.baseUrl+"api/Stock/Pakcages?id="+ID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        loadView.setVisibility(View.GONE);
                        ShowToastTime.showTextToast(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        textView.setText("补打中");
                        loadView.setVisibility(View.GONE);
                        MakeLabelDetail labelDetail = gson.fromJson(response, MakeLabelDetail.class);

                        for(MakeLabelDetail.DatasBean item : labelDetail.getDatas()){
                            item.setPname(printerName);
                        }
                        if (labelDetail.getCode() == 200) {
                            datas = labelDetail.getDatas();
                            adapter = new MakeLabelDetailAdapter(MakeLabelDetailActivity.this, datas);
                            pulltoRefresh.setAdapter(adapter);
                            adapter.setClickListener(new MakeLabelDetailAdapter.onItemClickListener() {
                                @Override
                                public void clickItem(int position) {
                                    list.clear();
                                    list.add(datas.get(position));
                                    loadView.setVisibility(View.VISIBLE);
                                    OkHttpUtils.postString()
                                            .url(MyApplication.baseUrl+"api/Print")
                                            .content(gson.toJson(list))
                                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                            .build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e) {
                                                    loadView.setVisibility(View.GONE);
                                                    Toast.makeText(MakeLabelDetailActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    loadView.setVisibility(View.GONE);
                                                    ConfigureOptions options = gson.fromJson(response, ConfigureOptions.class);
                                                    if(options.getCode()==200){
                                                        Toast.makeText(MakeLabelDetailActivity.this,"标签补打成功",Toast.LENGTH_SHORT).show();
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
