package com.questdot.firebasedatabaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainA extends AppCompatActivity {
    Button l;
    Button S;

    ImageView h;
    ImageView P;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        l = (Button) findViewById(R.id.buttonL);
        S = (Button) findViewById(R.id.buttonS);
        h = (ImageView) findViewById(R.id.imageView);


       Glide.with(this).load(getImage("h")).fitCenter().into(h);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    public void Onclick(View v)
    {
       // Intent ven=new Intent(this,Main.class);
       // startActivity(ven);
    }
    public void OnclickShop(View v)
    {
        startActivity(new Intent("com.example.fer.gamestore.tabs.TabsMain"));
    }
    public void OnclickCar(View v)
    {
        startActivity(new Intent("com.example.fer.gamestore.carro.TabsMainC"));
    }


    public int getImage(String imageName) {

        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return drawableResourceId;
    }
}
