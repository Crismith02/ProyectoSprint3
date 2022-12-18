package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.music.DB.DBHelper;
import com.example.music.Entidades.Producto3;
import com.example.music.Service.ProductService;

import java.util.ArrayList;


public class Lista extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;
    private Button btnReturn;
    private ImageView imgProductos1;
    private TextView tvNombreProducto1, tvDescripcionProducto1, tvPrecioProducto1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        imgProductos1 = (ImageView) findViewById(R.id.imgProductos1);
        tvNombreProducto1 = (TextView) findViewById(R.id.tvNombreProducto1);
        tvDescripcionProducto1 = (TextView) findViewById(R.id.tvDescripcionProducto1);
        tvPrecioProducto1 = (TextView) findViewById(R.id.tvPrecioProducto1);

        dbHelper = new DBHelper(this);
        productService = new ProductService();

        Intent intent = getIntent();
        String id = String.valueOf(intent.getIntExtra("id", 0));
        ArrayList<Producto3> productoDB = productService.cursorToArray(dbHelper.getProductById(id));
        Producto3 producto3 = productoDB.get(0);

        Bitmap bitmap = BitmapFactory.decodeByteArray(producto3.getImagen(), 0, producto3.getImagen().length);
        imgProductos1.setImageBitmap(bitmap);


        tvNombreProducto1.setText(intent.getStringExtra("nombre"));
        tvDescripcionProducto1.setText(intent.getStringExtra("descripcion"));
        tvPrecioProducto1.setText(String.valueOf(intent.getIntExtra("precio", 0)));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent2);
            }
        });
    }
}

