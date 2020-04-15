package project.mvp.com.socket;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.AndroidVerion;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.peizhi)
    RelativeLayout peizhi;

    @Bind(R.id.viewConfigura)
    RelativeLayout viewConfigura;


    public static final int SHOW_UPDATE_DIALOG = 1;
    @Bind(R.id.rl_re_stocking)
    RelativeLayout rlReStocking;
    @Bind(R.id.label_path)
    RelativeLayout labelPath;
    @Bind(R.id.nonviewConfigura)
    RelativeLayout nonviewConfigura;
    @Bind(R.id.label_path_make)
    RelativeLayout labelPathMake;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_UPDATE_DIALOG:// 显示应用更新对话框
                    if (permission) {
                        showUpdateDialog();
                    }
                    break;
            }
        }
    };
    private long mTaskId;
    private DownloadManager downloadManager;
    private AndroidVerion androidVerion;
    private boolean permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PackageManager pm = getPackageManager();
        permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "project.mvp.com.socket"));
        if (!permission) {
            MainActivityPermissionsDispatcher.showUpdateDialogWithPermissionCheck(MainActivity.this);
        }

        OkHttpUtils.get().url(MyApplication.baseUrl + "Version/version.json").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                androidVerion = gson.fromJson(response, AndroidVerion.class);
                PackageManager pm = MyApplication.getContext().getPackageManager();
                PackageInfo pi = null;
                try {
                    pi = pm.getPackageInfo(MainActivity.this.getPackageName(), 0);
                    int versionCode = pi.versionCode;
                    if (!androidVerion.getVersion().equals("" + versionCode)) {
                        Message msg = Message.obtain();
                        msg.what = SHOW_UPDATE_DIALOG;
                        handler.sendMessage(msg);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        //跳转到配置的Activity
        peizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
                intent.putExtra("configure", 1);
                startActivity(intent);
            }
        });
        viewConfigura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewConfiguraActivity.class));
            }
        });

        rlReStocking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
                intent.putExtra("configure", 2);
                startActivity(intent);
            }
        });
        //扫码标签补打
        labelPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
                intent.putExtra("configure", 3);
                startActivity(intent);
            }
        });
        //非扫码标签补打
        labelPathMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigureActivity.class);
                intent.putExtra("configure",4);
                startActivity(intent);
            }
        });
        //非扫码生产线
        nonviewConfigura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NonscanningActivity.class));
            }
        });
    }

    //广播接受者，接收下载状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkDownloadStatus();//检查下载状态
        }
    };

    /**
     * 显示自动更新的对话框
     *
     * @param
     */
    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    protected void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("升级提醒");
        builder.setMessage("解决一些已知问题");
        builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    downloadAPK(MyApplication.baseUrl + "dl/workshop.apk", "jiaqi.apk");
                } else {
                    // 进入应用程序主界面.
                    Toast.makeText(MainActivity.this, "sd卡不可用,无法自动更新", Toast.LENGTH_SHORT)
                            .show();

                }
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    //使用系统下载器下载
    private void downloadAPK(String versionUrl, String versionName) {
        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(versionUrl));
        request.setAllowedOverRoaming(true);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(versionUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setVisibleInDownloadsUi(true);
        request.setTitle("嘉琪车间");
        request.setDescription("优化用户体验");
        //sdcard的目录下的download文件夹，必须设置

        File file =
                new File(Environment.getExternalStorageDirectory(), "jiaqi.apk");
        if (file.exists()) {
            System.out.println(file.length());
            if (file.length() > 0) {
                file.delete();
                file = new File(Environment.getExternalStorageDirectory(), "jiaqi.apk");
            }
        }
        request.setDestinationUri(Uri.fromFile(file));
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径

        //将下载请求加入下载队列
        downloadManager = (DownloadManager) MainActivity.this.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        mTaskId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        MainActivity.this.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //检查下载状态
    private void checkDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(mTaskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    break;
                case DownloadManager.STATUS_PENDING:
                    break;
                case DownloadManager.STATUS_RUNNING:

                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    File apkFile =
                            new File(Environment.getExternalStorageDirectory(), "jiaqi.apk");
                    installAPK(apkFile);
                    break;
                case DownloadManager.STATUS_FAILED:

                    break;
            }
        }
    }

    //下载到本地后执行安装
    protected void installAPK(File file) {
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(MainActivity.this,
                "project.mvp.com.socket.fileProvider",
                file);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释

        MainActivity.this.startActivity(intent);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder(this).setMessage("自动升级功能，需要读写sdk这个权限").setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                request.proceed();
            }
        }).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDenied() {
        Toast.makeText(this, "无法使用自动升级", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNotAsk() {
        new AlertDialog.Builder(this).setMessage("该权限是为了自动更新").setPositiveButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "拒绝自己去设置里给权限", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
