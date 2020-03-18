package project.mvp.com.socket.pulltorefreshrecyclerview;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by Jason on 2017/4/2.
 */

/**
 * RecyclerView刷新头部
 */
public class RefreshHeader extends LinearLayout implements BaseRefreshHeader {

    public RefreshHeader(Context context) {
        super(context);
    }

    @Override
    public void onMove(float delta) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public boolean onRelease() {
        return false;
    }

    @Override
    public void onStateChange(int state) {

    }
}
