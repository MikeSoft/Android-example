package codaltec.initics.general.actividades.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import codaltec.initics.general.R;

/**
 * Created by mike on 13/12/17.
 */

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

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        try {
            if (view == null) {
                //view = inflater.from(parent.getContext()).inflate(R.layout.item_promocion, parent, false);
                view = inflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
            }
            TextView nombre,precio;
            ImageView imagen;
            nombre = (TextView)view.findViewById(R.id.txt_nombre);
            precio = (TextView)view.findViewById(R.id.txt_price);
            imagen = (ImageView)view.findViewById(R.id.img_prod);

            JSONObject jsonObject = array_promociones.getJSONObject(i);
            nombre.setText(jsonObject.getString("nombre"));
            precio.setText(jsonObject.getString("precio"));
            String url_imagen = jsonObject.getString("imagen");
            Picasso.with(CTT).load(SERVER + url_imagen).into(imagen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}