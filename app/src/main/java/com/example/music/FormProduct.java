package com.example.music;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.music.DB.DBHelper;
import com.example.music.Entidades.Producto3;
import com.example.music.Service.ProductService;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class FormProduct extends AppCompatActivity {

    private Button btnAgregarProducto, btnConsultarProducto, btnEliminarProducto, btnActualizarProducto;
    private EditText editNombreProducto, editDescripcionProducto, editPrecioProducto, editIdProductos;
    private ImageView imgProductos2;
    private DBHelper dbHelper;
    private ActivityResultLauncher<String> content;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_product);


        btnActualizarProducto = (Button) findViewById(R.id.btnActualizarProducto);
        btnConsultarProducto = (Button) findViewById(R.id.btnConsultarProducto);
        btnAgregarProducto = (Button) findViewById(R.id.btnAgregarProducto);
        btnEliminarProducto = (Button) findViewById(R.id.btnEliminarProducto);
        editNombreProducto = (EditText) findViewById(R.id.editNombreProducto);
        editDescripcionProducto = (EditText) findViewById(R.id.editDescripcionProducto);
        editPrecioProducto = (EditText) findViewById(R.id.editPrecioProducto);
        editIdProductos = (EditText) findViewById(R.id.editIdProductos);
        imgProductos2 = (ImageView) findViewById(R.id.imgProductos2);


        try {
            dbHelper = new DBHelper(this);
            productService = new ProductService();
            content = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>() {
                        @Override
                        public void onActivityResult(Uri result) {
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(result);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                imgProductos2.setImageBitmap(bitmap);
                            }catch (FileNotFoundException e){
                                Log.e("FileError", e.toString());
                            }
                        }
                    }
            );
        }catch (Exception e){
            Log.e("DB", e.toString());
        }

        imgProductos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                content.launch("image/*");
            }
        });


        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.insertProduct(
                        editNombreProducto.getText().toString(),
                        editDescripcionProducto.getText().toString(),
                        editPrecioProducto.getText().toString(),
                        productService.imageViewToByte(imgProductos2));

                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent);
            }
        });

        btnConsultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =  editIdProductos.getText().toString().trim();
                if (id.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "Ingrese un ID", Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<Producto3> productoDB = productService.cursorToArray(dbHelper.getProductById(id));
                    if(productoDB.size() != 0){
                        Producto3 producto3 = productoDB.get(0);
                        editNombreProducto.setText(producto3.getNombre());
                        editDescripcionProducto.setText(producto3.getDescripcion());
                        editPrecioProducto.setText(String.valueOf(producto3.getPrecio()));
                        Bitmap bitmap = BitmapFactory.decodeByteArray(producto3.getImagen(), 0, producto3.getImagen().length);
                        imgProductos2.setImageBitmap(bitmap);
                    }else {
                        Toast.makeText(getApplicationContext(), "No existe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =  editIdProductos.getText().toString().trim();
                dbHelper.deleteProductById(id);
                clean();
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id =  editIdProductos.getText().toString().trim();
                if (id.compareTo("")!=0){
                    dbHelper.updateProduct(
                            id,
                            editNombreProducto.getText().toString(),
                            editDescripcionProducto.getText().toString(),
                            editPrecioProducto.getText().toString(),
                            productService.imageViewToByte(imgProductos2)
                    );
                    clean();
                }
            }
        });
    }

    public void clean(){
        editNombreProducto.setText("");
        editDescripcionProducto.setText("");
        editPrecioProducto.setText("");
        imgProductos2.setImageResource(R.drawable.ic_launcher_background);
    }
}