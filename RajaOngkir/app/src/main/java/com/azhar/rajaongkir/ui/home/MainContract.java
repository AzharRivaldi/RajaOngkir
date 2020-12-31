package com.azhar.rajaongkir.ui.home;

import com.azhar.rajaongkir.data.cost.DataType;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public interface MainContract {
    interface Presenter{
        void getJNE();
        void getTIKI();
        void getPOS();
        void setupENV(String origin, String destination, int weight);
    }

    interface View{
        void onLoadingCost(boolean loadng, int progress);
        void onResultCost(List<DataType> data, List<String> courier);
        void onErrorCost();

        void showMessage(String msg);
        String getOrigin();
        String getDestination();
    }
}
