package com.track.dastar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private List<Item> itemsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    String TAG = "DASTAR";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ItemAdapter(itemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        getItems();
    }


    private void getItems()
    {
        final ProgressDialog mProgressDialog;
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage("Loading Notifications!");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RequestQueue mRequestQueue = null;
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        mRequestQueue.getCache().clear();

        itemsList.clear();

        StringRequest postRequest = new StringRequest(Request.Method.GET,
                                                      "http://fast-mesa-56712.herokuapp.com/get-all-users" ,

                                                      new Response.Listener<String>() {
                                                          @Override
                                                          public void onResponse(String response) {

                                                              Log.d(TAG , "getSimpleSearchStats ressponse: " + response);

                                                              try
                                                              {
                                                               //   JSONObject jsonObj = new JSONObject(response);
                                                                  JSONArray dataArray = new JSONArray(response);

                                                                  if(dataArray.length() !=0)
                                                                  {
                                                                      for (int i = 0; i < dataArray.length(); i++)
                                                                      {
                                                                          JSONObject record = dataArray.getJSONObject(i);
                                                                          String id = record.optString("_id");
                                                                          JSONArray foodarray = record.getJSONArray("foodItems");

                                                                          for(int j=0; j< foodarray.length(); j++)
                                                                          {
                                                                              JSONObject fooditm = foodarray.getJSONObject(j);
                                                                              String pickuphigh = fooditm.opt("pickupTimeHigh").toString();
                                                                              String pickuplow = fooditm.opt("pickupTimeLow").toString();
                                                                              String address = fooditm.opt("address").toString();
                                                                              String description = fooditm.opt("description").toString();
                                                                              String quantity = fooditm.opt("quantity").toString();
                                                                              String name = fooditm.opt("name").toString();

                                                                              Log.i(TAG , "fooditm: " + fooditm.get("name"));

                                                                                Item item = new Item(pickuphigh , pickuplow , address ,description ,quantity, name );
                                                                                    itemsList.add(item);
                                                                          }

                                                                          Log.v(TAG , "id: " + id);

                                                                          mAdapter.notifyDataSetChanged();

                                                                      }
                                                                  }

                                                              }
                                                              catch (JSONException e)
                                                              {

                                                              }

                                                              mProgressDialog.dismiss();
                                                              //   dismissProgressDialog(mProgressDialog);
                                                          }
                                                      }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e(TAG , "onErrorResponse: "+ error.toString());

                mProgressDialog.dismiss();

                //    dismissProgressDialog(mProgressDialog);
            }

        }) {

        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(postRequest);
    }
    }
