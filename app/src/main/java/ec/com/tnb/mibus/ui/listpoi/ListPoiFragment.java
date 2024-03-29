package ec.com.tnb.mibus.ui.listpoi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ec.com.tnb.mibus.R;
import ec.com.tnb.mibus.data.model.busstation.BusStation;
import ec.com.tnb.mibus.ui.base.BaseFragment;
import ec.com.tnb.mibus.util.DialogFactory;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListPoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPoiFragment extends BaseFragment implements ListPoiView, OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    ListPoiPresenter mListPoiPresenter;


    private BusStationsAdapter mBusStationsAdapter;


    public ListPoiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListPoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListPoiFragment newInstance(String param1, String param2) {
        ListPoiFragment fragment = new ListPoiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getFragmentComponent().inject(this);
        mListPoiPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_poi, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        //mListPoiPresenter.loadCacheListBusStations();
        mListPoiPresenter.loadOnlineListBusStations();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initAdapter() {
        if (mBusStationsAdapter == null ){
            mBusStationsAdapter = new BusStationsAdapter(this);
        }
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mBusStationsAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListPoiPresenter.detachView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListPoiPresenter.detachView();
        mListener = null;
    }

    @Override
    public void showListBusStationNear(List<BusStation> stationList) {
        Timber.i("llegan las paradas");
        mBusStationsAdapter.setBusStations(stationList);

    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(getActivity(), getString(R.string.error_loading_ribots)).show();
    }

    @Override
    public void showBusStationsEmpty() {
        Toast.makeText(getActivity(), R.string.empty_ribots, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(BusStation busStation) {
        Toast.makeText(getActivity(), busStation.getId(), Toast.LENGTH_SHORT).show();
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
