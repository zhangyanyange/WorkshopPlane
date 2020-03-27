package project.mvp.com.socket.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.mvp.com.socket.ProductTaskActivity;
import project.mvp.com.socket.R;
import project.mvp.com.socket.model.Company;
import project.mvp.com.socket.model.LabelDetail;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class PackageAdapter extends BaseAdapter {

    private Context context;
    private LabelDetail detail;
    public static int mPosition;

    public PackageAdapter(Context context, LabelDetail detail) {
        this.context = context;
        this.detail = detail;
    }

    @Override
    public int getCount() {
        return detail.getDatas().size();
    }

    @Override
    public Object getItem(int position) {
        return detail.getDatas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView =  View.inflate(context,R.layout.company_classificaton, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        mPosition = position;
        holder.tv.setText(detail.getDatas().get(position).getMark());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
