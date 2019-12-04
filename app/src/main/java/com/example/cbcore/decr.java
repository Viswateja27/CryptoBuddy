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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class decr extends AppCompatActivity {

    private static final String TAG = "decr";
    public static String Filename;
    TextView attachfile;
    String Path;
    EditText KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decr);

        attachfile = findViewById(R.id.attach_file);
        KEY=findViewById(R.id.key);
    }

    private static final int FILE_SELECT_CODE = 0;

    public void add_dec(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,10);
        if(Path!=null){

            Log.e(TAG, "add_decr: " + "path not null");

        }
    }


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
            String subString = Path.substring(temp+1);
            subString = subString.replace("Encrypted","");

            Filename= subString;
            String decryptedstr=com.example.cbcore.Encryption.Decrypt.decrypt(Path,decr.this,KEY.getText().toString());


            Log.e(TAG, "onActivityResult: " +decryptedstr);
            Log.e(TAG, "Pathsearch: "+ Path );
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "add_decr: " + e.toString());
        }

    }

}
