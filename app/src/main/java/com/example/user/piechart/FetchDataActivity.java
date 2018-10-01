package com.example.user.piechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchDataActivity extends AppCompatActivity {
    String url = "https://api.myjson.com/bins/9fsqo";
    private String finalText = "";
    //private TextView textView;
    private ArrayList<PieEntry> pieEntries = new ArrayList<>();
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);

        pieChart = findViewById(R.id.piechart);
        //textView =findViewById(R.id.tv_data_http);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String strName = jsonObject.getString("name");
                                String strValue = jsonObject.getString("value");

                                String name = strName.toString();
                                float value = Float.parseFloat(strValue);
                                pieEntries.add(new PieEntry(value, name));
                                finalText = finalText+ strName + " " + strValue + "\n";
                            }
                            PieDataSet dataSet = new PieDataSet(pieEntries, "Cartoon Network");
                            dataSet.setSliceSpace(3f);
                            dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                            PieData data = new PieData(dataSet);
                            data.setValueTextSize(10f);
                            data.setValueTextColor(Color.RED);

                            pieChart.setData(data);
                            pieChart.invalidate();
                            pieChart.getDescription().setEnabled(false);
                            pieChart.setExtraOffsets(5, 10, 5, 5);
                            pieChart.setDragDecelerationFrictionCoef(0.95f);
                            pieChart.setDrawHoleEnabled(true);
                            pieChart.setTransparentCircleRadius(80f);

                            //textView.setText(finalText);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(FetchDataActivity.this);
        requestQueue.add(stringRequest);
    }
}
