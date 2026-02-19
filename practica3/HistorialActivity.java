package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HistorialActivity extends AppCompatActivity {

    private ArrayAdapter<String> adaptador;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.title_history));
        setContentView(R.layout.historial_activity);

        lista = findViewById(R.id.Historial);

        adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Historial.get()
        );
        lista.setAdapter(adaptador);

        registerForContextMenu(lista);
    }

    // Menú que borra un elemento
    @Override
    public void onCreateContextMenu(ContextMenu menu, android.view.View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(getString(R.string.title_history));
        menu.add(0, v.getId(), 0, getString(R.string.menu_borrar_item));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.menu_borrar_item))) {
            Toast.makeText(this, "Elemento borrado", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
