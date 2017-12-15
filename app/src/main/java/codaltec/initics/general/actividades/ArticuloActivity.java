package codaltec.initics.general.actividades;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import codaltec.initics.general.R;
import es.dmoral.toasty.Toasty;

public class ArticuloActivity extends AppCompatActivity {
    WebView WV;
    TextView TV;
    String titulo,fecha,descripcion,link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        WV=(WebView)findViewById(R.id.web_articulo);
        TV=(TextView) findViewById(R.id.titulo_articulo);

        link = getIntent().getStringExtra("link");
        titulo =getIntent().getStringExtra("titulo");
        fecha = getIntent().getStringExtra("fecha");
        descripcion =getIntent().getStringExtra("descripcion");
        WV.loadData(descripcion,"text/html","UTF-8");

        TV.setText(titulo);
        setTitle(titulo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_articulo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.ai_link:
                Intent I = new Intent(Intent.ACTION_VIEW);
                I.setData(Uri.parse(link));
                startActivity(I);
                break;
            case R.id.ai_date:
                Toasty.info(this,"Fecha:"+fecha, Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
