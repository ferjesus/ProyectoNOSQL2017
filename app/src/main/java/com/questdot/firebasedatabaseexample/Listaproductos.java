package com.questdot.firebasedatabaseexample;

import android.app.Activity;
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

public class Listaproductos extends Activity {

    LayoutInflater inflater1;

    int count =0;
    int age;
    String name;

    EditText txtsearch;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;

    Producto data;


    ListView listView;

    ArrayList<Producto> dataArrayList;

    CustomAdapter1 customAdapter;

    String key;

    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaproductos);



        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("Productos");
        key=databaseReference.push().getKey();

        listView = (ListView) findViewById(R.id.readlist);
        dataArrayList = new ArrayList<>();

        customAdapter = new CustomAdapter1(Listaproductos.this, dataArrayList);

        listView.setAdapter(customAdapter);


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


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String imagen1 = dataArrayList.get(temp).getLink();
                final View v = inflater1.from(getApplicationContext()).inflate(R.layout.custom_alert, null);
                temp = i;

                final EditText updtname, updtage;

                updtname = (EditText) v.findViewById(R.id.updtname);
                updtage = (EditText) v.findViewById(R.id.updtage);


                final AlertDialog.Builder builder  = new AlertDialog.Builder(Listaproductos.this).setView(v);
                final AlertDialog alert = builder.create();

                v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Producto tempData = new Producto(dataArrayList.get(temp).getKey(), updtname.getText().toString().trim(),
                                Integer.parseInt(updtage.getText().toString().trim()),imagen1);

                        databaseReference.child(dataArrayList.get(temp).getKey()).setValue(tempData);

                        dataArrayList.remove(temp);

                        dataArrayList.add(temp,tempData);

                        customAdapter.notifyDataSetChanged();
                    }
                });

                v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(temp == -1){

                            Toast.makeText(getApplicationContext(), "There is no data to delete", Toast.LENGTH_SHORT).show();

                        }else {

                            databaseReference.child(dataArrayList.get(temp).getKey()).removeValue();

                            dataArrayList.remove(temp);

                            customAdapter.notifyDataSetChanged();

                           alert.cancel();

                            temp = -1;
                        }
                    }
                });



                updtname.setText(dataArrayList.get(temp).getName());

                updtage.setText("" + dataArrayList.get(temp).getPrecio());



                try {


                    alert.show();

                } catch (Exception e) {

                    Log.d("show", "onItemClick: " + e);

                }




                return;


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



                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                            data = dataSnapshot1.getValue(Producto.class);
                            dataArrayList.clear();
                            dataArrayList.add(data);
                            Log.d("log", "onDataChange: "+dataSnapshot1.child("name").getValue());

                        }


                        func();






                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });








            }
        });

        realtimeUpdate();
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

    public void func(){

        if(count!=0){

            customAdapter = new CustomAdapter1(getApplicationContext(),dataArrayList);

            listView.setAdapter(customAdapter);

        }else {

            Toast.makeText(getApplicationContext(), "There is no data to show", Toast.LENGTH_SHORT).show();
            listView.setVisibility(View.GONE);
        }


    }
}
