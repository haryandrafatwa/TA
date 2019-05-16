package org.d3ifcool.koor.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.koor.R;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_FOTO_KOOR;

public class KoorProfilActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private CircleImageView imageView;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_profil);

        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        sessionManager = new SessionManager(this);

        TextView textView_nama = findViewById(R.id.act_dsn_profil_nama);
        TextView textView_nip = findViewById(R.id.act_dsn_profil_nip);
        TextView textView_kode = findViewById(R.id.act_dsn_profil_kode);
        TextView textView_email = findViewById(R.id.act_dsn_profil_email);
        TextView textView_kontak = findViewById(R.id.act_dsn_profil_kontak);
        imageView = findViewById(R.id.act_dsn_profil_foto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permission  = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }else{
                        pickImageFromGallery();
                    }
                }else {
                    pickImageFromGallery();
                }
            }


        });

        textView_nama.setText(sessionManager.getSessionKoorNama());
        textView_nip.setText(sessionManager.getSessionKoorNip());
        textView_kode.setText(sessionManager.getSessionKoorKode());
        textView_email.setText(sessionManager.getSessionKoorEmail());
        textView_kontak.setText(sessionManager.getSessionKoorEmail());
        Picasso.get().load(URL_FOTO_KOOR + sessionManager.getSessionKoorFoto()).into(imageView);

    }
    private void pickImageFromGallery() {
        Intent galeri = new Intent(Intent.ACTION_PICK);
        galeri.setType("image/*");
        startActivityForResult(galeri, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_hanya_ubah) {
            Intent intentUbah = new Intent(KoorProfilActivity.this, KoorProfilUbahActivity.class);
            startActivity(intentUbah);

        } else {
        }
        return super.onOptionsItemSelected(item);
    }

}
