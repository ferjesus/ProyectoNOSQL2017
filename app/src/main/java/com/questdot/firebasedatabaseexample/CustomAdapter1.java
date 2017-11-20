package com.questdot.firebasedatabaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CustomAdapter1 extends BaseAdapter {

    TextView name, precio, link;
    public ImageView imagen;

    Context context;

    ArrayList<Producto> data;


    LayoutInflater inflater;

    public CustomAdapter1(Context context, ArrayList<Producto> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(context).inflate(R.layout.custom_list,viewGroup,false);

        name = (TextView) view.findViewById(R.id.readname);
        precio = (TextView) view.findViewById(R.id.readage);
        link = (TextView) view.findViewById(R.id.readlink);
        imagen = (ImageView) view.findViewById(R.id.imageView);

        name.setText(data.get(i).getnombre());
        precio.setText(precio.getText()+""+ data.get(i).getPrecio());
        //link.setText(link.getText()+""+data.get(i).getLink());
        Glide.with(context).load(data.get(i).getLink()).centerCrop().into(imagen);



        return view;
    }
}
