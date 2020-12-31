package com.azhar.rajaongkir.ui.home;

import android.os.Handler;

import com.azhar.rajaongkir.data.cost.DataType;
import com.azhar.rajaongkir.data.cost.ResponseCost;
import com.azhar.rajaongkir.network.Api;
import com.azhar.rajaongkir.network.ApiEndpoint;
import com.azhar.rajaongkir.network.Helper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;
    ApiEndpoint endpoint;

    String origin = "";
    String destination = "";
    int weight = 0;

    List<DataType> output = new ArrayList<>();
    List<String> courier = new ArrayList<>();

    public MainPresenter(MainContract.View view) {
        this.view = view;
        endpoint = Api.getUrl().create(ApiEndpoint.class);
    }

    @Override
    public void getJNE() {
        output.clear();
        courier.clear();
        endpoint.postCost(origin, destination, weight, "jne")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCost>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseCost responseCost) {
                        for (DataType data : responseCost.getRajaongkir().getResults().get(0).getCosts()) {
                            output.add(data);
                            courier.add("JNE");
                        }
                        Helper.jne = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void getTIKI() {
        endpoint.postCost(origin, destination, weight, "tiki")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCost>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseCost responseCost) {
                        for (DataType data : responseCost.getRajaongkir().getResults().get(0).getCosts()) {
                            output.add(data);
                            courier.add("TIKI");
                        }
                        Helper.tiki = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void getPOS() {
        endpoint.postCost(origin, destination, weight, "pos")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCost>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseCost responseCost) {
                        for (DataType data : responseCost.getRajaongkir().getResults().get(0).getCosts()) {
                            output.add(data);
                            courier.add("POS");
                        }

                        Helper.pos = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void setupENV(String origin, String destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;

        view.onLoadingCost(true, 25);

        getJNE();
        getPOS();
        getTIKI();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Helper.jne && Helper.pos && Helper.tiki) {
                    view.onLoadingCost(false, 100);
                    view.onResultCost(output, courier);
                }
            }
        }, 4000L);

    }
}
