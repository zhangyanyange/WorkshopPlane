package project.mvp.com.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.adapter.WorkConfigAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.ViewWorkConfig;
import project.mvp.com.socket.utils.ShowToastTime;

public class ViewConfiguraActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.view_configura)
    RecyclerView viewConfigura;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.textView)
    TextView textView;
    private Gson mGson;
    private ViewWorkConfig mWorkConfig;
    private WorkConfigAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_configura);
        ButterKnife.bind(this);
        mGson = new Gson();
        tbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getList();
        viewConfigura.addItemDecoration(new DividerItemDecoration(ViewConfiguraActivity.this,DividerItemDecoration.VERTICAL));
    }
    public void removeItem(int position){
        mWorkConfig.getDatas().remove(position);//删除数据源,移除集合中当前下标的数据
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getList();
    }

    private void getList() {
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/WorkBrenchConfig?index=1&size=10000").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                ShowToastTime.showTextToast(e.toString());
                loadView.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                mWorkConfig = mGson.fromJson(response, ViewWorkConfig.class);
                mAdapter = new WorkConfigAdapter(ViewConfiguraActivity.this, mWorkConfig.getDatas());
                viewConfigura.setLayoutManager(new LinearLayoutManager(ViewConfiguraActivity.this));
                viewConfigura.setAdapter(mAdapter);

                mAdapter.setClickListener(new WorkConfigAdapter.onItemClickListener() {
                    @Override
                    public void clickItem(int position,int state) {
                        Intent intent = new Intent(ViewConfiguraActivity.this, StartProductTaskActivity.class);
                        intent.putExtra("productTask", mWorkConfig.getDatas().get(position));
                        intent.putExtra("productTaskState",state);
                        startActivity(intent);
                    }
                });

                mAdapter.setDeleteProductTask(new WorkConfigAdapter.deleteProductTask() {
                    @Override
                    public void deleteItem(final int position) {
                        textView.setText("删除中");
                        loadView.setVisibility(View.VISIBLE);
                        OkHttpUtils
                                .delete()
                                .url(MyApplication.baseUrl+"api/WorkBrenchConfig/"+mWorkConfig.getDatas().get(position).getId())
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e) {
                                        loadView.setVisibility(View.GONE);
                                        ShowToastTime.showTextToast(e.toString());
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        loadView.setVisibility(View.GONE);
                                        ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                                        if(configureOptions.getCode()==200){
                                            removeItem(position);
                                        }else{
                                            ShowToastTime.showTextToast(configureOptions.getMessage());
                                        }
                                    }
                                });
                    }
                });
            }
        });
    }
}
