package com.imdglobal.psi.views.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.imdglobal.psi.R;
import com.imdglobal.psi.api.ImdGlobalPSILocalData;
import com.imdglobal.psi.api.entities.PsiByDate;
import com.imdglobal.psi.api.entities.RegionMetadatum;
import com.imdglobal.psi.views.adapters.PopupAdapter;

/**
 * Created by rizkyriadhy on 20/06/17.
 */

public class MapPsiFragment extends Fragment implements OnMapReadyCallback {
    private SupportMapFragment mapFragment;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.f_map_location);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        PsiByDate psiByDate = ImdGlobalPSILocalData.getPsiDateTime();

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getContext(), R.raw.style_json));

            if (!success) {
                Log.e("Map", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Map", "Can't find style. Error: ", e);
        }
        map = googleMap;
        for(RegionMetadatum d : psiByDate.getRegionMetadata())
            if (d.getName() != null && !d.getName().equals("region")) {
                boolean isCentral = false;
                LatLng destination = new LatLng(d.getLabelLocation().getLatitude(), d.getLabelLocation().getLongitude());

                if (d.getName().equals("central")) {
                    isCentral = true;
                    String snippet = "";
                    if (psiByDate.getItems() != null) {
                        snippet = snippet + "o3_sub_index : " + psiByDate.getItems().get(0).getReadings().getO3SubIndex().getCentral() + "\n";
                        snippet = snippet + "pm10_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm10TwentyFourHourly().getCentral() + "\n";
                        snippet = snippet + "pm10_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm10SubIndex().getCentral() + "\n";
                        snippet = snippet + "co_sub_index : " + psiByDate.getItems().get(0).getReadings().getCoSubIndex().getCentral() + "\n";
                        snippet = snippet + "pm25_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm25TwentyFourHourly().getCentral() + "\n";
                        snippet = snippet + "so2_sub_index : " + psiByDate.getItems().get(0).getReadings().getSo2SubIndex().getCentral() + "\n";
                        snippet = snippet + "co_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getCoEightHourMax().getCentral() + "\n";
                        snippet = snippet + "no2_one_hour_max : " + psiByDate.getItems().get(0).getReadings().getNo2OneHourMax().getCentral() + "\n";
                        snippet = snippet + "so2_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getSo2TwentyFourHourly().getCentral() + "\n";
                        snippet = snippet + "pm25_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm25SubIndex().getCentral() + "\n";
                        snippet = snippet + "psi_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPsiTwentyFourHourly().getCentral() + "\n";
                        snippet = snippet + "o3_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getO3EightHourMax().getCentral() + "\n";
                    }
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .position(destination)
                            .title("Singapore "+d.getName()+", readings :")
                            .snippet(snippet);
                    map.addMarker(destinationMarker);

                } else if (d.getName().equals("west")) {
                    String snippet = "";
                    if (psiByDate.getItems() != null) {
                        snippet = snippet + "o3_sub_index : " + psiByDate.getItems().get(0).getReadings().getO3SubIndex().getWest() + "\n";
                        snippet = snippet + "pm10_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm10TwentyFourHourly().getWest() + "\n";
                        snippet = snippet + "pm10_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm10SubIndex().getWest() + "\n";
                        snippet = snippet + "co_sub_index : " + psiByDate.getItems().get(0).getReadings().getCoSubIndex().getWest() + "\n";
                        snippet = snippet + "pm25_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm25TwentyFourHourly().getWest() + "\n";
                        snippet = snippet + "so2_sub_index : " + psiByDate.getItems().get(0).getReadings().getSo2SubIndex().getWest() + "\n";
                        snippet = snippet + "co_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getCoEightHourMax().getWest() + "\n";
                        snippet = snippet + "no2_one_hour_max : " + psiByDate.getItems().get(0).getReadings().getNo2OneHourMax().getWest() + "\n";
                        snippet = snippet + "so2_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getSo2TwentyFourHourly().getWest() + "\n";
                        snippet = snippet + "pm25_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm25SubIndex().getWest() + "\n";
                        snippet = snippet + "psi_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPsiTwentyFourHourly().getWest() + "\n";
                        snippet = snippet + "o3_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getO3EightHourMax().getWest() + "\n";
                    }
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .position(destination)
                            .title("Singapore "+d.getName()+", readings :")
                            .snippet(snippet);
                    map.addMarker(destinationMarker);

                } else if (d.getName().equals("north")) {

                    String snippet = "";
                    if (psiByDate.getItems() != null) {
                        snippet = snippet + "o3_sub_index : " + psiByDate.getItems().get(0).getReadings().getO3SubIndex().getNorth() + "\n";
                        snippet = snippet + "pm10_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm10TwentyFourHourly().getNorth() + "\n";
                        snippet = snippet + "pm10_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm10SubIndex().getNorth() + "\n";
                        snippet = snippet + "co_sub_index : " + psiByDate.getItems().get(0).getReadings().getCoSubIndex().getNorth() + "\n";
                        snippet = snippet + "pm25_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm25TwentyFourHourly().getNorth() + "\n";
                        snippet = snippet + "so2_sub_index : " + psiByDate.getItems().get(0).getReadings().getSo2SubIndex().getNorth() + "\n";
                        snippet = snippet + "co_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getCoEightHourMax().getNorth() + "\n";
                        snippet = snippet + "no2_one_hour_max : " + psiByDate.getItems().get(0).getReadings().getNo2OneHourMax().getNorth() + "\n";
                        snippet = snippet + "so2_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getSo2TwentyFourHourly().getNorth() + "\n";
                        snippet = snippet + "pm25_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm25SubIndex().getNorth() + "\n";
                        snippet = snippet + "psi_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPsiTwentyFourHourly().getNorth() + "\n";
                        snippet = snippet + "o3_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getO3EightHourMax().getNorth() + "\n";
                    }
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .position(destination)
                            .title("Singapore "+d.getName()+", readings :")
                            .snippet(snippet);
                    map.addMarker(destinationMarker);
                } else if (d.getName().equals("east")) {

                    String snippet = "";
                    if (psiByDate.getItems() != null) {
                        snippet = snippet + "o3_sub_index : " + psiByDate.getItems().get(0).getReadings().getO3SubIndex().getEast() + "\n";
                        snippet = snippet + "pm10_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm10TwentyFourHourly().getEast() + "\n";
                        snippet = snippet + "pm10_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm10SubIndex().getEast() + "\n";
                        snippet = snippet + "co_sub_index : " + psiByDate.getItems().get(0).getReadings().getCoSubIndex().getEast() + "\n";
                        snippet = snippet + "pm25_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm25TwentyFourHourly().getEast() + "\n";
                        snippet = snippet + "so2_sub_index : " + psiByDate.getItems().get(0).getReadings().getSo2SubIndex().getEast() + "\n";
                        snippet = snippet + "co_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getCoEightHourMax().getEast() + "\n";
                        snippet = snippet + "no2_one_hour_max : " + psiByDate.getItems().get(0).getReadings().getNo2OneHourMax().getEast() + "\n";
                        snippet = snippet + "so2_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getSo2TwentyFourHourly().getEast() + "\n";
                        snippet = snippet + "pm25_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm25SubIndex().getEast() + "\n";
                        snippet = snippet + "psi_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPsiTwentyFourHourly().getEast() + "\n";
                        snippet = snippet + "o3_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getO3EightHourMax().getEast() + "\n";
                    }
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .position(destination)
                            .title("Singapore "+d.getName()+", readings :")
                            .snippet(snippet);
                    map.addMarker(destinationMarker);

                } else if (d.getName().equals("south")) {

                    String snippet = "";
                    if (psiByDate.getItems() != null) {
                        snippet = snippet + "o3_sub_index : " + psiByDate.getItems().get(0).getReadings().getO3SubIndex().getSouth() + "\n";
                        snippet = snippet + "pm10_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm10TwentyFourHourly().getSouth() + "\n";
                        snippet = snippet + "pm10_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm10SubIndex().getSouth() + "\n";
                        snippet = snippet + "co_sub_index : " + psiByDate.getItems().get(0).getReadings().getCoSubIndex().getSouth() + "\n";
                        snippet = snippet + "pm25_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPm25TwentyFourHourly().getSouth() + "\n";
                        snippet = snippet + "so2_sub_index : " + psiByDate.getItems().get(0).getReadings().getSo2SubIndex().getSouth() + "\n";
                        snippet = snippet + "co_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getCoEightHourMax().getSouth() + "\n";
                        snippet = snippet + "no2_one_hour_max : " + psiByDate.getItems().get(0).getReadings().getNo2OneHourMax().getSouth() + "\n";
                        snippet = snippet + "so2_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getSo2TwentyFourHourly().getSouth() + "\n";
                        snippet = snippet + "pm25_sub_index : " + psiByDate.getItems().get(0).getReadings().getPm25SubIndex().getSouth() + "\n";
                        snippet = snippet + "psi_twenty_four_hourly : " + psiByDate.getItems().get(0).getReadings().getPsiTwentyFourHourly().getSouth() + "\n";
                        snippet = snippet + "o3_eight_hour_max : " + psiByDate.getItems().get(0).getReadings().getO3EightHourMax().getSouth() + "\n";
                    }
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .position(destination)
                            .title("Singapore "+d.getName()+", readings :")
                            .snippet(snippet);
                    map.addMarker(destinationMarker);

                }
                map.setInfoWindowAdapter(new PopupAdapter(getActivity().getLayoutInflater()));

                if (isCentral)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 10.0f), 5000, null);
            }
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Fragment fragment = (getFragmentManager().findFragmentById(R.id.f_map_location));
//        if (fragment != null){
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .remove(fragment)
//                    .commit();
//        }
//    }
}