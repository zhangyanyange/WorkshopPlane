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
import project.mvp.com.socket.R;
import project.mvp.com.socket.WarehouseActivity;
import project.mvp.com.socket.model.Store;

/**
 * 创建人 张岩
 * 时间   2019/6/4.
 * 类描述
 */
public class StoreAdapter extends BaseAdapter {

    private Context context;
    private Store mStore;
    public static int storePosition;

    public StoreAdapter(Context context, Store store) {
        this.context = context;
        mStore = store;
    }

    @Override
    public int getCount() {
        return mStore.getDatas().size();
    }

    @Override
    public Object getItem(int position) {
        return mStore.getDatas().get(position);
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
        storePosition = position;
        holder.tv.setText(mStore.getDatas().get(position).getStoreName());
        if (position == WarehouseActivity.storePosition) {
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
