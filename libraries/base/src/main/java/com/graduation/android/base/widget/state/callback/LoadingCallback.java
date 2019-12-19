package com.graduation.android.base.widget.state.callback;

import com.graduation.android.base.R;
import com.graduation.android.base.widget.state.CommonBaseCallback;

/**
 * 正在加载状态UI
 *
 * @date：2017/10/10
 * @author：chenqq
 * @company: www.babybus.com
 * @email: ym_qqchen@sina.com
 */

public class LoadingCallback extends CommonBaseCallback implements ILoadingCallback {

    @Override
    protected int onCreateView() {
        return R.layout.common_loading_view;
    }
}
