package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.ViewWorkConfig;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class WorkConfigAdapter extends RecyclerView.Adapter<WorkConfigAdapter.ViewHolder> {

    @Bind(R.id.product_item)
    RelativeLayout productItem;
    private Context mContext;
    private List<ViewWorkConfig.DatasBean> mDatasBeans;
    private final LayoutInflater mLayoutInflater;

    private onItemClickListener mClickListener;

    private deleteProductTask mDeleteProductTask;

    public void setDeleteProductTask(deleteProductTask deleteProductTask) {
        mDeleteProductTask = deleteProductTask;
    }

    public interface onItemClickListener {
        void clickItem(int position,int state);
    }

    public void setClickListener(onItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface  deleteProductTask{
        void deleteItem(int position);
    }
    public WorkConfigAdapter(Context context, List<ViewWorkConfig.DatasBean> Datas) {
        mContext = context;
        mDatasBeans = Datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.work_config_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.configureName.setText(mDatasBeans.get(position).getRemark());
        holder.testProductTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.clickItem(position,1);
                }
            }
        });
        holder.normalProductTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClickListener!=null){
                    mClickListener.clickItem(position,2);
                }
            }
        });
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDeleteProductTask!=null){
                    mDeleteProductTask.deleteItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatasBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.configure_name)
        TextView configureName;
        @Bind(R.id.delete)
        ImageView iv_delete;
        @Bind(R.id.product_item)
        RelativeLayout product_item;
        @Bind(R.id.testProductTask)
        Button testProductTask;
        @Bind(R.id.normalProductTask)
         Button normalProductTask;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
