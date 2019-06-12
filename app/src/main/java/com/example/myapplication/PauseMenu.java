package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PauseMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause_menu);

        Button button = (Button) findViewById(R.id.buttonStop);
        ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.layoutMain);

Database db=new Database(getApplicationContext());
      /* Level oyunBilgileri= db.getirOyunBilgileri();
       TextView txtlvl=(TextView)findViewById(R.id.txtLvl);
       txtlvl.setText(oyunBilgileri.getLevel());*/



        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),GameActivity.class);


                startActivity(i);
            }
        });
    }
}
