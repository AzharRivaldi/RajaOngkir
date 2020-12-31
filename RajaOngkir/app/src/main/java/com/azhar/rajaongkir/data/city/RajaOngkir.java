package com.azhar.rajaongkir.data.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class RajaOngkir implements Serializable {
    @SerializedName("query")
    @Expose
    public List<Object> query = null;
    @SerializedName("status")
    @Expose
    public DataStatus status;
    @SerializedName("results")
    @Expose
    public List<DataCity> results = null;

    public List<Object> getQuery() {
        return query;
    }

    public void setQuery(List<Object> query) {
        this.query = query;
    }

    public DataStatus getStatus() {
        return status;
    }

    public void setStatus(DataStatus status) {
        this.status = status;
    }

    public List<DataCity> getResults() {
        return results;
    }

    public void setResults(List<DataCity> results) {
        this.results = results;
    }
}
