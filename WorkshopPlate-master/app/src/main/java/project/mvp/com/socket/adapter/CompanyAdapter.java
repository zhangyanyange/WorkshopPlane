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

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class CompanyAdapter extends BaseAdapter {

    private Context context;
    private Company company;
    public static int mPosition;

    public CompanyAdapter(Context context, Company company) {
        this.context = context;
        this.company = company;
    }

    @Override
    public int getCount() {
        return company.getDatas().size();
    }

    @Override
    public Object getItem(int position) {
        return company.getDatas().get(position);
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
        holder.tv.setText(company.getDatas().get(position).getCompanyName());
        if (position == ProductTaskActivity.mPosition) {
            holder.tv.setTextColor(Color.parseColor("#000000"));
            TextPaint paint = holder.tv.getPaint();
            paint.setFakeBoldText(true);
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv.setTextColor(Color.parseColor("#333333"));
            TextPaint paint = holder.tv.getPaint();
            paint.setFakeBoldText(false);
            convertView.setBackgroundColor(Color.parseColor("#F6F6F6"));
        }
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
