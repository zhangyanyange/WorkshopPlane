package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.ProductTask;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class ProductTaskAdapter extends RecyclerView.Adapter<ProductTaskAdapter.ViewHolder> {

    @Bind(R.id.product_item)
    LinearLayout productItem;
    private Context mContext;
    private List<ProductTask.DatasBean> mDatasBeans;
    private final LayoutInflater mLayoutInflater;

    private onItemClickListener mClickListener;

    private addPackingItem mAddPackingItem;

    public interface onItemClickListener {
        void clickItem(int position);
    }

    public void setClickListener(onItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface addPackingItem{
        void addPackingItem(int position);
    }

    public void setAddPackingItem(addPackingItem addPackingItem) {
        mAddPackingItem = addPackingItem;
    }

    public ProductTaskAdapter(Context context, List<ProductTask.DatasBean> Datas) {
        mContext = context;
        mDatasBeans = Datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.adapter_product_task, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.productNumber.setText(mDatasBeans.get(position).getFicmobillNo());
        holder.productName.setText(mDatasBeans.get(position).getFproductName());
        holder.product_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.clickItem(position);
                }
            }
        });

        holder.add_packing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAddPackingItem!=null){
                    mAddPackingItem.addPackingItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatasBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.product_number)
        TextView productNumber;
        @Bind(R.id.product_name)
        TextView productName;
        @Bind(R.id.product_item)
        LinearLayout product_item;
        @Bind(R.id.add_packing)
        Button add_packing;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
