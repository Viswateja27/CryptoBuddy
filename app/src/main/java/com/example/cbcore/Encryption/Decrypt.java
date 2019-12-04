package com.example.cbcore.Encryption;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.cbcore.decr;

import java.io.*;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt
{
    private static final String TAG = "Decrypt";
    private static  String encryptionKey           = "";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    static Context mcontext;

    static String  s="";



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String filepath, Context context, String skey) throws IOException {

        mcontext=context;
        encryptionKey=skey;

        int index = filepath.lastIndexOf(":");
        String temppath= filepath.substring(index+1);
        String abspath= temppath;
        int index1 =abspath.lastIndexOf("/");
        String writepath = abspath.substring(0,index1);


        File myfile = new File(abspath);
        Log.e(TAG, "decrypt: " + myfile);
        FileInputStream fis = new FileInputStream(myfile);
        DataInputStream dis= new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(dis));
        String str;
        while((str= br.readLine())!=null)
        {
            Decrypt.s = Decrypt.s +str;
        }

        Log.e(TAG, "decrypt: " + Decrypt.s);

        String plainText= Decrypt.s;


        String decryptedText = "";
        //File
        FileReader fr= null;

        String encryptedText = Decrypt.s;
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
            Log.e(TAG, "decrypt: " + "clipher exception" );
        }

        //Decrypted File
//        int x=0;
//        FileWriter fw= new FileWriter("decrypted_"+x+".txt");
//        //File file = new File("encrypted"+x+".txt");
//        x++;
//        String s_d= decryptedText;
//
//        // read character wise from string and write
//        // into FileWriter
//        for (int i = 0; i < s_d.length(); i++)
//            fw.write(s_d.charAt(i));
//
//        System.out.println("Writing successful");
//        //close the file
//        fw.close();

        Write(decryptedText,writepath);


        String decryptStr =decryptedText;

        System.out.println("Decrypt String  : "+decryptStr);

        return decryptStr;
    }


    private static void Write(String decryptedText, String abspath) throws IOException {
        File mfile = new File(abspath,"Cryptobuddy");
        if(!mfile.exists()){
            mfile.mkdir();
        }
        File gpxfile = new File(mfile, decr.Filename + "_decrypted.txt");

        FileWriter writer = new FileWriter(gpxfile);
        writer.append(decryptedText);
        writer.flush();
        writer.close();
        Log.e(TAG, "Write: new file dir "+ mfile );
        Log.e(TAG, "Write: " + mcontext.getFilesDir() );
        Toast.makeText(mcontext,"File Created Sucessfully",Toast.LENGTH_LONG).show();
    }
}