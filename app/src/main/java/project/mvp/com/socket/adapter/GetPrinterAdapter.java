package project.mvp.com.socket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.Printer;
import project.mvp.com.socket.model.Scanning;

/**
 * 创建人 张岩
 * 时间   2019/5/31.
 * 类描述
 */
public class GetPrinterAdapter extends RecyclerView.Adapter<GetPrinterAdapter.ViewHolder> {

    private Printer mPrinter;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    private CheckboxClickListener mListener;

    public interface CheckboxClickListener {
        void checkboxEvent(int position, boolean isCheck);
    }

    public void setListener(CheckboxClickListener listener) {
        mListener = listener;
    }

    public GetPrinterAdapter(Printer printer, Context context) {
        mPrinter = printer;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.scanning_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        List<String> datas = mPrinter.getDatas();
        holder.scanningName.setText(datas.get(position));

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱
        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(false);

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mListener != null) {
                    mListener.checkboxEvent(position, b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPrinter== null ? 0 : mPrinter.getDatas().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cb)
        CheckBox cb;
        @Bind(R.id.scanning_name)
        TextView scanningName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
