package com.graduation.android.news.mvp;

import android.app.Activity;
import android.util.Log;

import com.graduation.android.R;
import com.graduation.android.base.BasePresenterTest;
import com.graduation.android.base.network.ErrorEntity;
import com.graduation.android.bean.NewsDetail;
import com.graduation.android.entity.DesignRes;
import com.graduation.android.http.BaseObserver;
import com.graduation.android.http.BaseResponse;
import com.graduation.android.model.FreshBean;
import com.graduation.android.model.FreshNewsBean;
import com.graduation.android.model.HomeModel;
import com.graduation.android.model.NewsModel;
import com.graduation.android.model.Translation3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * 新闻的p层
 */
public class NewsPresenterTest extends BasePresenterTest<NewsContractTest.View> implements NewsContractTest.Presenter {

    public List<DesignRes> datas;

    private Activity mActivity;

    @Override
    public void pullToLoadList() {
        loadData(1);
    }

    @Override
    public void getCall(String id, String action, int pullNum) {

    }

    private NewsModel model;
    private NewsPresenterTest mHomePresenterTest;

    public NewsPresenterTest(Activity activity) {
        mActivity = activity;
        mHomePresenterTest = this;

        model = new NewsModel();
    }


    /**
     * 新闻数据回调
     */
//    @Override
//    public void getCall(final String id, final String action, int pullNum) {
//        subscribe(model.getNewsDetail(id, action, pullNum), new BaseObserver<List<NewsDetail>>() {
//            @Override
//            public void onError(ErrorEntity err) {
//                getView().showErr(err);
//                getView().loadSimple(null);
//            }
//
//            @Override
//            public void onAfter() {
//
//            }
//
//            @Override
//            public void onData(BaseResponse<List<NewsDetail>> baseResponse) {
//
//            }
//
//
//        });
//
//
//    }
    @Override
    public void getFreshNews(int page) {
        subscribe(model.getFreshNews(page), new BaseObserver<FreshNewsBean>() {
            @Override
            public void onError(ErrorEntity err) {
                getView().showErr(err);
                getView().loadFreshNews(null);
            }

            @Override
            public void onAfter() {

            }

            @Override
            public void onData(FreshNewsBean baseResponse) {
                getView().loadFreshNews(changeServerData(baseResponse));//转化数据
            }
        });
    }

    private List<FreshBean.PostsBean> changeServerData(FreshNewsBean freshNewsBean) {
        List<FreshBean.PostsBean> studyBeanList = new ArrayList<>();
        for (int i = 0; i < freshNewsBean.getPosts().size(); i++) {
            FreshNewsBean.PostsBean postsBean = freshNewsBean.getPosts().get(i);
            FreshBean.PostsBean studyBean = new FreshBean.PostsBean();
            studyBean.setItemType(NewsDetail.ItemBean.TYPE_DOC_TITLEIMG);
            studyBean.setTitle(postsBean.getTitle());
//            studyBean.setUrl(postsBean.getUrl());
           // studyBean.getAuthor().setName(postsBean.getAuthor().getName());
            studyBean.setComment_count(postsBean.getComment_count());
            studyBeanList.add(studyBean);
        }
        return studyBeanList;
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
