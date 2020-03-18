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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import project.mvp.com.socket.adapter.ReStockingAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.PutReStocking;
import project.mvp.com.socket.model.ReStocking;
import project.mvp.com.socket.utils.ShowToastTime;

public class ReStockingActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.restocking)
    RecyclerView restocking;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private Gson gson;
    private ReStocking reStocking;
    private int machineId;
    private ReStockingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_stocking);
        ButterKnife.bind(this);
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tbMain.setTitle("重新入库");

        restocking.addItemDecoration(new DividerItemDecoration(ReStockingActivity.this, DividerItemDecoration.VERTICAL));
        restocking.setLayoutManager(new LinearLayoutManager(ReStockingActivity.this));

        Intent intent = getIntent();
        machineId = intent.getIntExtra("machineId", -1);
        gson = new Gson();
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Production/ReInStore/" + machineId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                reStocking = gson.fromJson(response, ReStocking.class);

                if (reStocking.getCode() == 200) {//刷新RecycleView
                    adapter = new ReStockingAdapter(ReStockingActivity.this, reStocking);
                    restocking.setAdapter(adapter);
                    adapter.setButtonReStock(new ReStockingAdapter.ButtonReStock() {
                        @Override
                        public void listener(int position) {
                            showDialogs(position);
                        }
                    });
                } else {
                    ShowToastTime.showTextToast(reStocking.getMessage());
                }
            }
        });

    }
    //判断订单是否重新入库
    public void showDialogs(final int pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("订单重新入库");
        builder.setMessage(reStocking.getDatas().get(pos).getBillNo());
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PutReStocking putReStocking = new PutReStocking();
                putReStocking.setMachineid(machineId);
                putReStocking.setFilename(reStocking.getDatas().get(pos).getFileName());
                String content = gson.toJson(putReStocking);
                loadView.setVisibility(View.VISIBLE);
                textView.setText("重新入库");
                OkHttpUtils
                        .postString()
                        .url(MyApplication.baseUrl+"api/Production/ReInStore")
                        .content(content)
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
                                    ShowToastTime.showTextToast("重新入库成功");
                                    removeItem(pos);
                                } else {
                                    ShowToastTime.showTextToast(configureOptions.getMessage());
                                }
                            }
                        });
            }
        });
        builder.show();
    }
    //删除当前条目，根据索引位置
    public void removeItem(int position){
        reStocking.getDatas().remove(position);//删除数据源,移除集合中当前下标的数据
        adapter.notifyDataSetChanged();
    }
}
