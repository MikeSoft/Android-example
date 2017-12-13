package codaltec.initics.general.actividades;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
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

public class ProductosActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    JsonObjectRequest jsArrayRequest;
    String URL = "http://moi24.org/api/index/";
    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        setTitle("Productos");
        LV = (ListView) findViewById(R.id.lista_listview);
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                this, this
        );
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jsArrayRequest);

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
            LV.setAdapter((ListAdapter) adaptador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class PromocionAdapter extends BaseAdapter {

        LayoutInflater inflater;
        String SERVER;

        Context CTT;
        JSONArray array_promociones;


        public PromocionAdapter(JSONArray array_promociones, Context CTT) {
            this.CTT = CTT;
            this.array_promociones = array_promociones;
            inflater = (LayoutInflater) CTT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            SERVER = "http://moi24.org";
        }


        @Override
        public int getCount() {
            return array_promociones.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            try {
                if (view == null) {
                    //view = inflater.from(parent.getContext()).inflate(R.layout.item_promocion, parent, false);
                    view = inflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                }

                JSONObject jsonObject = array_promociones.getJSONObject(i);
                ((TextView) view.findViewById(android.R.id.text1)).setText(jsonObject.getString("nombre"));
                //String url_imagen = jsonObject.getString("imagen");
                //Picasso.with(CTT).load(SERVER + url_imagen).into((ImageView) view.findViewById(R.id.imagen));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return view;
        }

    }
}