package com.example.androidlesson1.workingWithRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.R;
import com.example.androidlesson1.workingWithFragments.ItemClickListener;

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
        holder.setData(soc.getDayOfWeek(), soc.getTemperature() + "\u00B0",
                soc.getWeatherPicture(), soc.getWind(), soc.getPressure(), soc.getHumidity(),
                soc.getDescription());
    }

    @Override
    public int getItemCount() {
        return socSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dayView;
        private TextView temperatureView;
        private ImageView weatherPictureView;
        private TextView windView;
        private TextView pressureView;
        private TextView humidityView;
        private TextView descriptionView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayView = itemView.findViewById(R.id.day_in_item);
            temperatureView = itemView.findViewById(R.id.temperature_in_item);
            weatherPictureView = itemView.findViewById(R.id.weather_picture_in_item);
            windView = itemView.findViewById(R.id.wind_in_item);
            pressureView = itemView.findViewById(R.id.pressure_in_item);
            humidityView = itemView.findViewById(R.id.humidity_in_item);
            descriptionView = itemView.findViewById(R.id.description_in_item);
            linearLayout = itemView.findViewById(R.id.linear_layout_in_item);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.notifySubscribers(dayView.getText().toString(),
                            temperatureView.getText().toString(),
                            weatherPictureView.getDrawable(),
                            windView.getText().toString(),
                            pressureView.getText().toString(),
                            humidityView.getText().toString(),
                            descriptionView.getText().toString());

                }
            });
        }

        public void setData(String day, String temperature, int weatherPicture, String wind,
                            String pressure, String humidity, String description) {
            getDayView().setText(day);
            getTemperatureView().setText(temperature);
            getWeatherPictureView().setImageResource(weatherPicture);
            getWindView().setText(wind);
            getPressureView().setText(pressure);
            getHumidityView().setText(humidity);
            getDescriptionView().setText(description);
        }


        public TextView getDayView() {
            return dayView;
        }

        public TextView getTemperatureView() {
            return temperatureView;
        }

        public ImageView getWeatherPictureView() {
            return weatherPictureView;
        }

        public TextView getWindView() {
            return windView;
        }

        public TextView getPressureView() {
            return pressureView;
        }

        public TextView getHumidityView() {
            return humidityView;
        }

        public TextView getDescriptionView() {
            return descriptionView;
        }
    }

}
