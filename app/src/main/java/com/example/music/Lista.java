package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class Lista extends AppCompatActivity {
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

        Intent intent = getIntent();
        imgProductos1.setImageResource(intent.getIntExtra("imagen",R.drawable.ic_launcher_background));
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

