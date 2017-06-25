package com.imdglobal.psi;

import com.imdglobal.psi.api.entities.CoEightHourMax;
import com.imdglobal.psi.api.entities.CoSubIndex;
import com.imdglobal.psi.api.entities.Item;
import com.imdglobal.psi.api.entities.LabelLocation;
import com.imdglobal.psi.api.entities.No2OneHourMax;
import com.imdglobal.psi.api.entities.O3EightHourMax;
import com.imdglobal.psi.api.entities.O3SubIndex;
import com.imdglobal.psi.api.entities.Pm10SubIndex;
import com.imdglobal.psi.api.entities.Pm10TwentyFourHourly;
import com.imdglobal.psi.api.entities.Pm25SubIndex;
import com.imdglobal.psi.api.entities.Pm25TwentyFourHourly;
import com.imdglobal.psi.api.entities.PsiTwentyFourHourly;
import com.imdglobal.psi.api.entities.Readings;
import com.imdglobal.psi.api.entities.RegionMetadatum;
import com.imdglobal.psi.api.entities.So2SubIndex;
import com.imdglobal.psi.api.entities.So2TwentyFourHourly;
import com.imdglobal.psi.api.rest.RestConnect;
import com.imdglobal.psi.api.rest.data.PsiByDates;
import com.imdglobal.psi.utils.ImdGlobalPSIConst;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.mock.BehaviorDelegate;

/**
 * Created by rizkyriadhy-ebizu on 6/25/17.
 */

public class MockPSIDataRestConnect implements RestConnect {

    private final BehaviorDelegate<RestConnect> delegate;

    public MockPSIDataRestConnect(BehaviorDelegate<RestConnect> service) {
        this.delegate = service;
    }

