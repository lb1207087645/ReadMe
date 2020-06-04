package com.graduation.android.readme.base.widget.state.callback;

import com.graduation.android.readme.base.R;
import com.graduation.android.readme.base.widget.state.CommonBaseCallback;

/**
 * 正在加载状态UI
 *
 */

public class LoadingCallback extends CommonBaseCallback implements ILoadingCallback {

    @Override
    protected int onCreateView() {
        return R.layout.common_loading_view;
    }
}
