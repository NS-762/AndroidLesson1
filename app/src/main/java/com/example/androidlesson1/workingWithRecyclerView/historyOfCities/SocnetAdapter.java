package com.example.androidlesson1.workingWithRecyclerView.historyOfCities;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;

import java.util.List;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    private SocSource socSource;
    private List<String> historyOfCities;

//    public SocnetAdapter(SocSource socSource) {
//        this.socSource = socSource;
//    }

    public SocnetAdapter(SocSource socSource, List<String> historyOfCities) {
        this.socSource = socSource;
        this.historyOfCities = historyOfCities;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_of_cities_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Soc soc = socSource.getSoc(position);
//        holder.setData(soc.getCity());
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Soc soc = socSource.getSoc(position);
        holder.setData(soc.getCity());
    }

    @Override
    public int getItemCount() {
        return socSource.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            Constants {

        private TextView cityView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityView = itemView.findViewById(R.id.city_in_item);
            linearLayout = itemView.findViewById(R.id.linearLayout_city_rv);

            linearLayout.setOnCreateContextMenuListener(this);
        }

        public void setData(String city) {
            getCityView().setText(city);
        }

        public TextView getCityView() {
            return cityView;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), MENU_DELETE_ITEM, 0, "Delete");
        }
    }

}
