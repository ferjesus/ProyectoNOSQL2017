package com.questdot.firebasedatabaseexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductosVistaActivity extends Activity {

    LayoutInflater inflater1dfdfdasdadadasdadsasdasdadsf;

    int count =0;
    int price;
    String name;
    String url;

    EditText txtname, txtprecio,txtsearch,txtlink;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Producto data;



    ArrayList<Producto> dataArrayList;

    CustomAdapter1 customAdapter;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productosvista);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Productos");
        key=databaseReference.push().getKey();

        txtname = (EditText) findViewById(R.id.pnombre);
        txtprecio = (EditText)findViewById(R.id.precio);
        txtlink = (EditText)findViewById(R.id.link);

          findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {

                        name = txtname.getText().toString().trim();
                        url = txtlink.getText().toString().trim();


                        if (TextUtils.isEmpty(name) ) {

                            Toast.makeText(getApplicationContext(), "Ingrese un Nombre", Toast.LENGTH_SHORT).show();

                        } else {

                            price = Integer.parseInt(txtprecio.getText().toString().trim());

                            data = new Producto(databaseReference.push().getKey(), name, price, url);

                            databaseReference.child(data.getKey()).setValue(data);

                            Toast.makeText(getApplicationContext(), "Resgistrado!!!", Toast.LENGTH_SHORT).show();

                            txtname.setText("");
                            txtprecio.setText("");
                            txtlink.setText("");
                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();

                    }


                }
        });

        dataArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter1(ProductosVistaActivity.this, dataArrayList);



        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Producto datam = dataSnapshot.getValue(Producto.class);

                dataArrayList.add(datam);

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        txtsearch = (EditText) findViewById(R.id.search);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = txtsearch.getText().toString().trim();

                databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ++count;









                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });








            }
        });

        realtimeUpdate();
    }

    public void Onclickhome(View v)
    {
        Intent ven=new Intent(this,MainA.class);
        startActivity(ven);
    }

    public void realtimeUpdate(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    data = dataSnapshot1.getValue(Producto.class);

                    dataArrayList.add(data);

                }


               customAdapter.notifyDataSetChanged();


            }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}


}
