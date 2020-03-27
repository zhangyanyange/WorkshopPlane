package project.mvp.com.socket.pulltorefreshrecyclerview;

/**
 * Created by Jason on 2017/4/3.
 */

public interface OnRefreshListener {
    void onRefresh();
    void onRefreshTimeOut();
    void onLoadMore();
    void onLoadMoreTimeOut();
}
