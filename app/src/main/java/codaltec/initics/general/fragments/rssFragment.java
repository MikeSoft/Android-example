package codaltec.initics.general.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

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
import codaltec.initics.general.utils.RSS.ParseRSS;
import codaltec.initics.general.utils.RSS.RssFeedModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class rssFragment extends Fragment implements Response.ErrorListener, Response.Listener<String> {

    public rssFragment() {

    }

    ListView listado;
    StringRequest stringRequest;
    String RSS = "http://www.unillanos.edu.co/index.php?option=com_content&view=category&id=3&Itemid=16&format=feed&type=rss";
    List<RssFeedModel> listadoRSS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V =inflater.inflate(R.layout.fragment_rss, container, false);
        listado = (ListView)V.findViewById(R.id.listado);

        return V;
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

            for (RssFeedModel s:listadoRSS
                 ) {
                Log.i("taaaaaaa",s.getTitle());

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class noticiasAdapter extends BaseAdapter{

        public noticiasAdapter(){

        }

        @Override
        public int getCount() {
            return 0;
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
            return null;
        }
    }

}
