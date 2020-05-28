package com.Save.App.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.Save.App.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BarGraph#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BarGraph extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BarGraph() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BarGraph.
     */
    // TODO: Rename and change types and number of parameters
    public static BarGraph newInstance(String param1, String param2) {
        BarGraph fragment = new BarGraph();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    AnyChartView barChart;
    Cartesian bar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.bar_graph, container,false );
        barChart = (AnyChartView) layout.findViewById(R.id.bar_chart);
        bar = AnyChart.column();
        setBarChart(bar);
        barChart.setChart(bar);

        return layout;

    }

    public void setBarChart(final Cartesian bar) {

        final List<DataEntry> dataEntries= new ArrayList<>();

        dataEntries.add(new ValueDataEntry("Jan", 24));
        dataEntries.add(new ValueDataEntry("Feb", 10));
        dataEntries.add(new ValueDataEntry("Mar", 20));
        dataEntries.add(new ValueDataEntry("Apr", 10));
        dataEntries.add(new ValueDataEntry("May", 5));
        dataEntries.add(new ValueDataEntry("Jun", 5));
        dataEntries.add(new ValueDataEntry("Jul", 5));
        dataEntries.add(new ValueDataEntry("Aug", 5));
        dataEntries.add(new ValueDataEntry("Sep", 100));
        dataEntries.add(new ValueDataEntry("Oct", 5));
        dataEntries.add(new ValueDataEntry("Nov", 5));
        dataEntries.add(new ValueDataEntry("Dec", 5));


        bar.data(dataEntries);
    }
}
