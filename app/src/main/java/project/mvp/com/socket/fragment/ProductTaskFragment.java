package project.mvp.com.socket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import project.mvp.com.socket.AddUnitActivity;
import project.mvp.com.socket.PackingActivity;
import project.mvp.com.socket.R;
import project.mvp.com.socket.SearchActivity;
import project.mvp.com.socket.adapter.ProductTaskAdapter;
import project.mvp.com.socket.application.MyApplication;
import project.mvp.com.socket.model.LoadDone;
import project.mvp.com.socket.model.MessageEvent;
import project.mvp.com.socket.model.ProductTask;
import project.mvp.com.socket.pulltorefreshrecyclerview.OnRefreshListener;
import project.mvp.com.socket.pulltorefreshrecyclerview.PullToRefreshRecyclerView;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class ProductTaskFragment extends Fragment {

    public static final String TAG = "MyFragment";
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.loadView)
    RelativeLayout loadView;
    @Bind(R.id.pulltoRefresh)
    PullToRefreshRecyclerView pulltoRefresh;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    private int id;
    private Gson mGson;
    private ProductTaskAdapter mAdapter;
    private ProductTask mProductTask;
    private List<ProductTask.DatasBean> mDatas;

    private int index = 1;

    private int size = 100;
    private double maxCount = 0;
    private String mTargetIp;
    private int mMachineId;
    private String mScanningName;
    private String mShumeipai;
    private String mCompanyName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_task_item, null);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mGson = new Gson();
        //得到数据
        mDatas = new ArrayList<>();
        Bundle arguments = getArguments();
        id = arguments.getInt(TAG);
        mTargetIp = arguments.getString("targetIp");
        mMachineId = arguments.getInt("machineId");
        mScanningName = arguments.getString("scanningName");
        mShumeipai = arguments.getString("shumeipai");
        mCompanyName = arguments.getString("companyName");
        pulltoRefresh.setPullToRefreshEnabled(false);
        pulltoRefresh.loadingMoreFooter.setVisibility(View.GONE);
        pulltoRefresh.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        loadView.setVisibility(View.VISIBLE);

        llSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getContext(), SearchActivity.class);
                intent.putExtra("leave","max");
                intent.putExtra("companyId", id);
                intent.putExtra("targetIp",mTargetIp);
                intent.putExtra("machineId",mMachineId);
                intent.putExtra("scanningName",mScanningName);
                intent.putExtra("shumeipai",mShumeipai);
                intent.putExtra("companyName",mCompanyName);
                startActivity(intent);
            }
        });

        OkHttpUtils.get().url(MyApplication.baseUrl+"api/ProductOrder?index=" + index + "&size=" + size + "&companyid=" + id).tag(id).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                loadView.setVisibility(View.GONE);
                if (!(e.toString().equals("java.io.IOException: Canceled")) && !(e.toString().equals("java.net.SocketException: Socket closed"))) {
                    Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onResponse(String response) {
                pulltoRefresh.loadingMoreFooter.setVisibility(View.VISIBLE);
                loadView.setVisibility(View.GONE);
                mProductTask = mGson.fromJson(response, ProductTask.class);
                mDatas.addAll(mProductTask.getDatas());
                mAdapter = new ProductTaskAdapter(MyApplication.getContext(), mDatas);
                pulltoRefresh.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
                pulltoRefresh.setAdapter(mAdapter);
                mAdapter.setClickListener(new ProductTaskAdapter.onItemClickListener() {
                    @Override
                    public void clickItem(int position) {
                        //跳转到PackingActivity
                        Intent intent = new Intent(MyApplication.getContext(), PackingActivity.class);
                        intent.putExtra("goodNumber", mDatas.get(position).getGoodNumber());
                        intent.putExtra("leave","max");
                        intent.putExtra("companyId", id);
                        intent.putExtra("targetIp",mTargetIp);
                        intent.putExtra("machineId",mMachineId);
                        intent.putExtra("scanningName",mScanningName);
                        intent.putExtra("productName",mDatas.get(position).getFproductName());
                        intent.putExtra("billNumber",mDatas.get(position).getFicmobillNo());
                        intent.putExtra("productModel",mDatas.get(position).getFproductModel());
                        intent.putExtra("companyName",mCompanyName);
                        intent.putExtra("shumeipai",mShumeipai);
                        startActivity(intent);
                    }
                });

                mAdapter.setAddPackingItem(new ProductTaskAdapter.addPackingItem() {
                    @Override
                    public void addPackingItem(int position) {
                        Intent intent1=new Intent(MyApplication.getContext(),AddUnitActivity.class);
                        intent1.putExtra("goodNumbers",mDatas.get(position).getGoodNumber());
                        intent1.putExtra("CompanyId",id);
                        intent1.putExtra("ProductName",mDatas.get(position).getFproductName());
                        startActivity(intent1);
                    }
                });
                maxCount = Math.ceil(mProductTask.getTotal() / 100.0);

                LoadDone loadDone = new LoadDone();
                loadDone.setLoadDone(true);
                EventBus.getDefault().post(loadDone);

                if (mProductTask.getCode() != 200) {
                    mDatas.clear();
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(MyApplication.getContext(), mProductTask.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        pulltoRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                pulltoRefresh.refreshComplete();
            }

            @Override
            public void onRefreshTimeOut() {

            }

            @Override
            public void onLoadMore() {

                index = index + 1;
                if (index <= maxCount) {
                    OkHttpUtils.get().url(MyApplication.baseUrl+"api/ProductOrder?index=" + index + "&size=" + size + "&companyid=" + id).tag(id).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            if (!(e.toString().equals("java.io.IOException: Canceled")) && !(e.toString().equals("java.net.SocketException: Socket closed"))) {
                                Toast.makeText(MyApplication.getContext(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onResponse(String response) {
                            mProductTask = mGson.fromJson(response, ProductTask.class);
                            mDatas.addAll(mProductTask.getDatas());
                            mAdapter.notifyDataSetChanged();
                            pulltoRefresh.loadingMoreComplete();
                        }
                    });
                } else {
                    pulltoRefresh.loadingMoreComplete();
                    pulltoRefresh.setNoMore(true);
                }

            }

            @Override
            public void onLoadMoreTimeOut() {

            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        pulltoRefresh.loadingMoreFooter.setVisibility(View.GONE);
        loadView.setVisibility(View.VISIBLE);
        OkHttpUtils.getInstance().cancelTag(id);
        id = event.getCpmpanyId();
        mCompanyName=event.getCompanyName();
        index = 1;
        mDatas.clear();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        OkHttpUtils.get().url(MyApplication.baseUrl+"api/ProductOrder?index=" + index + "&size=" + size + "&companyid=" + id).tag(id).build().execute(new StringCallback() {
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
                mProductTask = mGson.fromJson(response, ProductTask.class);
                if (mProductTask.getCode() == 200) {
                    maxCount = Math.ceil(mProductTask.getTotal() / 100.0);
                    pulltoRefresh.loadingMoreFooter.setVisibility(View.VISIBLE);
                    mDatas.addAll(mProductTask.getDatas());
                    mAdapter.notifyDataSetChanged();
                } else {
                    pulltoRefresh.loadingMoreFooter.setVisibility(View.GONE);
                    Toast.makeText(MyApplication.getContext(), mProductTask.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

