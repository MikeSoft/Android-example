package codaltec.initics.general.actividades;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import codaltec.initics.general.R;
import codaltec.initics.general.actividades.adapters.PromocionAdapter;

public class ProductosActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    JsonObjectRequest jsArrayRequest;
    String URL = "http://moi24.org/api/index/";

    GridView GV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        setTitle("Productos");
        GV = (GridView) findViewById(R.id.list_gridv);
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                this, this
        );
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jsArrayRequest);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        if(rotation!=0){
            GV.setNumColumns(4);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i("RTA", response.toString());
        try {
            JSONArray Productos = response.getJSONArray("productos");
            Adapter adaptador = new PromocionAdapter(Productos, this);
            GV.setAdapter((ListAdapter) adaptador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}