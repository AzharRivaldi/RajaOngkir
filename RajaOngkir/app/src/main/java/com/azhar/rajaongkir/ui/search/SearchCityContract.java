package com.azhar.rajaongkir.ui.search;

import android.widget.EditText;

import com.azhar.rajaongkir.data.city.DataCity;
import com.azhar.rajaongkir.data.city.ResponseCity;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public interface SearchCityContract {
    interface View{
        void onLoadingSearch(boolean loading);
        void onResultSearch(ResponseCity response);
        void onErrorSearch();

        void onResultInstantSearch(List<DataCity> data);

        void showMessage(String msg);
    }

    interface Presenter{
        void getCity();
        void instantSearch(EditText editText, List<DataCity> data);
        void searchCity(List<DataCity> data, String keyword);
        DisposableObserver<TextViewTextChangeEvent> observer(List<DataCity> data);
    }
}
