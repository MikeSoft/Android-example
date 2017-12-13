package codaltec.initics.general;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements PatternLockViewListener, View.OnClickListener {

    Button btn_login,btn_register;

    SharedPreferences SP;
    SharedPreferences.Editor EDIT;

    PatternLockView mPatternLockView;
    String pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(this);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_rgsr);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);


        SP= getSharedPreferences("db_pattern", Context.MODE_PRIVATE);
        EDIT = SP.edit();

        if(SP.getString("key_pattern","").equalsIgnoreCase("")){
            //((ViewManager)btn_login.getParent()).removeView(btn_login);
            btn_login.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        this.pattern =  PatternLockUtils.patternToString(mPatternLockView, pattern);
    }

    @Override
    public void onCleared() {

    }

    @Override
    public void onClick(View v) {
        int i =v.getId();
        switch (i){
            case R.id.btn_login:
                if(SP.getString("key_pattern","").equalsIgnoreCase(this.pattern)){
                    startActivity(new Intent(this,MainActivity.class));
                    finish();
                }else{
                    Toasty.error(this, "Patron invalido.", Toast.LENGTH_SHORT, true).show();
                }
                break;
            case R.id.btn_rgsr:
                EDIT.putString("key_pattern",pattern);
                //EDIT.remove("key_pattern");
                EDIT.apply();
                btn_login.setVisibility(View.VISIBLE);
                mPatternLockView.clearPattern();
                break;
        }

    }
}
