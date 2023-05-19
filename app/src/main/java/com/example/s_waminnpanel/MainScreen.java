package com.example.s_waminnpanel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.s_waminnpanel.databinding.ActivityMainScreenBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class MainScreen extends AppCompatActivity {
    ActivityMainScreenBinding binding;
    private String id,product,desc,price;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageReference= FirebaseStorage.getInstance().getReference("product/*"+id+".png,.jpg,.pdf,.jpeg");
                storageReference.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                storageReference.getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                FirebaseFirestore.getInstance()
                                                        .collection("products")
                                                        .document(id)
                                                        .update("image",uri.toString());
                                                Toast.makeText(MainScreen.this,"succesfully uploaed",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=binding.ide.getText().toString();
                product=binding.Product.getText().toString();
                desc=binding.description.getText().toString();
                price=binding.price.getText().toString();
                btn();
            }
        });
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*".toString());
                startActivityForResult(intent,100);
            }
        });

    }

    private void btn() {
        id = UUID.randomUUID().toString();
        ProductModel productModel=new ProductModel(id,product,desc,price,null,true);
        
        FirebaseFirestore.getInstance()
                .collection("product")
                .document(id)
                .set(productModel);

        Toast.makeText(MainScreen.this,"succesfully uploaed",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            uri=data.getData();
            binding.image.setImageURI(uri);
        }
    }
}

