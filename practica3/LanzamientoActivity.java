package com.example.practica3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * Actividad que simula el lanzamiento de una moneda.
 * Cumple con los requisitos de la práctica:
 * - Contiene 4 elementos de interfaz.
 * - Soporte multilenguaje (textos en strings.xml).
 * - Lógica sencilla para estudiantes DAM.
 */
public class LanzamientoActivity extends AppCompatActivity {

    private final Random random = new Random();
    private ImageView moneda;
    private TextView resultado;
    private Button btnotravez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_lanzamiento));
        setContentView(R.layout.activity_lanzamiento);

        moneda = findViewById(R.id.moneda);
        resultado = findViewById(R.id.resultado);
        btnotravez = findViewById(R.id.btnotravez);

        // Lanzamiento inicial
        startFlipAnimation();

        // Botón repetir lanzamiento
        btnotravez.setOnClickListener(v -> startFlipAnimation());
    }

    private void startFlipAnimation() {
        moneda.setImageResource(R.drawable.moneda);
        resultado.setText(getString(R.string.lanzando));//Carpeta string\xmlEspañol

        ObjectAnimator anim = ObjectAnimator.ofFloat(moneda, "rotationY", 0f, 720f);//velocidad
        anim.setDuration(1000);//duracion
        anim.setInterpolator(new LinearInterpolator());


        anim.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {}
            @Override public void onAnimationEnd(Animator animation) {
                boolean cara = random.nextBoolean();
                String resultado1 = cara ? getString(R.string.cara) : getString(R.string.cruz);

                moneda.setImageResource(cara ? R.drawable.cara : R.drawable.cruz);
                LanzamientoActivity.this.resultado.setText(getString(R.string.text_resultado_prefijo) + " " + resultado1);

                Historial.add(resultado1);
            }
            @Override public void onAnimationCancel(Animator animation)
            {

            }
            @Override public void onAnimationRepeat(Animator animation)
            {

            }
        });
        anim.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_history) {
            startActivity(new Intent(this, HistorialActivity.class));
            return true;
        } else if (id == R.id.action_clear) {
            Historial.clear();
            Toast.makeText(this, getString(R.string.menu_limpiar_historial), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
