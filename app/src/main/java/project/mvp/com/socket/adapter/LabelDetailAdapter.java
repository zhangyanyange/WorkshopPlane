package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.LabelDetail;
import project.mvp.com.socket.model.LabelPathOrder;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class LabelDetailAdapter extends RecyclerView.Adapter<LabelDetailAdapter.ViewHolder> {

    private Context mContext;
    private List<LabelDetail.DatasBean> detail;
    private final LayoutInflater mLayoutInflater;

    private onItemClickListener mClickListener;

    public interface onItemClickListener {
        void clickItem(int position);
    }

    public void setClickListener(onItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public LabelDetailAdapter(Context context, List<LabelDetail.DatasBean> labelDetail) {
        mContext = context;
        this.detail = labelDetail;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_label_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.goodName.setText(detail.get(position).getPackageInfo().getGoodName());
        holder.descript.setText(detail.get(position).getPackageInfo().getDescription());
        holder.time.setText(detail.get(position).getPackageInfo().getCreateDate().substring(0,10));
        holder.mark.setText(detail.get(position).getMark());

        holder.btMakeUp.setOnClickListener(new View.OnClickListener() {
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
        return detail.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.good_name)
        TextView goodName;
        @Bind(R.id.descript)
        TextView descript;
        @Bind(R.id.mark)
        TextView mark;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.bt_make_up)
        Button btMakeUp;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
