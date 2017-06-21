package com.imdglobal.psi.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rizkyriadhy on 19/06/17.
 */

public class So2TwentyFourHourly {

@SerializedName("east")
@Expose
private int east;
@SerializedName("central")
@Expose
private int central;
@SerializedName("south")
@Expose
private int south;
@SerializedName("north")
@Expose
private int north;
@SerializedName("west")
@Expose
private int west;
@SerializedName("national")
@Expose
private int national;

public int getEast() {
return east;
}

public void setEast(int east) {
this.east = east;
}

public int getCentral() {
return central;
}

public void setCentral(int central) {
this.central = central;
}

public int getSouth() {
return south;
}

public void setSouth(int south) {
this.south = south;
}

public int getNorth() {
return north;
}

public void setNorth(int north) {
this.north = north;
}

public int getWest() {
return west;
}

public void setWest(int west) {
this.west = west;
}

public int getNational() {
return national;
}

public void setNational(int national) {
this.national = national;
}

}

