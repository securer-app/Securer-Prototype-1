package com.securer.nemboru.proto1;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class landing extends AppCompatActivity {

    protected TextView output;
    protected Button encrypt;
    protected Button decrypt;

    protected EditText payload;
    protected EditText key;

    protected void doEncrypt(){
       String t = AESWrapper.Encrypt(key.getText().toString(), payload.getText().toString());
       output.setText(t);
    }

    protected void doDecrypt(){
        String ant = output.getText().toString();
        String t;
        try {
            t = AESWrapper.Decrypt(key.getText().toString(), output.getText().toString());
        } catch (BadPaddingException e) {
            t = null;
            badPass();
        } catch (InvalidKeyException e) {
            badPass();
            t = null;
        } catch (IllegalBlockSizeException e) {
            t = null;
            badPass();
        }
        if(t == null){
            output.setText(ant);
        }else{
            output.setText(t);
        }
    }

    protected void badPass(){
        Snackbar.make(findViewById(android.R.id.content),"Invalid password",Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        output = (TextView) findViewById(R.id.output);
        encrypt = (Button) findViewById(R.id.encrypt_button);
        decrypt = (Button) findViewById(R.id.decrypt_button);

        payload = (EditText) findViewById(R.id.content);
        key = (EditText) findViewById(R.id.key);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doEncrypt();
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDecrypt();
            }
        });
    }
}
