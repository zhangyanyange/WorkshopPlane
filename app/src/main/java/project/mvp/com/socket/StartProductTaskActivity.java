package project.mvp.com.socket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import project.mvp.com.socket.adapter.LabelDetailAdapter;
import project.mvp.com.socket.adapter.LogAdapter;
import project.mvp.com.socket.adapter.PackageAdapter;
import project.mvp.com.socket.adapter.PackingAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.ConfigureOptions;
import project.mvp.com.socket.model.ContinueProduce;
import project.mvp.com.socket.model.HandShakeBean;
import project.mvp.com.socket.model.LabelDetail;
import project.mvp.com.socket.model.LogBean;
import project.mvp.com.socket.model.LogInfo;
import project.mvp.com.socket.model.ViewWorkConfig;
import project.mvp.com.socket.utils.MyComparator;
import project.mvp.com.socket.utils.NumberUtils;
import project.mvp.com.socket.utils.ShowToastTime;

public class StartProductTaskActivity extends AppCompatActivity {

    @Bind(R.id.tb_main)
    Toolbar tbMain;
    @Bind(R.id.product_number)
    TextView productNumber;
    @Bind(R.id.productName)
    TextView productName;
    @Bind(R.id.createTime)
    TextView createTime;
    @Bind(R.id.startorend)
    Button startorend;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.productModel)
    TextView productModel;

    @Bind(R.id.rece_list)
    RecyclerView mReceList;
    @Bind(R.id.connect)
    Button mConnect;
    @Bind(R.id.clear_log)
    Button mClearLog;
    @Bind(R.id.form)
    ScrollView form;
    @Bind(R.id.ll_product)
    LinearLayout llProduct;
    @Bind(R.id.label)
    ListView listLabel;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ViewWorkConfig.DatasBean mProductTask;
    private Gson mGson;
    private int mProductTaskState;
    private String mMachineIp;
    private ConnectionInfo mInfo;
    private IConnectionManager mManager;

    private OkSocketOptions mOkOptions;

    private SoundPool soundPool = null;

    private LogAdapter mSendLogAdapter = new LogAdapter();
    private LogAdapter mReceLogAdapter = new LogAdapter();

    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
            mManager.send(new HandShakeBean());
            mConnect.setText("断开");
        }

        @Override
        public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                logRece("正常断开:" + e.getMessage(), 2);
            } else {
                logRece("正常断开", 2);
            }
            mConnect.setText("连接");
        }

        @Override
        public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
            logRece("连接失败", 3);
            logRece("连接详细信:" + info.getIp() + " " + info.getPort(), 3);
            logRece("Action:" + action, 3);
            logRece(e.getLocalizedMessage(), 3);
            mConnect.setText("连接");
        }

        @Override
        public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            LogInfo logInfo = mGson.fromJson(str, LogInfo.class);

            if (logInfo.getCType().equals("Error")) {

                soundPool.load(StartProductTaskActivity.this, R.raw.baojing, 1);
                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                        soundPool.play(1,  //声音id
                                1, //左声道
                                1, //右声道
                                1, //优先级
                                0, // 0表示不循环，-1表示循环播放
                                1);//播放比率，0.5~2，一般为1
                    }
                });
                logRece(logInfo.getDatas(), 3);
            } else if (logInfo.getCType().equals("Data")) {
                logRece(logInfo.getDatas(), 2);
            }

        }

        @Override
        public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
            String str = new String("连接成功");
            logRece(str, 2);
        }
    };
    private LabelDetail labelDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_product_task);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
        }

//        instance.release();

        Intent intent = getIntent();
        mProductTask = intent.getParcelableExtra("productTask");
        mProductTaskState = intent.getIntExtra("productTaskState", -1);


        tbMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (mProductTaskState == 1) {
            tbMain.setTitle("测式" + mProductTask.getRemark());
        } else if (mProductTaskState == 2) {
            tbMain.setTitle("正式" + mProductTask.getRemark());
            //查看是否又继续生产的任务单,只有正式生产任务单可继续生产
            lookContinueProduce(mProductTask.getId());
        }
        mMachineIp = mProductTask.getMachineIp();

        productNumber.setText(mProductTask.getFicmobillNo());
        productName.setText(mProductTask.getFproductName());
        createTime.setText(mProductTask.getCreateTime().substring(0, 10));
        productModel.setText(mProductTask.getFproductModel());
        //获取补打标签有哪些
