package com.sudhirtheindian4.firebasephotorecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<model> datalist;
    FirebaseFirestore db;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist= new ArrayList<>();

//        adapter = new MyAdapter(datalist);
        adapter = new MyAdapter(datalist,this);
        recyclerView.setAdapter(adapter);





        db= FirebaseFirestore.getInstance();

//        db.collection("students").get()
        // name ke anusar item show hoga
        db.collection("students").orderBy("name").get()
        ///now small letter wale phle dikhega
//        db.collection("students").orderBy("name", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list){
                            model obj = d.toObject(model.class);
                            datalist.add(obj);

                        }

                        ///set  the adapter
                        /// adapter check again arralist
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}