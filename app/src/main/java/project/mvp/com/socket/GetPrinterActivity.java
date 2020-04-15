package project.mvp.com.socket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.GetPrinterAdapter;
import project.mvp.com.socket.adapter.ScanningListAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Print;
import project.mvp.com.socket.model.Printer;
import project.mvp.com.socket.model.Scanning;

public class GetPrinterActivity extends AppCompatActivity {
    @Bind(R.id.id_recyclerview)
    RecyclerView idRecyclerview;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.tb_main)
    Toolbar tbMain;
    private Printer mPrinter;
    private String mTargetIp;
    private int mMachineId;
    private String mDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_printer);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mTargetIp = intent.getStringExtra("targetIp");
        mDetail = intent.getStringExtra("detail");
        mMachineId = intent.getIntExtra("machineId",-1);
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        textView.setText("获取打印机");
        loadView.setVisibility(View.VISIBLE);
        idRecyclerview.setLayoutManager(new LinearLayoutManager(GetPrinterActivity.this));//这里用线性显示 类似于listviewGsom

        tbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tbMain.setTitle(mDetail);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Print?targetip=" + mTargetIp).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.INVISIBLE);
                Toast.makeText(GetPrinterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.INVISIBLE);
                Gson gson = new Gson();
                mPrinter= gson.fromJson(response, Printer.class);
                setAdapter();
            }

        });
    }

    private void setAdapter() {
        GetPrinterAdapter mAdapter = new GetPrinterAdapter(mPrinter, GetPrinterActivity.this);
        idRecyclerview.setAdapter(mAdapter);

        mAdapter.setListener(new GetPrinterAdapter.CheckboxClickListener() {
            @Override
            public void checkboxEvent(int position, boolean isCheck) {
                if (isCheck) {
                    Intent intent1 = new Intent(GetPrinterActivity.this, MakeLabelPathActivity.class);
                    intent1.putExtra("targetIp",mTargetIp);
                    intent1.putExtra("machineId",mMachineId);
                    intent1.putExtra("printerName",mPrinter.getDatas().get(position));
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setAdapter();
    }
}