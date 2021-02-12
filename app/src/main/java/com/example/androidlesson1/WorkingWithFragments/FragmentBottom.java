package com.example.androidlesson1.WorkingWithFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlesson1.R;
import com.example.androidlesson1.WorkingWithRecyclerView.SocSource;
import com.example.androidlesson1.WorkingWithRecyclerView.SocnetAdapter;

public class FragmentBottom extends Fragment {

    private String temperature;
    private String date;
    private String dayOfWeek;
    private ImageView weatherPicture;

    private TextView cityTextView;
    private TextView temperatureTextView;
    private TextView dayOfWeekTextView;
    private TextView dateTextView;

    private Publisher publisher;

    public static FragmentBottom create() { //фабричный метод
        FragmentBottom fragmentBottom = new FragmentBottom();
        Bundle args = new Bundle();
/*        args.putString(CITY, city);
        args.putString(temperature, temperature);
        args.putString(date, date);*/

        fragmentBottom.setArguments(args);

        return fragmentBottom;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = ((PublisherGetter)context).getPublisher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        SocSource socSource = new SocSource(getResources());
        initRecyclerView(view, socSource.build()); //передать в RV socSource с заполненным списком внутри

        return view;
    }

    public void initRecyclerView(View view, SocSource socSource) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        SocnetAdapter adapter  = new SocnetAdapter(socSource, publisher);
        recyclerView.setAdapter(adapter);
    }
}