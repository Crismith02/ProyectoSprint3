package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.music.Adaptadores.ProductoAdaptadores3;
import com.example.music.Entidades.Producto3;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ListView listViewProductos3;
    ProductoAdaptadores3 productoAdaptadores3;
    ArrayList<Producto3> arrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listViewProductos3 = (ListView) findViewById(R.id.listViewProductos3);
        arrayProductos = new ArrayList<>();

        Producto3 producto1 = new Producto3(R.drawable.piano,"Producto 1", "Descripcion 1", 1000);
        Producto3 producto2 = new Producto3(R.drawable.piano31,"Producto 2", "Descripcion 2", 2000);
        Producto3 producto3 = new Producto3(R.drawable.piano32,"Producto 3", "Descripcion 3", 3000);
        Producto3 producto4 = new Producto3(R.drawable.piano,"Producto 4", "Descripcion 4", 4000);
        Producto3 producto5 = new Producto3(R.drawable.piano31,"Producto 5", "Descripcion 5", 5000);
        Producto3 producto6 = new Producto3(R.drawable.piano32,"Producto 6", "Descripcion 6", 6000);
        Producto3 producto7 = new Producto3(R.drawable.piano,"Producto 7", "Descripcion 7", 7000);
        Producto3 producto8 = new Producto3(R.drawable.piano31,"Producto 8", "Descripcion 8", 8000);
        Producto3 producto9 = new Producto3(R.drawable.piano32,"Producto 9", "Descripcion 9", 9000);

        arrayProductos.add(producto1);
        arrayProductos.add(producto2);
        arrayProductos.add(producto3);
        arrayProductos.add(producto4);
        arrayProductos.add(producto5);
        arrayProductos.add(producto6);
        arrayProductos.add(producto7);
        arrayProductos.add(producto8);
        arrayProductos.add(producto9);

        productoAdaptadores3 = new ProductoAdaptadores3(this,arrayProductos);

        listViewProductos3.setAdapter(productoAdaptadores3);

    }
}