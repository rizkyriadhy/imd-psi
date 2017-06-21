package com.imdglobal.psi.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rizkyriadhy on 19/06/17.
 */

public class LabelLocation {

@SerializedName("latitude")
@Expose
private double latitude;
@SerializedName("longitude")
@Expose
private double longitude;

public double getLatitude() {
return latitude;
}

public void setLatitude(int latitude) {
this.latitude = latitude;
}

public double getLongitude() {
return longitude;
}

public void setLongitude(int longitude) {
this.longitude = longitude;
}

}