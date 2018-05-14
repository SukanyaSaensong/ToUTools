package com.toutools.me.toutools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toutools.me.toutools.R;
import com.toutools.me.toutools.adapter.RecyclerViewAdapter;
import com.toutools.me.toutools.models.Post;

import java.util.ArrayList;
import java.util.List;

public class MenuSixActivity extends AppCompatActivity {
    DatabaseReference databaseRef;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    List<Post> listPots = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_six);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuSixActivity.this));
        ;

        databaseRef = FirebaseDatabase.getInstance().getReference();
        Query query = databaseRef.child("Post")
                .orderByChild("category").equalTo("ช่างทั่วไป");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            listPots.clear();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                Post post = snapshot.getValue(Post.class);
                listPots.add(post);
            }
            adapter = new RecyclerViewAdapter(getApplicationContext(), listPots);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
