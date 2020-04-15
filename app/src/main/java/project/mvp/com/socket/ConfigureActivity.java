package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import project.mvp.com.socket.adapter.PcListAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Machine;
import project.mvp.com.socket.model.MachineOnline;

public class ConfigureActivity extends AppCompatActivity {

    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.tb_main)
    Toolbar tbMain;
    private PcListAdapter mAdapter;
    private Machine mMachine;
    private Gson mGson;
    private int configure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        ButterKnife.bind(this);
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        textView.setText("获取机器");
        loadView.setVisibility(View.VISIBLE);
        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tbMain.setTitle("嘉琪车间配置页");

        Intent intent = getIntent();
        configure = intent.getIntExtra("configure", 0);

        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Machine?index=1&size=1000").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.INVISIBLE);
                Toast.makeText(ConfigureActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.INVISIBLE);
                idRecyclerview.setLayoutManager(new LinearLayoutManager(ConfigureActivity.this));//这里用线性显示 类似于listview
                mGson = new Gson();
                mMachine = mGson.fromJson(response, Machine.class);
                for (Machine.DatasBean data : mMachine.getDatas()) {
                    data.setCheckStatus(false);
                }
                if (mMachine.getCode() == 200) {
                    setAdapter();
                } else {
                    Toast.makeText(ConfigureActivity.this, mMachine.getErrorInfo(), Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private void setAdapter() {
        mAdapter = new PcListAdapter(mMachine, ConfigureActivity.this);
        idRecyclerview.setAdapter(mAdapter);
        mAdapter.setListener(new PcListAdapter.CheckboxClickListener() {
            @Override
            public void checkboxEvent(final int position, final boolean isCheck) {
                if (isCheck) {
                    textView.setText("查询机器是否可用");
                    loadView.setVisibility(View.VISIBLE);
                    OkHttpUtils.get().url(MyApplication.baseUrl+"api/Machine/" + mMachine.getDatas().get(position).getId()).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            loadView.setVisibility(View.INVISIBLE);
                            Toast.makeText(ConfigureActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(String response) {

                            loadView.setVisibility(View.INVISIBLE);
                            MachineOnline online = mGson.fromJson(response, MachineOnline.class);
                            if (online.getCode() == 200) {
                                if(configure==1){
                                    //跳转到下一个扫描枪界面
                                    Intent intent = new Intent(ConfigureActivity.this, ScanningActivity.class);
                                    intent.putExtra("targetIp", mMachine.getDatas().get(position).getMachineIp());
                                    intent.putExtra("detail",mMachine.getDatas().get(position).getRemark());
                                    intent.putExtra("machineId",mMachine.getDatas().get(position).getId());
                                    startActivity(intent);
                                }else if(configure==2){
                                    Intent intent=new Intent(ConfigureActivity.this,ReStockingActivity.class);
                                    intent.putExtra("detail",mMachine.getDatas().get(position).getRemark());
                                    intent.putExtra("machineId",mMachine.getDatas().get(position).getId());
                                    startActivity(intent);
                                }else if(configure==3){
                                    Intent intent=new Intent(ConfigureActivity.this,LabelPathActivity.class);
                                    intent.putExtra("detail",mMachine.getDatas().get(position).getRemark());
                                    intent.putExtra("machineId",mMachine.getDatas().get(position).getId());
                                    startActivity(intent);
                                }else if(configure==4){
                                    //获取打印机
                                    Intent intent=new Intent(ConfigureActivity.this,GetPrinterActivity.class);
                                    intent.putExtra("machineId",mMachine.getDatas().get(position).getId());
                                    intent.putExtra("detail",mMachine.getDatas().get(position).getRemark());
                                    intent.putExtra("targetIp", mMachine.getDatas().get(position).getMachineIp());
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(ConfigureActivity.this, online.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for (Machine.DatasBean data : mMachine.getDatas()) {
            data.setCheckStatus(false);
        }
        setAdapter();
    }

}
