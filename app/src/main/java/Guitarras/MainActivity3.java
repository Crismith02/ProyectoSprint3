/*package com.example.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.music.Adaptadores.ProductAdapter;
import com.example.music.DB.DBHelper;
import com.example.music.Entidades.Product;
import com.example.music.Service.ProductService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;
    private ListView listViewProducts;
    private ProductAdapter productAdapter;
    private ArrayList<Product> arrayProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        arrayProducts = new ArrayList<>();
        try {
            dbHelper = new DBHelper(this);
            productService = new ProductService();
            arrayProducts = productService.cursorToArray(dbHelper.getProducts());
        }catch (Exception e){
            Log.e("DB", e.toString());
        }
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        productAdapter = new ProductAdapter(this, arrayProducts);
        listViewProducts.setAdapter(productAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getApplicationContext(), FormProduct.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}*/