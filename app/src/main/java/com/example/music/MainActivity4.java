package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.music.Adaptadores.ProductoAdaptadores3;
import com.example.music.DB.DBHelper;
import com.example.music.Entidades.Producto3;
import com.example.music.Service.ProductService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;
    private ListView listViewProductos3;
    private ProductoAdaptadores3 productoAdaptadores3;
    private ArrayList<Producto3> arrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        arrayProductos = new ArrayList<>();
        try {
            dbHelper = new DBHelper(this);
            productService = new ProductService();
            arrayProductos = productService.cursorToArray(dbHelper.getProducts());
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        listViewProductos3 = (ListView) findViewById(R.id.listViewProductos3);
        productoAdaptadores3 = new ProductoAdaptadores3(this,arrayProductos);
        listViewProductos3.setAdapter(productoAdaptadores3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionAgregar:
                Intent intent = new Intent(getApplicationContext(), FormProduct.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}