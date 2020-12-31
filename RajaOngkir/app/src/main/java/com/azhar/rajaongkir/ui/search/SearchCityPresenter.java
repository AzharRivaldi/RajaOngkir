package com.azhar.rajaongkir.ui.search;

import android.widget.EditText;

import com.azhar.rajaongkir.data.city.DataCity;
import com.azhar.rajaongkir.data.city.ResponseCity;
import com.azhar.rajaongkir.network.Api;
import com.azhar.rajaongkir.network.ApiEndpoint;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class SearchCityPresenter implements SearchCityContract.Presenter {

    SearchCityContract.View view;
    ApiEndpoint endpoint;

    public SearchCityPresenter(SearchCityContract.View view) {
        this.view = view;
        endpoint = Api.getUrl().create(ApiEndpoint.class);
    }

    @Override
    public void getCity() {
        view.onLoadingSearch(true);
        endpoint.getCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseCity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseCity responseCity) {
                        view.onLoadingSearch(false);
                        view.onResultSearch(responseCity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onLoadingSearch(false);
                        view.showMessage(e.getMessage());
                    }
                });
    }

    @Override
    public void instantSearch(EditText editText, List<DataCity> data) {
        new CompositeDisposable().add(
                RxTextView.textChangeEvents(editText)
                    .skipInitialValue()
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(observer(data))
        );
    }

    @Override
    public void searchCity(List<DataCity> data, String keyword) {
        List<DataCity> output = new ArrayList<>();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getCityName().equalsIgnoreCase(keyword)){
                output.add(data.get(i));
            }
        }
        view.onResultInstantSearch(output);
    }

    @Override
    public DisposableObserver<TextViewTextChangeEvent> observer(List<DataCity> data) {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                String keyword = textViewTextChangeEvent.getText().toString();
                searchCity(data, keyword);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
