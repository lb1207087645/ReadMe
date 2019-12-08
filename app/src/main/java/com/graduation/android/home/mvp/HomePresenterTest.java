package com.graduation.android.home.mvp;

import android.app.Activity;

import com.graduation.android.base.BasePresenterTest;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.entity.DesignRes;

import com.graduation.android.http.BaseObserver;
import com.graduation.android.http.BaseResponse;
import com.graduation.android.model.HomeModel;
import com.graduation.android.model.Translation3;

import java.util.List;


/**
 * home的p层
 */
public class HomePresenterTest extends BasePresenterTest<HomeContractTest.View> implements HomeContractTest.Presenter {

    public List<DesignRes> datas;

    private Activity mActivity;

    @Override
    public void pullToLoadList() {
        loadData(1);
    }

    private HomeModel model;
    private HomePresenterTest mHomePresenterTest;

    public HomePresenterTest(Activity activity) {
        mActivity = activity;
        mHomePresenterTest = this;

        model = new HomeModel();
    }


    /**
     * 简单文本回调
     */
    @Override
    public void getCall() {
        subscribe(model.getAudioDetailInfo(), new BaseObserver<Translation3.content>() {
            @Override
            public void onError(ErrorEntity err) {
                getView().showErr(err);
                getView().loadSimple(null);
            }

            @Override
            public void onAfter() {

            }

            @Override
            public void onData(BaseResponse<Translation3.content> baseResponse) {
                getView().loadSimple(baseResponse.data.out);
            }
        });

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
