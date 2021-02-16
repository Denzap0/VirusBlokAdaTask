package com.example.virusblokadatask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<ApplicationInfo> appsList = this.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        ArrayList<Pair<String,String>> appsNameHashList = new ArrayList<>();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(md != null) {
            for (ApplicationInfo curApp : appsList) {
                if(curApp.name != null) {
                md.update(curApp.name.getBytes());
                appsNameHashList.add(Pair.create(curApp.name,Base64.getEncoder().encodeToString(md.digest())));
                }
            }
        }
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new AppsListAdapter(appsNameHashList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    }

}