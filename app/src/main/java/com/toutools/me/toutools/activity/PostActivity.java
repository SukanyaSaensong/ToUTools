package com.toutools.me.toutools.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.toutools.me.toutools.models.Post;
import com.toutools.me.toutools.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {


    String Storage_Path = "All_Image_Uploads/";

    private DatabaseReference mDatabase;
    private DatabaseReference mCategoryRef;

    private FirebaseUser mFirebaseUser;
    private FirebaseAuth mFirebaseAuth;

    Button ChooseButton, UploadButton;
    EditText ImageName, PriceName, discrip, phone;
    ImageView SelectImage;
    Uri FilePathUri;
    Spinner listCategory, listLocation;


    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        initInstances();

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        mCategoryRef = mDatabase.child("category");
        databaseReference = mDatabase.child("Post");


        ChooseButton = (Button) findViewById(R.id.ButtonChooseImage);
        UploadButton = (Button) findViewById(R.id.ButtonUploadImage);

        ImageName = (EditText) findViewById(R.id.EditTextNameTools);
        PriceName = (EditText) findViewById(R.id.EditTextPriceTools);
        discrip = (EditText) findViewById(R.id.editdis);
        phone = (EditText) findViewById(R.id.editPhone);

        listCategory = (Spinner) findViewById(R.id.category);
        listLocation = (Spinner) findViewById(R.id.location);

        SelectImage = (ImageView) findViewById(R.id.ShowImageView);
        progressDialog = new ProgressDialog(PostActivity.this);

        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImageFileToFirebaseStorage();
            }
        });
    }

    private void initInstances() {

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                SelectImage.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    public void UploadImageFileToFirebaseStorage() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            if (FilePathUri != null) {

                progressDialog.setTitle("Image is Uploading...");

                progressDialog.show();

                StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

                StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = storageReference2nd.putFile(FilePathUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                String User = user.getUid();
                                String TempImageName = ImageName.getText().toString().trim();
                                String TempImagePrice = PriceName.getText().toString().trim();
                                String category = listCategory.getSelectedItem().toString();
                                String discription = discrip.getText().toString().trim();
                                String location = listLocation.getSelectedItem().toString();
                                String phoneNumber = phone.getText().toString().trim();
                                // Hiding the progressDialog after done uploading.
                                progressDialog.dismiss();

                                // Showing toast message after done uploading.
                                Toast.makeText(getApplicationContext(), "การกรอกข้อมูลประกาศสำเร็จ ", Toast.LENGTH_LONG).show();


                                @SuppressWarnings("VisibleForTests")
                                Post post = new Post(User, TempImagePrice, TempImageName, taskSnapshot.getDownloadUrl().toString(), category, discription, location, phoneNumber);

                                String ImageUploadId = databaseReference.push().getKey();


                                HashMap<String, Object> postValues = new HashMap<>();
                                Map<String, Object> childUpdates = new HashMap<>();
                                childUpdates.put("/Post/" + ImageUploadId, postValues);

                                postValues.put("UserId", user.getUid());
                                mDatabase.updateChildren(childUpdates);
                                databaseReference.child(ImageUploadId).setValue(post);

                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                progressDialog.dismiss();
                                Toast.makeText(PostActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                progressDialog.setTitle("Uploading...");
                            }
                        });
            } else {

                Toast.makeText(PostActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_LONG).show();

            }


        }

    }


}
