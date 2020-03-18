package project.mvp.com.socket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import project.mvp.com.socket.model.LabelDetail;

public class MyComparator implements Comparator<LabelDetail.DatasBean> {
    @Override
    public int compare(LabelDetail.DatasBean o, LabelDetail.DatasBean t1) {
        int who=0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //返回时间至少是20位，服务器已经约定。
            long  time1 = simpleDateFormat.parse(o.getPackageInfo().getCreateDate().substring(0,10)+" "+o.getPackageInfo().getCreateDate().substring(11,19)).getTime();
            long time2 = simpleDateFormat.parse(t1.getPackageInfo().getCreateDate().substring(0,10)+" "+t1.getPackageInfo().getCreateDate().substring(11,19)).getTime();
            if(time2-time1>0){
                who=-1;
            }else{
                who=1;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return who;
    }
}
