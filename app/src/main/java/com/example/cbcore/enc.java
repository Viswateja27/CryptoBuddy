package com.example.cbcore;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.io.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class enc extends AppCompatActivity {

    private static final String TAG = "enc";
    public static String Filename;
    TextView attachfile;
    String Path;
    EditText KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enc);

        attachfile = findViewById(R.id.attach_file);
        KEY=findViewById(R.id.key);

    }

    private static final int FILE_SELECT_CODE = 0;
    public void add_enc(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,10);
        if(Path!=null){

            Log.e(TAG, "add_enc: " + "path not null");

        }


    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case FILE_SELECT_CODE:
//                if (resultCode == RESULT_OK) {
//                    // Get the Uri of the selected file
//                    Uri uri = data.getData();
//                    Log.d(TAG, "File Uri: " + uri.toString());
//                    // Get the path
//                    String path = null;
//                    try {
//                        path = FileUtils.getPath(this, uri);
//
//                    } catch (URISyntaxException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d(TAG, "File Path: " + path);
//                    // Get the file instance
//                    // File file = new File(path);
//                    // Initiate the upload
//                }
//                break;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if(resultCode!=RESULT_CANCELED){
                     Path=data.getData().getPath();
                    Log.e(TAG, "onActivityResult: " + Path );
                    Pathsearch();


                }
        }
    }

    void Pathsearch()
    {
        try {
            int temp = Path.lastIndexOf("/");
            Filename= Path.substring(temp+1);
            String encryptedstr=com.example.cbcore.Encryption.Encrypt.encrypt(Path,enc.this,KEY.getText().toString());
            Log.e(TAG, "onActivityResult: " + encryptedstr );
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "add_enc: " + e.toString());
        }

    }
}
