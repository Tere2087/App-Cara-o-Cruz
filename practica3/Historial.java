package com.example.practica3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Esta clase actúa como un "almacén" en memoria.
 * - Mantiene una lista de los resultados de lanzamientos en esta ejecución.
 * - No persiste en disco
 */
public final class Historial {

    private static final ArrayList<String> HISTORIAL = new ArrayList<>();

    private static final SimpleDateFormat FORMATO =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

    private Historial() { }

    public static ArrayList<String> get() {
        return HISTORIAL;
    }

    // Inserta una línea con marca temporal y resultado ("Cara" o "Cruz")
    public static void add(String resultado) {
        String ts = FORMATO.format(new Date());
        HISTORIAL.add(ts + "  ·  " + resultado);
    }

    // Elimina todo el historial
    public static void clear() {
        HISTORIAL.clear();
    }
}