//        getLabel();
        if (mProductTask.isFlag()) {
            llProduct.setVisibility(View.VISIBLE);
            startorend.setBackgroundColor(Color.parseColor("#5cb85c"));
            startorend.setText("开始生产");
        } else {
            llProduct.setVisibility(View.GONE);
            startorend.setBackgroundColor(Color.parseColor("#d9534f"));
            startorend.setText("结束生产");
        }
        mGson = new Gson();

        startorend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProductTask.isFlag()) {
                    if (mProductTaskState == 1) {
                        startTestProductTask();
                    } else if (mProductTaskState == 2) {
                        startProductTask();
                    }

                } else {
                    if (mProductTaskState == 1) {
                        endTestProductTask();
                    } else if (mProductTaskState == 2) {
                        endProductTask();
                    }
                }
            }
        });

        initData();
        setListener();
    }

    //获取补打标签有哪些
    private void getLabel() {
        listLabel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawer(listLabel);
                OkHttpUtils
                        .postString()
                        .url(MyApplication.baseUrl + "api/Production/RePrint")
                        .content(mGson.toJson(labelDetail.getDatas().get(i)))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {
                                ShowToastTime.showTextToast(e.getMessage());
                            }

                            @Override
                            public void onResponse(String response) {
                                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                                if (configureOptions.getCode() == 200) {
                                    ShowToastTime.showTextToast("补打成功");
                                } else {
                                    ShowToastTime.showTextToast("补打失败");
                                }
                            }
                        });
            }
        });
        loadView.setVisibility(View.VISIBLE);
        //获取重新打印包装,根据按钮点击，在获取数据
        OkHttpUtils.get().url(MyApplication.baseUrl + "api/Production/RePrint/" + mProductTask.getMachineId() + "?machineid=" + mProductTask.getMachineId() + "&pageindex=1&pagesize=10000&billno=" + mProductTask.getFicmobillNo() + "&brand=" + mProductTask.getCompanyId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                labelDetail = mGson.fromJson(response, LabelDetail.class);
                if (labelDetail.getCode() == 200) {
                    //服务器数组返回空，规定不能数组返回空，以约定。
                    MyComparator mc=new MyComparator();
                    Collections.sort(labelDetail.getDatas(),mc);
                    PackageAdapter adapter = new PackageAdapter(StartProductTaskActivity.this, labelDetail);
                    listLabel.setAdapter(adapter);

                    toggleRight();
                }else{
                    ShowToastTime.showTextToast(labelDetail.getMessage());
                }
            }
        });
    }

    //查看继续生产任务单
    private void lookContinueProduce(final int id) {

        OkHttpUtils.get().url(MyApplication.baseUrl + "api/Production/TmpData/" + id).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                ShowToastTime.showTextToast(e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                ContinueProduce produce = mGson.fromJson(response, ContinueProduce.class);
                //弹出对话框
                if (produce.getCode() == 200) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(StartProductTaskActivity.this);
                    builder.setTitle("订单继续生产");
                    builder.setMessage("订单:"+produce.getDatas().getBillNo()+"\n当前生产:"+produce.getDatas().getCurrentNumber()+produce.getDatas().getUnit()+"\n包装单位:"+produce.getDatas().getRemark());
                    builder.setNegativeButton("", null);
                    builder.setPositiveButton("继续生产", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            loadView.setVisibility(View.VISIBLE);
                            textView.setText("开始生产");
                            OkHttpUtils
                                    .put()
                                    .url(MyApplication.baseUrl + "api/Production/ContinueProduction/" + id)
                                    .requestBody(RequestBody.create(null, ""))
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e) {
                                    loadView.setVisibility(View.GONE);
                                    ShowToastTime.showTextToast(e.getMessage());
                                }

                                @Override
                                public void onResponse(String response) {
                                    loadView.setVisibility(View.GONE);
                                    ConfigureOptions options = mGson.fromJson(response, ConfigureOptions.class);
                                    if (options.getCode() == 200) {
                                        if (!mManager.isConnect()) {
                                            initManager();
                                            mManager.connect();
                                            mConnect.setText("断开连接");
                                        }
                                    } else {
                                        ShowToastTime.showTextToast(options.getMessage());
                                    }

                                }
                            });
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    private void initData() {

        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mReceList.setLayoutManager(manager2);
        mReceList.setAdapter(mReceLogAdapter);

        initManager();
    }

    private void initManager() {

        final Handler handler = new Handler();
        mInfo = new ConnectionInfo(mMachineIp, 6003);
        mOkOptions = new OkSocketOptions.Builder()
                .setReconnectionManager(new NoneReconnect())
                .setConnectTimeoutSecond(10)
                .setReaderProtocol(new IReaderProtocol() {
                    @Override
                    public int getHeaderLength() {
                        return 4;
                    }

                    @Override
                    public int getBodyLength(byte[] header, ByteOrder byteOrder) {
                        if (header == null || header.length < getHeaderLength()) {
                            return 0;
                        }
                        int i = NumberUtils.byte4ToInt(header, 0);
                        System.out.println(i);
                        return i;
                    }
                })
                .setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() {
                    @Override
                    public void handleCallbackEvent(ActionDispatcher.ActionRunnable runnable) {
                        handler.post(runnable);
                    }
                })
                .build();
        mManager = OkSocket.open(mInfo).option(mOkOptions);
        mManager.registerReceiver(adapter);
    }

    private void setListener() {
        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductTask.isFlag()) {
                    Toast.makeText(StartProductTaskActivity.this, "请先开始生产,再连接信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mManager == null) {
                    return;
                }
                shouldConnected();
            }
        });

        mClearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getLabel();
            }
        });
    }

    private void shouldConnected() {
        if (!mManager.isConnect()) {
            initManager();
            mManager.connect();

        } else {
            mConnect.setText("断开连接");
            mManager.disconnect();
        }
    }


    private void logRece(final String log, int Type) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            LogBean logBean = new LogBean(System.currentTimeMillis(), log, Type);
            mReceLogAdapter.getDataList().add(mReceLogAdapter.getDataList().size(), logBean);
            mReceLogAdapter.notifyDataSetChanged();
            mReceList.scrollToPosition(mReceLogAdapter.getItemCount() - 1);
        } else {
            final String threadName = Thread.currentThread().getName();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    logRece(threadName + " 线程打印(In Thread):" + log, 2);
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            mManager.disconnect();
            mManager.unRegisterReceiver(adapter);
        }
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }

    private void endProductTask() {
        textView.setText("结束正式生产任务中...");
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.put().requestBody(RequestBody.create(null, "")).url(MyApplication.baseUrl + "api/Production/CloseProduction/" + mProductTask.getId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                loadView.setVisibility(View.GONE);
                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                if (configureOptions.getCode() == 200) {
                    startorend.setBackgroundColor(Color.parseColor("#5cb85c"));
                    startorend.setText("开始生产");
                    mProductTask.setFlag(true);
                    llProduct.setVisibility(View.VISIBLE);
                    //连接断开
                    if (mManager.isConnect()) {
                        mConnect.setText("连接");
                        mManager.disconnect();
                    }
                } else {
                    ShowToastTime.showTextToast(configureOptions.getMessage());
                }
            }
        });
    }

    private void endTestProductTask() {
        textView.setText("结束测试生产任务中...");
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.put().requestBody(RequestBody.create(null, "")).url(MyApplication.baseUrl + "api/Test/Production/CloseProduction/" + mProductTask.getId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                loadView.setVisibility(View.GONE);
                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                if (configureOptions.getCode() == 200) {
                    startorend.setBackgroundColor(Color.parseColor("#5cb85c"));
                    startorend.setText("开始生产");
                    mProductTask.setFlag(true);
                    llProduct.setVisibility(View.VISIBLE);

                    if (mManager.isConnect()) {
                        mConnect.setText("连接");
                        mManager.disconnect();
                    }
                } else {
                    ShowToastTime.showTextToast(configureOptions.getMessage());
                }
            }
        });
    }

    private void startTestProductTask() {
        textView.setText("开始测试生产任务...");
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.put().requestBody(RequestBody.create(null, "")).url(MyApplication.baseUrl + "api/Test/Production/" + mProductTask.getId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                if (configureOptions.getCode() == 200) {
                    startorend.setBackgroundColor(Color.parseColor("#d9534f"));
                    startorend.setText("结束生产");
                    mProductTask.setFlag(false);
                    llProduct.setVisibility(View.GONE);

                    if (!mManager.isConnect()) {
                        initManager();
                        mManager.connect();
                        mConnect.setText("断开连接");
                    }

                } else {
                    ShowToastTime.showTextToast(configureOptions.getMessage());
                }
            }
        });
    }

    private void startProductTask() {
        textView.setText("开始正式生产任务...");
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.put().requestBody(RequestBody.create(null, "")).url(MyApplication.baseUrl + "api/Production/" + mProductTask.getId()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                ShowToastTime.showTextToast(e.toString());
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                loadView.setVisibility(View.GONE);
                ConfigureOptions configureOptions = mGson.fromJson(response, ConfigureOptions.class);
                if (configureOptions.getCode() == 200) {
                    startorend.setBackgroundColor(Color.parseColor("#d9534f"));
                    startorend.setText("结束生产");
                    mProductTask.setFlag(false);
                    llProduct.setVisibility(View.GONE);
                    if (!mManager.isConnect()) {
                        initManager();
                        mManager.connect();
                        mConnect.setText("断开连接");
                    }
                } else {
                    ShowToastTime.showTextToast(configureOptions.getMessage());
                }
            }
        });
    }

    //是否弹出右侧弹窗
    private void toggleRight() {
        if (drawerLayout.isDrawerOpen(listLabel)) {
            drawerLayout.closeDrawer(listLabel);
        } else {
            drawerLayout.openDrawer(listLabel);
        }

    }
}
