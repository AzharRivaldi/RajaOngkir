package com.azhar.rajaongkir.network;

import com.azhar.rajaongkir.data.city.ResponseCity;
import com.azhar.rajaongkir.data.cost.ResponseCost;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public interface ApiEndpoint {
    @GET("city")
    Single<ResponseCity> getCity();
    @FormUrlEncoded
    @POST("cost")
    Single<ResponseCost> postCost(
            @Field("origin") String origin,
            @Field("destination") String destination,
            @Field("weight") int weight,
            @Field("courier") String courier
    );
}
