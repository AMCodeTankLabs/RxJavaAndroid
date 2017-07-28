package com.codetanklabs.rxjavasample.network;

import android.content.Context;

import com.codetanklabs.rxjavasample.model.NetworkHand;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrewmay on 4/17/17.
 */

public class HandService {

    private RequestInterface mRequestInterface;

    public HandService(Context ctx) {

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5,TimeUnit.MINUTES)
                .cache(new Cache(ctx.getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .build();

        mRequestInterface = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
    }

    public DisposableObserver<NetworkHand> getNetworkHand(final HandCallback callback) {
        return mRequestInterface.getNetworkHand()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(
                        new DisposableObserver<NetworkHand>() {
                            @Override
                            public void onNext(NetworkHand value) {
                                callback.onSuccess(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                callback.onError(e);
                            }

                            @Override
                            public void onComplete() {
                                callback.onComplete();
                            }
                        }
                );
    }

    public interface HandCallback {
        void onSuccess(NetworkHand hand);
        void onError(Throwable e);
        void onComplete();
    }

}
