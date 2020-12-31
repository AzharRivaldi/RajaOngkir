package com.azhar.rajaongkir.data.cost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class ResponseCost implements Serializable {
    @SerializedName("rajaongkir")
    @Expose
    public RajaOngkir rajaongkir;

    public RajaOngkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(RajaOngkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }
}
