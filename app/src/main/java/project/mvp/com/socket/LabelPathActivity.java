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

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.LabelPathOrderAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.LabelPathOrder;
import project.mvp.com.socket.utils.ShowToastTime;

public class LabelPathActivity extends AppCompatActivity {

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
    private int machineId;
    private Gson gson;

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
        machineId = intent.getIntExtra("machineId", -1);

        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        gson = new Gson();

        OkHttpUtils
                .get()
                .url(MyApplication.baseUrl+"api/Production/RePrintOrders/"+machineId+"?machineid="+machineId+"&pageindex=0&pagesize=10000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        ShowToastTime.showTextToast(e.getMessage());
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        final LabelPathOrder order = gson.fromJson(response, LabelPathOrder.class);
                        System.out.println(response);
                        if(order.getCode()==200){
                            LabelPathOrderAdapter adapter=new LabelPathOrderAdapter(LabelPathActivity.this,order);
                            idRecyclerview.setAdapter(adapter);

                            adapter.setClickListener(new LabelPathOrderAdapter.onItemClickListener() {
                                @Override
                                public void clickItem(int position) {
                                    Intent intent1 = new Intent(LabelPathActivity.this, LabelDetailActivity.class);
                                    intent1.putExtra("machineId",machineId);
                                    intent1.putExtra("orderNo",order.getDatas().get(position).getBillNo());
                                    intent1.putExtra("brand",order.getDatas().get(position).getBrand());
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
