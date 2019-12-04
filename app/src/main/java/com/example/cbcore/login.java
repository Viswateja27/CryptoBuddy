package com.example.cbcore;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cbcore.external.Permissions;

import java.io.File;
import java.security.Permission;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(!checkPermissionArray(Permissions.permissions)){

            verifyPermission(Permissions.permissions);

        }


    }

    public void signup(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    public void login(View view) {
        Intent in = new Intent(this, home.class);
        startActivity(in);
        Toast.makeText(this,"Lon in Sucessfull",Toast.LENGTH_LONG).show();
    }
    private void verifyPermission(String[] permissions) {
        Log.e("verify ", "verifyPermission: ");

        ActivityCompat.requestPermissions(this,permissions,1);
    }

    private boolean checkPermissionArray(String[] permission) {

        for (int i=0;i<permission.length;i++){
            String singlep = permission[i];
            if(!checksinglep(singlep)){
                return false;
            }

        }

        return true;
    }

    private boolean checksinglep(String singlep) {

        int PermissionGranted = ActivityCompat.checkSelfPermission(this,singlep);
        if(PermissionGranted!= PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else {
            return true;
        }

    }

}
