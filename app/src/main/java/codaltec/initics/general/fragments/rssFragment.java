package codaltec.initics.general.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import codaltec.initics.general.R;
import codaltec.initics.general.actividades.ArticuloActivity;
import codaltec.initics.general.utils.RSS.ParseRSS;
import codaltec.initics.general.utils.RSS.RssFeedModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class rssFragment extends Fragment implements Response.ErrorListener, Response.Listener<String>, AdapterView.OnItemClickListener {

    public rssFragment() {

    }

    ListView listado;
    StringRequest stringRequest;
    String RSS = "http://www.unillanos.edu.co/index.php?option=com_content&view=category&id=3&Itemid=16&format=feed&type=rss";
    //String RSS = "http://www.eltiempo.com/rss/opinion.xml";
    List<RssFeedModel> listadoRSS;
    ProgressBar PB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V =inflater.inflate(R.layout.fragment_rss, container, false);
        listado = (ListView)V.findViewById(R.id.listado);
        listado.setVisibility(View.INVISIBLE);

        PB=(ProgressBar)V.findViewById(R.id.progressBar2);

        listado.setOnItemClickListener(this);

        return V;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RssFeedModel articulo = listadoRSS.get(i);
        Intent I = new Intent(getActivity(), ArticuloActivity.class);
        I.putExtra("titulo",articulo.getTitle());
        I.putExtra("fecha",articulo.getDate());
        I.putExtra("link",articulo.getLink());
        I.putExtra("descripcion",articulo.getDescription());
        startActivity(I);
    }

    @Override
    public void onResume() {
        super.onResume();

        stringRequest = new StringRequest(Request.Method.GET, RSS,
                this,this);

        RequestQueue rq = Volley.newRequestQueue(getContext());
        rq.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(String response) {
        //Log.i("RtaRss",response);
        try {
            listadoRSS = ParseRSS.ParseFeed(response);

            Adapter adapter=new noticiasAdapter();
            listado.setAdapter((ListAdapter) adapter);
            listado.setVisibility(View.VISIBLE);
            PB.setVisibility(View.INVISIBLE);



        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    class noticiasAdapter extends BaseAdapter{
        LayoutInflater inflater;
        public noticiasAdapter(){
            inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return listadoRSS.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                //view = inflater.from(parent.getContext()).inflate(R.layout.item_promocion, parent, false);
                view = inflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, viewGroup, false);
            }
            String titulo = listadoRSS.get(i).getTitle();

            ((TextView)view.findViewById(android.R.id.text1)).setText(titulo);
            ((TextView)view.findViewById(android.R.id.text2)).setText(listadoRSS.get(i).getDate());

            return view;
        }
    }

}
