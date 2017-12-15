package codaltec.initics.general.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import codaltec.initics.general.R;

public class ArticuloActivity extends AppCompatActivity {
    WebView WV;
    TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        WV=(WebView)findViewById(R.id.web_articulo);
        TV=(TextView) findViewById(R.id.titulo_articulo);
        String titulo,fecha,descripcion,link;
        link = getIntent().getStringExtra("link");
        titulo =getIntent().getStringExtra("titulo");
        fecha = getIntent().getStringExtra("fecha");
        descripcion =getIntent().getStringExtra("descripcion");
        WV.loadData(descripcion,"text/html","UTF-8");

        TV.setText(titulo);
        setTitle(titulo);

    }



}
