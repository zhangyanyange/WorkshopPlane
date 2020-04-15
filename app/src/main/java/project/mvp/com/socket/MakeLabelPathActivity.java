package project.mvp.com.socket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.LabelPathOrderAdapter;
import project.mvp.com.socket.adapter.MakeLabelPathOrderAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.LabelPathOrder;
import project.mvp.com.socket.model.MakeLabelPathOrder;
import project.mvp.com.socket.utils.ShowToastTime;

public class MakeLabelPathActivity extends AppCompatActivity {
    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private Gson gson;
    private String printerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_path);
        ButterKnife.bind(this);
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final Intent intent = getIntent();

        final int machineId = intent.getIntExtra("machineId",-1);
        printerName = intent.getStringExtra("printerName");
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        gson = new Gson();
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils
                .get()
                .url("http://192.168.12.247:8085/api/Stock/InRecord?pageindex=1&pagesize=10000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ShowToastTime.showTextToast(e.getMessage());
                        loadView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response) {
                        final  MakeLabelPathOrder order = gson.fromJson(response, MakeLabelPathOrder.class);
                        loadView.setVisibility(View.GONE);
                        if(order.getCode()==200){
                            MakeLabelPathOrderAdapter adapter=new MakeLabelPathOrderAdapter(MakeLabelPathActivity.this,order);
                            idRecyclerview.setAdapter(adapter);

                            adapter.setClickListener(new MakeLabelPathOrderAdapter.onItemClickListener() {
                                @Override
                                public void clickItem(int position) {
                                    Intent intent1 = new Intent(MakeLabelPathActivity.this, MakeLabelDetailActivity.class);
                                    intent1.putExtra("printerName",printerName);
                                    intent1.putExtra("ID",order.getDatas().get(position).getId());
                                    intent1.putExtra("machineId",machineId);
                                    startActivity(intent1);
                                }
                            });
                        }else {
                            ShowToastTime.showTextToast(order.getMessage());
                        }
                    }
                });

}
}
