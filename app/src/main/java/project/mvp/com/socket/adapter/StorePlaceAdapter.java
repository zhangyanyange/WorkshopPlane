package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.StorePlace;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class StorePlaceAdapter extends RecyclerView.Adapter<StorePlaceAdapter.ViewHolder> {

    private Context mContext;
    private List<StorePlace.DatasBean> mDatasBeans;
    private final LayoutInflater mLayoutInflater;

    private onItemClickListener mClickListener;

    public interface onItemClickListener {
        void clickItem(int position);
    }

    public void setClickListener(onItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public StorePlaceAdapter(Context context, List<StorePlace.DatasBean> Datas) {
        mContext = context;
        mDatasBeans = Datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.store_place_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.storeName.setText(mDatasBeans.get(position).getStorePliceName());
        holder.product_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.clickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatasBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.store_name)
        TextView storeName;
        @Bind(R.id.product_item)
        RelativeLayout product_item;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
