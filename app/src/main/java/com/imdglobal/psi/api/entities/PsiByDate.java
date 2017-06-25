package com.imdglobal.psi.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.imdglobal.psi.api.rest.data.RestResponseBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizkyriadhy on 19/06/17.
 */

public class PsiByDate extends RestResponseBase {

    @SerializedName("region_metadata")
    @Expose
    private ArrayList<RegionMetadatum> regionMetadata = new ArrayList<>();
    @SerializedName("items")
    @Expose
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<RegionMetadatum> getRegionMetadata() {
        return regionMetadata;
    }

    public void setRegionMetadata(ArrayList<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
