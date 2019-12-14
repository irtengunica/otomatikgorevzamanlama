package com.example.okul.otomatikgorevzamanlama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button butonsurekli,butondur;
    TextView zamantext;
    //nesneleri koda tanýtma

    Timer ZamanAl;
    GorevZamanlamaSinifi GorevZamanlayici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butonsurekli=(Button) findViewById(R.id.buttonsurekli);
        butondur=(Button) findViewById(R.id.buttondur);
        zamantext=(TextView) findViewById(R.id.zamantext);
        //xml ile neneleri baðlama
        if(ZamanAl != null){
            ZamanAl.cancel();
        }
        ZamanAl=new Timer();
        GorevZamanlayici=new GorevZamanlamaSinifi();
        //sýnýfý çaðýr


        butonsurekli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ZamanAl.schedule(GorevZamanlayici,1000,5000);
                //her 5 saniyede bir görevi tekrarla
            }
        });

        butondur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ZamanAl!=null){
                    ZamanAl.cancel();
                    ZamanAl = null;
                    //iþlemi durdur
                }
            }
        });
    }
    //zamanlama için sýnýf üretimi
    class GorevZamanlamaSinifi extends TimerTask {

        @Override
        public void run() {
            Calendar takvim = Calendar.getInstance();
            SimpleDateFormat BasTarihAl =
                    new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
            final String TarihStringi = BasTarihAl.format(takvim.getTime());

            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    zamantext.setText(TarihStringi);
                }});
        }

    }
}
