package com.azhar.rajaongkir.data.cost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class DataQuery implements Serializable {
    @SerializedName("origin")
    @Expose
    public String origin;
    @SerializedName("destination")
    @Expose
    public String destination;
    @SerializedName("weight")
    @Expose
    public Integer weight;
    @SerializedName("courier")
    @Expose
    public String courier;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}
