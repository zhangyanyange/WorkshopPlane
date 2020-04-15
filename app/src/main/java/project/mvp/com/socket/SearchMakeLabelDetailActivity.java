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
import project.mvp.com.socket.utils.ShowToastTime;

public class SearchMakeLabelDetailActivity extends AppCompatActivity {

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
    private String ID;
    private Gson gson;
    private List<MakeLabelDetail.DatasBean> datas;
    private MakeLabelDetailAdapter adapter;
    private String printerName;
    private ArrayList<MakeLabelDetail.DatasBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_label_detail);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        printerName = intent.getStringExtra("printerName");
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
                loadView.setVisibility(View.VISIBLE);;
                OkHttpUtils
                        .get()
                        .url("http://192.168.12.247:8085/api/Stock/Pakcages?id="+ID+"&key="+s)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                loadView.setVisibility(View.GONE);
                                ShowToastTime.showTextToast(e.toString());
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
                                    adapter = new MakeLabelDetailAdapter(SearchMakeLabelDetailActivity.this, datas);
                                    pulltoRefresh.setAdapter(adapter);
                                    adapter.setClickListener(new MakeLabelDetailAdapter.onItemClickListener() {
                                        @Override
                                        public void clickItem(int position) {
                                            list.clear();
                                            list.add(datas.get(position));
                                            System.out.println(list);
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
                                                            Toast.makeText(SearchMakeLabelDetailActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onResponse(String response) {
                                                            loadView.setVisibility(View.GONE);
                                                            ConfigureOptions options = gson.fromJson(response, ConfigureOptions.class);
                                                            if(options.getCode()==200){
                                                                Toast.makeText(SearchMakeLabelDetailActivity.this,"标签补打成功",Toast.LENGTH_SHORT).show();
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
