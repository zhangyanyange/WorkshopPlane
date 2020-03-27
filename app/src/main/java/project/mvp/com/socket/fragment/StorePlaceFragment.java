package project.mvp.com.socket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.R;
import project.mvp.com.socket.WorkShopActivity;
import project.mvp.com.socket.adapter.StorePlaceAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.Packing;
import project.mvp.com.socket.model.StoreEvent;
import project.mvp.com.socket.model.StoreLoadDone;
import project.mvp.com.socket.model.StorePlace;

/**
 * 创建人 张岩
 * 时间   2019/6/11.
 * 类描述
 */
public class StorePlaceFragment extends Fragment {

    @Bind(R.id.pulltoRefresh)
    RecyclerView pulltoRefresh;
    @Bind(R.id.p)
    ProgressBar p;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    private String mShumeipai;
    private String mGoodNumber;
    private String mTargetIp;
    private int mMachineId;
    private String mScanningName;
    private String mProductName;
    private String mBillNumber;
    private String mProductModel;
    private String mCompanyName;
    private int mCompanyId;
    private ArrayList<Packing.DatasBean> mPacking;
    private String mStoreId;
    private String mStoreName;
    private Gson mGson;
    private ArrayList<StorePlace.DatasBean> mDatas;
    private StorePlaceAdapter mAdapter;
    private StorePlace mStorePlace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_place, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mGson = new Gson();
        Bundle arguments = getArguments();
        mDatas = new ArrayList<>();
        //获取配置信息
        mShumeipai = arguments.getString("shumeipai");
        mGoodNumber = arguments.getString("goodNumber");
        mTargetIp = arguments.getString("targetIp");
        mMachineId = arguments.getInt("machineId", -1);
        mScanningName = arguments.getString("scanningName");
        mProductName = arguments.getString("productName");
        mBillNumber = arguments.getString("billNumber");
        mProductModel = arguments.getString("productModel");
        mCompanyName = arguments.getString("companyName");
        mCompanyId = arguments.getInt("companyId");
        mPacking = arguments.getParcelableArrayList("packing");
        mStoreId = arguments.getString("storeId");
        mStoreName = arguments.getString("storeName");

        pulltoRefresh.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Store/api/Store/StorePlace?companyid=" + mCompanyId + "&storeid=" + mStoreId).tag(mStoreId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                if (!(e.toString().equals("java.io.IOException: Canceled")) && !(e.toString().equals("java.net.SocketException: Socket closed"))) {
                    Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onResponse(String response) {

                loadView.setVisibility(View.GONE);
                mStorePlace = mGson.fromJson(response, StorePlace.class);
                mDatas.addAll(mStorePlace.getDatas());
                mAdapter = new StorePlaceAdapter(MyApplication.getContext(), mDatas);
                pulltoRefresh.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
                pulltoRefresh.setAdapter(mAdapter);
                StoreLoadDone loadDone = new StoreLoadDone();
                loadDone.setLoadDone(true);
                EventBus.getDefault().post(loadDone);
                mAdapter.setClickListener(new StorePlaceAdapter.onItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        //跳转到PackingActivity,跳转包装activity
                        Intent intent = new Intent(MyApplication.getContext(), WorkShopActivity.class);
                        intent.putExtra("goodNumber", mGoodNumber);
                        intent.putExtra("companyId", mCompanyId);
                        intent.putExtra("targetIp", mTargetIp);
                        intent.putParcelableArrayListExtra("packing", mPacking);
                        intent.putExtra("machineId",mMachineId);
                        intent.putExtra("scanningName",mScanningName);
                        intent.putExtra("productName",mProductName);
                        intent.putExtra("billNumber",mBillNumber);
                        intent.putExtra("productModel",mProductModel);
                        intent.putExtra("shumeipai",mShumeipai);
                        intent.putExtra("companyName",mCompanyName);
                        intent.putExtra("storeId",mStoreId);
                        intent.putExtra("storeName",mStoreName);
                        intent.putExtra("companyId",mCompanyId);
                        intent.putExtra("storePlaceId",mStorePlace.getDatas().get(position).getStorePliceId());
                        intent.putExtra("storePlaceName",mStorePlace.getDatas().get(position).getStorePliceName());
                        startActivity(intent);
                    }
                });




                if (mStorePlace.getCode() != 200) {
                    mDatas.clear();
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(MyApplication.getContext(), mStorePlace.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }
    //信息回调，回调重新入库信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StoreEvent event) {
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.getInstance().cancelTag(mStoreId);
        mStoreId = event.getStoreId();
        mStoreName = event.getStoreName();
        mDatas.clear();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/Store/api/Store/StorePlace?companyid=" + mCompanyId + "&storeid=" + mStoreId).tag(mStoreId).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                if (!(e.toString().equals("java.io.IOException: Canceled")) && !(e.toString().equals("java.net.SocketException: Socket closed"))) {
                    Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onResponse(String response) {
                loadView.setVisibility(View.GONE);
                mStorePlace = mGson.fromJson(response, StorePlace.class);
                if (mStorePlace.getCode() == 200) {
                    mDatas.addAll(mStorePlace.getDatas());
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyApplication.getContext(), mStorePlace.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
