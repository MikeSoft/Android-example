package codaltec.initics.general.actividades.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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