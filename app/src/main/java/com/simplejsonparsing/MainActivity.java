package com.simplejsonparsing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.simplejsonparsing.api.dao.MainDao;
import com.simplejsonparsing.api.endpoint.BaseEndPoint;
import com.simplejsonparsing.api.service.BaseService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rec_main_movie)
    RecyclerView mRecMovie;

    public LinearLayoutManager mLinearLayoutManager;
    public MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        try {
            getListMovie(this);
        } catch (Exception e) {
            Log.e(MainActivity.class.getSimpleName(), e.getMessage());
        }
    }

    public void getListMovie(final Context mContext) {
        Observable<MainDao> mCall = BaseEndPoint.getEndPoint().create(BaseService.class).getMovieList();
        mCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainDao>() {
                    @Override
                    public void onCompleted() {
                        onNotifyData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(MainActivity.class.getSimpleName(), e.getMessage());
                        getListMovie(mContext);
                    }

                    @Override
                    public void onNext(MainDao mainDao) {
                        onSetData(mContext, mainDao.getResults());
                    }
                });
    }

    public void onNotifyData() {
        mAdapter.notifyDataSetChanged();
        mRecMovie.getAdapter().notifyDataSetChanged();
    }

    public void onSetData(Context mContext,
                          List<MainDao.ResultsBean> mData) {
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new MainAdapter(mContext, mData);
        mRecMovie.setLayoutManager(mLinearLayoutManager);
        mRecMovie.setItemAnimator(new DefaultItemAnimator());
        mRecMovie.setHasFixedSize(true);
        mRecMovie.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
//        this.mSubscription.unsubscribe();
        super.onDestroy();
    }
}
