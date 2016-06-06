package ec.com.tnb.mibus.ui.mapboxfullscreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;
import ec.com.tnb.mibus.R;
import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailPoiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailPoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailPoiFragment extends BaseFragment implements DetailPoiView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject DetailPoiPresenter mDetailPoiPresenter;

    private MapView mapView;
    private LatLng mLatLng;
    private Marker mMarker;
    private MapboxMap mMapboxMap;

    public DetailPoiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailPoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailPoiFragment newInstance(String param1, String param2) {
        DetailPoiFragment fragment = new DetailPoiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentComponent().inject(this);
        mDetailPoiPresenter.attachView(this);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View detailPoiView = inflater.inflate(R.layout.fragment_detail_poi, container, false);
        mapView = (MapView) detailPoiView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mMapboxMap = mapboxMap;
                CameraPosition position = new CameraPosition.Builder()
                        .target(new LatLng(-0.13709799945354462, -78.49885559082031)) // Sets the new camera position
                        .zoom(14) // Sets the zoom
                        .build(); // Creates a CameraPosition from the builder
                mMapboxMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), 7000);
                mDetailPoiPresenter.loadStations();
                // Customize map with markers, polylines, etc.
            }
        });
        return detailPoiView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDetailPoiPresenter.detachView();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mDetailPoiPresenter.detachView();

    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void showBusStationsNear(List<BusStation> stations) {
        Timber.d("Numero de paradas: " + stations.size());
        /*for(BusStation station : stations){
            Double lat = station.loc.coordinates.get(0);
            Double lng = station.loc.coordinates.get(1);
            mLatLng = new LatLng(lat, lng);
            mMapboxMap.addMarker(new MarkerOptions()
                    .position(mLatLng)
                    .snippet(station.stopUuid)
                    .title(station.nameStation));


        }*/
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
