package com.example.androidlesson1.WorkingWithRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.R;
import com.example.androidlesson1.WorkingWithFragments.ItemClickListener;
import com.google.android.material.snackbar.Snackbar;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    private SocSource socSource;
    private ItemClickListener itemClickListener;

    public SocnetAdapter(SocSource socSource, ItemClickListener itemClickListener) {
        this.socSource = socSource;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SocnetAdapter.ViewHolder holder, int position) {
        Soc soc = socSource.getSoc(position);
        holder.setData(soc.getDate(), soc.getDayOfWeek(), soc.getTemperature(), soc.getWeatherPicture());
    }

    @Override
    public int getItemCount() {
        return socSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView dayOfWeek;
        private TextView temperature;
        private ImageView weatherPicture;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_in_item);
            dayOfWeek = itemView.findViewById(R.id.day_of_week_in_item);
            temperature = itemView.findViewById(R.id.temperature_in_item);
            weatherPicture = itemView.findViewById(R.id.weather_picture_in_item);
            linearLayout = itemView.findViewById(R.id.linear_layout_in_item);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.notifySubscribers(date.getText().toString(),
                            dayOfWeek.getText().toString(),
                            temperature.getText().toString(),
                            weatherPicture.getDrawable());
                    
                }
            });

        }

        public void setData(String date, String dayOfWeek, String temperature, int weatherPicture) {
            getDate().setText(date);
            getDayOfWeek().setText(dayOfWeek);
            getTemperature().setText(temperature);
            getWeatherPicture().setImageResource(weatherPicture);

        }

        public TextView getDate() {
            return date;
        }

        public TextView getDayOfWeek() {
            return dayOfWeek;
        }

        public TextView getTemperature() {
            return temperature;
        }

        public ImageView getWeatherPicture() {
            return weatherPicture;
        }
    }

}
