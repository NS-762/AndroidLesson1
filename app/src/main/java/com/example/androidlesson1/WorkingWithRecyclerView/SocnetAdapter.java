package com.example.androidlesson1.WorkingWithRecyclerView;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.MainActivity;
import com.example.androidlesson1.R;
import com.example.androidlesson1.WorkingWithFragments.Publisher;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    private SocSource socSource;
    private AdapterView.OnItemClickListener itemClickListener;
    private Publisher publisher;

    public SocnetAdapter(SocSource socSource, Publisher publisher) {
        this.socSource = socSource;
        this.publisher = publisher;
    }

    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        /*if ()*/
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

    public void SetOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
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
                    Log.d("SocnetAdapter", "НАЖАТО");
                    publisher.notifySubscriber(date.getText().toString(),
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