    @Override
    public Call<PsiByDates.Response> psiByDates(@Query("date") String date, @Header("api-key") String apikey) {
        PsiByDates.Response psiByDatesResponse = new PsiByDates.Response();

        ArrayList<RegionMetadatum> regionMetadata = new ArrayList<>();
        RegionMetadatum regionMetadatum = new RegionMetadatum();
        LabelLocation labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.94);
        regionMetadatum.setName("east");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("central");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.29587);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("south");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.41803);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("north");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.7);
        regionMetadatum.setName("west");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(0);
        labelLocation.setLongitude(0);
        regionMetadatum.setName("national");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);


        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setTimestamp("2017-06-12T08:00:00+08:00");
        item.setUpdateTimestamp("2017-06-12T08:06:18+08:00");

        Readings readings = new Readings();
        O3SubIndex o3SubIndex = new O3SubIndex();
        o3SubIndex.setEast(3);
        o3SubIndex.setCentral(4);
        o3SubIndex.setSouth(3);
        o3SubIndex.setNorth(5);
        o3SubIndex.setWest(4);
        o3SubIndex.setNational(5);
        readings.setO3SubIndex(o3SubIndex);

        Pm10TwentyFourHourly pm10TwentyFourHourly = new Pm10TwentyFourHourly();
        pm10TwentyFourHourly.setEast(14);
        pm10TwentyFourHourly.setCentral(12);
        pm10TwentyFourHourly.setSouth(21);
        pm10TwentyFourHourly.setNorth(18);
        pm10TwentyFourHourly.setWest(17);
        pm10TwentyFourHourly.setNational(21);
        readings.setPm10TwentyFourHourly(pm10TwentyFourHourly);

        Pm10SubIndex pm10SubIndex = new Pm10SubIndex();
        pm10SubIndex.setEast(14);
        pm10SubIndex.setCentral(12);
        pm10SubIndex.setSouth(21);
        pm10SubIndex.setNorth(18);
        pm10SubIndex.setWest(17);
        pm10SubIndex.setNational(21);
        readings.setPm10SubIndex(pm10SubIndex);

        CoSubIndex coSubIndex = new CoSubIndex();
        coSubIndex.setEast(4);
        coSubIndex.setCentral(5);
        coSubIndex.setSouth(2);
        coSubIndex.setNorth(4);
        coSubIndex.setWest(4);
        coSubIndex.setNational(5);
        readings.setCoSubIndex(coSubIndex);

        Pm25TwentyFourHourly pm25TwentyFourHourly = new Pm25TwentyFourHourly();
        pm25TwentyFourHourly.setEast(8);
        pm25TwentyFourHourly.setCentral(6);
        pm25TwentyFourHourly.setSouth(9);
        pm25TwentyFourHourly.setNorth(10);
        pm25TwentyFourHourly.setWest(7);
        pm25TwentyFourHourly.setNational(10);
        readings.setPm25TwentyFourHourly(pm25TwentyFourHourly);

        So2SubIndex so2SubIndex = new So2SubIndex();
        so2SubIndex.setEast(8);
        so2SubIndex.setCentral(12);
        so2SubIndex.setSouth(12);
        so2SubIndex.setNorth(14);
        so2SubIndex.setWest(20);
        so2SubIndex.setNational(20);
        readings.setSo2SubIndex(so2SubIndex);

        CoEightHourMax coEightHourMax = new CoEightHourMax();
        coEightHourMax.setEast(0.4);
        coEightHourMax.setCentral(0.48);
        coEightHourMax.setSouth(0.24);
        coEightHourMax.setNorth(0.43);
        coEightHourMax.setWest(0.42);
        coEightHourMax.setNational(0.48);
        readings.setCoEightHourMax(coEightHourMax);

        No2OneHourMax no2OneHourMax = new No2OneHourMax();
        no2OneHourMax.setEast(14);
        no2OneHourMax.setCentral(37);
        no2OneHourMax.setSouth(37);
        no2OneHourMax.setNorth(30);
        no2OneHourMax.setWest(35);
        no2OneHourMax.setNational(37);
        readings.setNo2OneHourMax(no2OneHourMax);

        So2TwentyFourHourly so2TwentyFourHourly = new So2TwentyFourHourly();
        so2TwentyFourHourly.setEast(13);
        so2TwentyFourHourly.setCentral(19);
        so2TwentyFourHourly.setSouth(20);
        so2TwentyFourHourly.setNorth(23);
        so2TwentyFourHourly.setWest(32);
        so2TwentyFourHourly.setNational(32);
        readings.setSo2TwentyFourHourly(so2TwentyFourHourly);

        Pm25SubIndex pm25SubIndex = new Pm25SubIndex();
        pm25SubIndex.setEast(34);
        pm25SubIndex.setCentral(25);
        pm25SubIndex.setSouth(36);
        pm25SubIndex.setNorth(43);
        pm25SubIndex.setWest(30);
        pm25SubIndex.setNational(43);
        readings.setPm25SubIndex(pm25SubIndex);

        PsiTwentyFourHourly psiTwentyFourHourly = new PsiTwentyFourHourly();
        psiTwentyFourHourly.setEast(34);
        psiTwentyFourHourly.setCentral(25);
        psiTwentyFourHourly.setSouth(36);
        psiTwentyFourHourly.setNorth(43);
        psiTwentyFourHourly.setWest(30);
        psiTwentyFourHourly.setNational(43);
        readings.setPsiTwentyFourHourly(psiTwentyFourHourly);

        O3EightHourMax o3EightHourMax = new O3EightHourMax();
        o3EightHourMax.setEast(6);
        o3EightHourMax.setCentral(10);
        o3EightHourMax.setSouth(8);
        o3EightHourMax.setNorth(13);
        o3EightHourMax.setWest(9);
        o3EightHourMax.setNational(13);
        readings.setO3EightHourMax(o3EightHourMax);
        return delegate.returningResponse(psiByDatesResponse).psiByDates("2017-06-12", ImdGlobalPSIConst.API_KEY);
    }

    @Override
    public Call<PsiByDates.Response> psiByDateTimes(@Query("date_time") String date, @Header("api-key") String apikey) {
        PsiByDates.Response psiByDatesResponse = new PsiByDates.Response();

        ArrayList<RegionMetadatum> regionMetadata = new ArrayList<>();
        RegionMetadatum regionMetadatum = new RegionMetadatum();
        LabelLocation labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.94);
        regionMetadatum.setName("east");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("central");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.29587);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("south");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.41803);
        labelLocation.setLongitude(103.82);
        regionMetadatum.setName("north");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(1.35735);
        labelLocation.setLongitude(103.7);
        regionMetadatum.setName("west");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);

        regionMetadatum = new RegionMetadatum();
        labelLocation = new LabelLocation();
        labelLocation.setLatitude(0);
        labelLocation.setLongitude(0);
        regionMetadatum.setName("national");
        regionMetadatum.setLabelLocation(labelLocation);
        regionMetadata.add(regionMetadatum);


        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setTimestamp("2017-06-12T08:00:00+08:00");
        item.setUpdateTimestamp("2017-06-12T08:06:18+08:00");

        Readings readings = new Readings();
        O3SubIndex o3SubIndex = new O3SubIndex();
        o3SubIndex.setEast(3);
        o3SubIndex.setCentral(4);
        o3SubIndex.setSouth(3);
        o3SubIndex.setNorth(5);
        o3SubIndex.setWest(4);
        o3SubIndex.setNational(5);
        readings.setO3SubIndex(o3SubIndex);

        Pm10TwentyFourHourly pm10TwentyFourHourly = new Pm10TwentyFourHourly();
        pm10TwentyFourHourly.setEast(14);
        pm10TwentyFourHourly.setCentral(12);
        pm10TwentyFourHourly.setSouth(21);
        pm10TwentyFourHourly.setNorth(18);
        pm10TwentyFourHourly.setWest(17);
        pm10TwentyFourHourly.setNational(21);
        readings.setPm10TwentyFourHourly(pm10TwentyFourHourly);

        Pm10SubIndex pm10SubIndex = new Pm10SubIndex();
        pm10SubIndex.setEast(14);
        pm10SubIndex.setCentral(12);
        pm10SubIndex.setSouth(21);
        pm10SubIndex.setNorth(18);
        pm10SubIndex.setWest(17);
        pm10SubIndex.setNational(21);
        readings.setPm10SubIndex(pm10SubIndex);

        CoSubIndex coSubIndex = new CoSubIndex();
        coSubIndex.setEast(4);
        coSubIndex.setCentral(5);
        coSubIndex.setSouth(2);
        coSubIndex.setNorth(4);
        coSubIndex.setWest(4);
        coSubIndex.setNational(5);
        readings.setCoSubIndex(coSubIndex);

        Pm25TwentyFourHourly pm25TwentyFourHourly = new Pm25TwentyFourHourly();
        pm25TwentyFourHourly.setEast(8);
        pm25TwentyFourHourly.setCentral(6);
        pm25TwentyFourHourly.setSouth(9);
        pm25TwentyFourHourly.setNorth(10);
        pm25TwentyFourHourly.setWest(7);
        pm25TwentyFourHourly.setNational(10);
        readings.setPm25TwentyFourHourly(pm25TwentyFourHourly);

        So2SubIndex so2SubIndex = new So2SubIndex();
        so2SubIndex.setEast(8);
        so2SubIndex.setCentral(12);
        so2SubIndex.setSouth(12);
        so2SubIndex.setNorth(14);
        so2SubIndex.setWest(20);
        so2SubIndex.setNational(20);
        readings.setSo2SubIndex(so2SubIndex);

        CoEightHourMax coEightHourMax = new CoEightHourMax();
        coEightHourMax.setEast(0.4);
        coEightHourMax.setCentral(0.48);
        coEightHourMax.setSouth(0.24);
        coEightHourMax.setNorth(0.43);
        coEightHourMax.setWest(0.42);
        coEightHourMax.setNational(0.48);
        readings.setCoEightHourMax(coEightHourMax);

        No2OneHourMax no2OneHourMax = new No2OneHourMax();
        no2OneHourMax.setEast(14);
        no2OneHourMax.setCentral(37);
        no2OneHourMax.setSouth(37);
        no2OneHourMax.setNorth(30);
        no2OneHourMax.setWest(35);
        no2OneHourMax.setNational(37);
        readings.setNo2OneHourMax(no2OneHourMax);

        So2TwentyFourHourly so2TwentyFourHourly = new So2TwentyFourHourly();
        so2TwentyFourHourly.setEast(13);
        so2TwentyFourHourly.setCentral(19);
        so2TwentyFourHourly.setSouth(20);
        so2TwentyFourHourly.setNorth(23);
        so2TwentyFourHourly.setWest(32);
        so2TwentyFourHourly.setNational(32);
        readings.setSo2TwentyFourHourly(so2TwentyFourHourly);

        Pm25SubIndex pm25SubIndex = new Pm25SubIndex();
        pm25SubIndex.setEast(34);
        pm25SubIndex.setCentral(25);
        pm25SubIndex.setSouth(36);
        pm25SubIndex.setNorth(43);
        pm25SubIndex.setWest(30);
        pm25SubIndex.setNational(43);
        readings.setPm25SubIndex(pm25SubIndex);

        PsiTwentyFourHourly psiTwentyFourHourly = new PsiTwentyFourHourly();
        psiTwentyFourHourly.setEast(34);
        psiTwentyFourHourly.setCentral(25);
        psiTwentyFourHourly.setSouth(36);
        psiTwentyFourHourly.setNorth(43);
        psiTwentyFourHourly.setWest(30);
        psiTwentyFourHourly.setNational(43);
        readings.setPsiTwentyFourHourly(psiTwentyFourHourly);

        O3EightHourMax o3EightHourMax = new O3EightHourMax();
        o3EightHourMax.setEast(6);
        o3EightHourMax.setCentral(10);
        o3EightHourMax.setSouth(8);
        o3EightHourMax.setNorth(13);
        o3EightHourMax.setWest(9);
        o3EightHourMax.setNational(13);
        readings.setO3EightHourMax(o3EightHourMax);
        return delegate.returningResponse(psiByDatesResponse).psiByDateTimes("2017-06-12T08:00:00", ImdGlobalPSIConst.API_KEY);
    }
}