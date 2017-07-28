package com.codetanklabs.rxjavasample.network;

import com.codetanklabs.rxjavasample.model.NetworkHand;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by andrewmay on 4/11/17.
 */

public interface RequestInterface {

    /**
     * NO AUTH
     */

    @GET("api/v1/game/androidHand")
    Observable<NetworkHand> getNetworkHand();

}
