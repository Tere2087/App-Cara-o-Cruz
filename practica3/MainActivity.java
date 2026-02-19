package com.example.practica3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


/**
 * Activity principal:
 * - Permite "lanzar" una moneda y muestra "Cara" o "Cruz".
 * - Guarda cada resultado en un almacén de historial en memoria.
 * - Ofrece menú en AppBar para ver historial, limpiar historial o salir de la App.
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_main));
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
        Button btnLanzar = findViewById(R.id.btnLanzar);

        // Al pulsar "Lanzar", se calcula aleatoriamente "Cara" o "Cruz".
        btnLanzar.setOnClickListener(v -> {
            // Navega a la pantalla de lanzamiento con animación.
            // Lógica del lanzamiento se ejecuta en LanzamientoActivity.
            startActivity(new Intent(this, LanzamientoActivity.class));
        });
    }

    // método inflater del menú superior (AppBar) con sus opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Gestiona las opciones del menú.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_history) {
            startActivity(new Intent(this, HistorialActivity.class));
            return true;
        } else if (id == R.id.action_clear) {
            Historial.clear();
            tvResultado.setText(getString(R.string.placeholder_resultado));
            return true;
        } else if (id == R.id.action_exit) {
            // Finaliza el Activity actual
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
