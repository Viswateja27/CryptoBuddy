package com.example.cbcore.Encryption;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cbcore.MainActivity;
import com.example.cbcore.enc;

import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encrypt
{
    private static final String TAG = "Encrypt";
    private static  String encryptionKey = "";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    static Context mcontext;
    static String  s="";







    public static String encrypt(String filepath, Context context, String skey) throws IOException {
        encryptionKey=skey;

        mcontext=context;

        int index = filepath.lastIndexOf(":");
        String temppath= filepath.substring(index+1);
        String abspath= temppath;
        int index1 =abspath.lastIndexOf("/");
        String writepath = abspath.substring(0,index1);


        File myfile = new File(abspath);
        Log.e(TAG, "encrypt: " + myfile);
        FileInputStream fis = new FileInputStream(myfile);
        DataInputStream dis= new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        String str;
        while((str= br.readLine())!=null)
        {
            Encrypt.s = Encrypt.s +str;
        }

        Log.e(TAG, "encrypt: " + Encrypt.s);

        String plainText= Encrypt.s;



        String encryptedText = "";
        byte[] key= new byte[10000];
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
            System.err.println("Encrypt Exception : "+E.getMessage());
            Log.e(TAG, "encrypt: " + "clipher exception" );
        }

//        //File
//
//        int x=0,y=0;
//        FileWriter fw= new FileWriter("encrypted_"+x+".txt");
//        //File file = new File("encrypted"+x+".txt");
//        x++;
//        s= encryptedText;
//
//        // read character wise from string and write
//        // into FileWriter
//        for (int i = 0; i < s.length(); i++)
//            fw.write(s.charAt(i));
//
//        System.out.println("Writing successful");
//        //close the file
//        fw.close();
//
//        //Key
//
//        FileWriter fwk= new FileWriter("encrypted_key"+y+".txt");
//        //File file = new File("encrypted"+x+".txt");
//        y++;
//        String s_k=encryptionKey;
//        // read character wise from string and write
//        // into FileWriter
//        for (int i = 0; i < s_k.length(); i++)
//            fwk.write(s_k.charAt(i));
//
//        System.out.println("Writing successful");
//        //close the file
//        fwk.close();

        Write(encryptedText,writepath);

        String encyptStr =encryptedText;


        //String decryptStr  = decrypt();

        System.out.println("Plain   String  : "+plainText);
        System.out.println("Encrypt String  : "+encyptStr);
        return encyptStr;

        //System.out.println("Decrypt String  : "+decryptStr);
    }

    private static void Write(String encryptedText, String abspath) throws IOException {
        File mfile = new File(abspath,"Cryptobuddy");
        if(!mfile.exists()){
            mfile.mkdir();
        }
        File gpxfile = new File(mfile, enc.Filename +"_Encrypted.txt");

        FileWriter writer = new FileWriter(gpxfile);
        writer.append(encryptedText);
        writer.flush();
        writer.close();

        Log.e(TAG, "Write: new file dir "+ mfile );
        Log.e(TAG, "Write: " + mcontext.getFilesDir() );
        Toast.makeText(mcontext,"File Created Sucessfully",Toast.LENGTH_LONG).show();
    }


}