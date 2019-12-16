package com.graduation.android.base.loadmore;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.graduation.android.base.adapter.BaseViewHolder;


/**
 * 加载更多
 *
 * @author linbin
 * @Email
 * @date 2017/7/18 0018
 */
public abstract class LoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;

    private int mLoadMoreStatus = STATUS_DEFAULT;
    private boolean mLoadMoreEndGone = false;

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.mLoadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return mLoadMoreStatus;
    }

    public void convert(BaseViewHolder holder) {
        switch (mLoadMoreStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_FAIL:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_END:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            default:
                break;
        }
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getLoadingViewId(), visible);
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getLoadFailViewId(), visible);
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
        final int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setVisible(loadEndViewId, visible);
        }
    }

    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        return getLoadEndViewId() == 0 || mLoadMoreEndGone;
    }

    /**
     * No more data is hidden
     *
     * @return true for no more data hidden load more
     * @deprecated Use {@link BaseQuickAdapter#loadMoreEnd(boolean)} instead.
     */
    @Deprecated
    public boolean isLoadEndGone() {
        return mLoadMoreEndGone;
    }

    /**
     * load more layout
     *
     * @return resId
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * loading view
     *
     * @return resId
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * load fail view
     *
     * @return resId
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    /**
     * load end view, you can return 0
     *
     * @return resId
     */
    protected abstract
    @IdRes
    int getLoadEndViewId();
}
