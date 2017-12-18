package codaltec.initics.general.fragments;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import codaltec.initics.general.MainActivity;
import codaltec.initics.general.R;
import codaltec.initics.general.basedatos.BaseDatos;
import es.dmoral.toasty.Toasty;


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


    Dialog DDD;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.add_rss){
            DDD = new Dialog(MA);
            DDD.setTitle("lalalala");
            DDD.setContentView(R.layout.menu_crear_rss);
            final EditText nombre=((EditText)DDD.findViewById(R.id.input_nombre));
            final EditText link=((EditText)DDD.findViewById(R.id.input_link));
            ((Button)DDD.findViewById(R.id.btn_save_rss)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( nombre.getText().toString().length()<2 || link.getText().toString().length()<5){
                        Toasty.error(MA,"Escribe bien los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(DB.agregarFeed(nombre.getText().toString(),link.getText().toString())){
                        Toasty.success(MA,"RSS agregado",Toast.LENGTH_SHORT).show();
                    }
                }
            });



            DDD.show();


        }

        return  true;
    }
}
