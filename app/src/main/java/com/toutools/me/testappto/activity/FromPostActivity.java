package com.toutools.me.testappto.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.toutools.me.testappto.R;

public class FromPostActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_post);

        button = (Button)findViewById(R.id.ButtonRegister);


//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FromPostActivity.this,PostActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
            switch (item.getItemId()) {
                case R.id.action_nextPage:
                    startActivity(new Intent(FromPostActivity.this,RegisterActivity

                            .class));
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
