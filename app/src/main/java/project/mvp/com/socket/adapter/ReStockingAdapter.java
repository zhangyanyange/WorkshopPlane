package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.Company;
import project.mvp.com.socket.model.Machine;
import project.mvp.com.socket.model.ReStocking;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class ReStockingAdapter extends RecyclerView.Adapter<ReStockingAdapter.ViewHolder> {

    private Context context;
    private ReStocking reStocking;

    private ButtonReStock buttonReStock;
    private final LayoutInflater mLayoutInflater;

    public interface ButtonReStock {
        void listener(int position);
    }

    public void setButtonReStock(ButtonReStock buttonReStock) {
        this.buttonReStock = buttonReStock;
    }

    public ReStockingAdapter(Context context, ReStocking reStocking) {
        this.context = context;
        this.reStocking = reStocking;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_restocking, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.btStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonReStock != null) {
                    buttonReStock.listener(position);
                }
            }
        });

        holder.orderNo.setText(reStocking.getDatas().get(position).getBillNo());
    }

    @Override
    public int getItemCount() {
        return reStocking.getDatas().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bt_stock)
        Button btStock;
        @Bind(R.id.orderNo)
        TextView orderNo;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
