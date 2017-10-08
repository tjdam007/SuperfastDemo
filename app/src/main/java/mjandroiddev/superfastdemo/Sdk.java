package mjandroiddev.superfastdemo;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by manoj.rawal on 07-Oct-17.
 */

class Sdk {
    private static final String NAME = "name";
    private static final String CITY_ID = "city_id";
    private static final String DATA = "data";
    public static final String TAG = "Sdk";

    public static void getCities(final Context context, int max, int skip) {

        final ProgressDialog progressDialog = ProgressDialog.getInstance(context).show();
        String url = String.format(Locale.getDefault(), "https://superfastdemo-c7ff.restdb.io/rest/city?max=%d&totals=true&skip=%d", max, skip);
        Log.d(TAG, "getCities: " + url);
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.d(TAG, "onResponse: " + response);
                try {
                    ArrayList<City> cities = new ArrayList<>();
                    JSONArray data = response.getJSONArray(DATA);
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject jsonObject = data.getJSONObject(i);
                        City city = new City(jsonObject.getLong(CITY_ID), jsonObject.getString(NAME));
                        cities.add(city);

                        DbHelper
                                .getInstance(context)
                                .insertIntoCity(city);
                    }
                    //Calculation for has more data.
                    boolean hasMore = false;
                    if (response.has("totals")) {
                        JSONObject totalObj = response.getJSONObject("totals");
                        int total = totalObj.getInt("total");
                        int skip = totalObj.getInt("skip");
                        if (total - skip > 0) {
                            hasMore = true;
                        }
                    }
                    EventBus.getDefault().post(new CityEvent(cities, hasMore));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("x-apikey", "59d8d62216d89bb7783291d2");
                return hashMap;
            }
        };

        AppLoader.addRequest(request);
    }
}
