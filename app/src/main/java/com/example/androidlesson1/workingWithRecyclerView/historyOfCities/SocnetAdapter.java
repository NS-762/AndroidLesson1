package com.example.androidlesson1.workingWithRecyclerView.historyOfCities;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.Constants;
import com.example.androidlesson1.R;

import java.util.ArrayList;
import java.util.List;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> implements Filterable {

    private List<String> historyOfCities;
    private List<String> historyOfCitiesFull;

    public SocnetAdapter(List<String> historyOfCities) {
        this.historyOfCities = historyOfCities;
        historyOfCitiesFull = new ArrayList<>(historyOfCities);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_of_cities_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(historyOfCities.get(position));
    }

    @Override
    public int getItemCount() {
        return historyOfCities.size();
    }

    @Override
    public Filter getFilter() {
        return historyOfCitiesFilter;
    }

    private Filter historyOfCitiesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(historyOfCitiesFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String item : historyOfCitiesFull) {
                    if (item.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            historyOfCities.clear();
            historyOfCities.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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
            getCityView().setText(city); // почему нельзя cityView().setText(city)??
        }

        public TextView getCityView() { //это зачем?
            return cityView;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), MENU_DELETE_ITEM, 0, "Delete");
        }
    }
}
