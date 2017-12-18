package codaltec.initics.general.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import codaltec.initics.general.MainActivity;
import codaltec.initics.general.R;
import codaltec.initics.general.basedatos.BaseDatos;


public class PanelRSSFragment extends Fragment {

    MainActivity MA;
    ListView listado;
    BaseDatos DB;

    public PanelRSSFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MA=(MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V=inflater.inflate(R.layout.fragment_rss, container, false);
        return V;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        DB=new BaseDatos(MA);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_panel_rss,menu);
    }
}
