package es.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_splash);

// Activity splash para que sea la actividad principal al iniciarse la app. (se configura en  androidmanifest)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia la actividad principal
                Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000); // aqui se define el tiempo de duración de la pantalla de inicio
    }
}

