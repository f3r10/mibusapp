package ec.com.tnb.mibus.ui.listpoi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ec.com.tnb.mibus.R;
import ec.com.tnb.mibus.data.model.busstation.BusStation;

/**
 * Created by f3r10 on 5/6/16.
 */

public class BusStationsAdapter extends RecyclerView.Adapter<BusStationsAdapter.ViewHolder> {



    private Context mContext;
    private List<BusStation> mDataSet;
    private OnItemClickListener mOnItemClickListener;


    public BusStationsAdapter(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mDataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BusStation element = mDataSet.get(position);
        String strName = element.getNameStation();
        String strLatitude = element.getLoc().getCoordinates().get(0).getNumber().toString();
        String strLongitude = element.getLoc().getCoordinates().get(1).getNumber().toString();
        holder.txtNameBusStation.setText(strName);
        holder.txtLatitudeBusStation.setText(strLatitude);
        holder.txtLongitudeBusStation.setText(strLongitude);
        holder.setOnItemClickListener(element, mOnItemClickListener);
    }

    public void add(BusStation busStation){
        mDataSet.add(0, busStation);
        notifyDataSetChanged();
    }

    public void clear(){
        mDataSet.clear();
        notifyDataSetChanged();
    }

    public void setBusStations(List<BusStation> busStations){
        mDataSet = busStations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtNameBusStation)
        TextView txtNameBusStation;
        @BindView(R.id.txtLatitudeBusStation)
        TextView txtLatitudeBusStation;
        @BindView(R.id.txtLongitudeBusStation)
        TextView txtLongitudeBusStation;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final BusStation busStation, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(busStation);
                }
            } );
        }
    }
}
