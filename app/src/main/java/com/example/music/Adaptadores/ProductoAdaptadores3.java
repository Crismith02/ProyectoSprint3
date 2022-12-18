package com.example.music.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.music.Entidades.Producto3;
import com.example.music.Lista;
import com.example.music.MainActivity4;
import com.example.music.R;

import java.util.ArrayList;

public class ProductoAdaptadores3 extends BaseAdapter {
    private Context context;
    private ArrayList<Producto3> arrayProductos;

    public ProductoAdaptadores3(Context context, ArrayList<Producto3> arrayProductos) {
        this.context = context;
        this.arrayProductos = arrayProductos;
    }

    @Override
    public int getCount() {
        return arrayProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.productos_template3,null);

        Producto3 producto3 = arrayProductos.get(i);
        ImageView imgProducto = (ImageView) view.findViewById(R.id.imgProducto);
        TextView tvNombreProducto = (TextView) view.findViewById(R.id.tvNombreProducto);
        TextView tvDescripcionProducto = (TextView) view.findViewById(R.id.tvDescripcionProducto);
        TextView tvPrecioProducto = (TextView) view.findViewById(R.id.tvPrecioProducto);
        Button btnProducto = (Button) view.findViewById(R.id.btnProducto);

        Bitmap bitmap = BitmapFactory.decodeByteArray(producto3.getImagen(), 0, producto3.getImagen().length);
        imgProducto.setImageBitmap(bitmap);
        tvNombreProducto.setText(producto3.getNombre());
        tvDescripcionProducto.setText(producto3.getDescripcion());
        tvPrecioProducto.setText(String.valueOf(producto3.getPrecio()));

        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),Lista.class);
                intent.putExtra("nombre", producto3.getNombre());
                intent.putExtra("descripcion", producto3.getDescripcion());
                intent.putExtra("precio", producto3.getPrecio());
                intent.putExtra("id", producto3.getId());
                context.startActivity(intent);
            }
        });

        return view;
    }
}
