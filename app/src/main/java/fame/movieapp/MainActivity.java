package fame.movieapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    public static final String urlx="https://api.themoviedb.org/3/trending/movie/week?api_key=c2ced2f6edbb103b8a097ca8a8ea0576";

    RecyclerView rvx;


    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvx=findViewById(R.id.rv_mov);

        pg=new ProgressDialog(this);
        pg.setMessage(" Loading");
        pg.show();


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(urlx, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e("ff", "FfF}}}}}}}}}}}}}}}] "+jsonObject.toString() );
                JSONObject jsox=jsonObject;
                ArrayList<Model> movarr=new ArrayList<>();
                try {
                    JSONArray insarr=jsox.getJSONArray("results");



                    for(int i=0;i<insarr.length();i++){
                        JSONObject jsonObject1=insarr.getJSONObject(i);
                        movarr.add(new Model(jsonObject1.getString("title"),jsonObject1.getString("overview"),jsonObject1.getString("poster_path"),jsonObject1.getString("vote_average")));
                    }


                    rvx.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    MovieAdapter insightsAdapter=new MovieAdapter(movarr,MainActivity.this);
                    rvx.setAdapter(insightsAdapter);

                    pg.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pg.dismiss();
                Log.e("F", ")))))))))))))))))$$$$$$$$$$$$    "+volleyError.toString() );
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                return headers;
            }
        };

        requestQueue.add(request);

    }
}
