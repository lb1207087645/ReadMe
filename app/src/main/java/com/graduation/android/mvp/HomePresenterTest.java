package com.graduation.android.mvp;

import com.graduation.android.base.BasePresenterTest;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.mvp.HomeContractTest;

import java.util.List;


/**
 * home的p层
 */
public class HomePresenterTest extends BasePresenterTest<HomeContractTest.View> implements HomeContractTest.Presenter {

    public List<DesignRes> datas;


    @Override
    public void pullToLoadList() {
        loadData(1);
    }

    @Override
    public void loadList(final int page) {
        if (page == 1) {
            getView().showProgress();
        }

        loadData(page);
    }

    private void loadData(final int page) {
//        Observable<ListResponse<DesignRes>> observable = HttpRequest.getInstance().getDesignRes(page);
//        ObservableDecorator.decorate(observable).subscribe(
//                new Subscriber<ListResponse<DesignRes>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (!getView().isActive()) {
//                            return;
//                        }
//                        getView().dismissProgress();
//
//                        String error = ErrorInfoUtils.parseHttpErrorInfo(e);
//                        getView().showTip(error);
//                    }
//
//                    @Override
//                    public void onNext(ListResponse<DesignRes> response) {
//                        if (!getView().isActive()) {
//                            return;
//                        }
//                        getView().dismissProgress();
//
//                        datas = response.getResults();
//                        getView().loadListSuccess(page, datas);
//                    }
//                });
    }


}
