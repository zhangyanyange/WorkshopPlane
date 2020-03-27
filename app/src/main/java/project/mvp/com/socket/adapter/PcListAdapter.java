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
import project.mvp.com.socket.model.Machine;

/**
 * 创建人 张岩
 * 时间   2019/5/31.
 * 类描述
 */
public class PcListAdapter extends RecyclerView.Adapter<PcListAdapter.ViewHolder> {
    private Machine mMachine;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    private CheckboxClickListener mListener;
    public  interface CheckboxClickListener{
        void checkboxEvent(int position,boolean isCheck);
    }
    public void setListener(CheckboxClickListener listener) {
        mListener = listener;
    }

    public PcListAdapter(Machine machine, Context context) {
        mMachine = machine;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.pclist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final List<Machine.DatasBean> datas = mMachine.getDatas();
        holder.machineName.setText(datas.get(position).getMachineName());
        holder.machineIp.setText(datas.get(position).getMachineIp());
        holder.machineRemark.setText(datas.get(position).getRemark());

        // 先设置一次CheckBox的选中监听器，传入参数null,防止多次监听错乱
       holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(datas.get(position).isCheckStatus());

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mListener!=null){
                    mListener.checkboxEvent(position,b);
                }
                datas.get(position).setCheckStatus(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMachine==null?0:mMachine.getDatas().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cb)
        CheckBox cb;
        @Bind(R.id.machineName)
        TextView machineName;
        @Bind(R.id.machineIp)
        TextView machineIp;
        @Bind(R.id.machineRemark)
        TextView machineRemark;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
