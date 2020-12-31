package com.azhar.rajaongkir.data.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Azhar Rivaldi on 25-12-2020
 */

public class DataCity implements Serializable {
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("province_id")
    @Expose
    public String provinceId;
    @SerializedName("province")
    @Expose
    public String province;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("city_name")
    @Expose
    public String cityName;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
