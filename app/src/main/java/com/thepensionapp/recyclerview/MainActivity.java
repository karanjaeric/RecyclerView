package com.thepensionapp.recyclerview;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private static final  String URL_DATA="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();

//        for (int i=0;i<10;i++){
//            ListItem listItem=new ListItem("heading"+(i+1),"lorem ipsum dummy data");
//            listItems.add(listItem);
//
//
//        }
      //  loadRecyclerData();
        adapter=new MyAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);




    }

    private void loadRecyclerData(){

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading appointments.....");
        progressDialog.show();

        //Make a network request
        StringRequest stringRequest=new StringRequest(
                Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("heroes");

                            for(int i=0;i>jsonArray.length();i++){
                                JSONObject o=jsonArray.getJSONObject(i);
                                ListItem listItem=new ListItem(o.getString("name"),o.getString("about"));
                                listItems.add(listItem);

                            }
                            adapter=new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }

        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
