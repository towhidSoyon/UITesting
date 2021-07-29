package com.example.a2uitesting.SimpleNavDrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a2uitesting.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class EasyNavActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_nav);

        toolbar=findViewById(R.id.topApp);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
               int id=item.getItemId();
               drawerLayout.closeDrawer(GravityCompat.START);
               switch (id){
                   case R.id.nav_home:
                       Toast.makeText(EasyNavActivity.this, "Home is Clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_about:
                       Toast.makeText(EasyNavActivity.this, "About is Clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_res:
                       Toast.makeText(EasyNavActivity.this, "Restaurant is Clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_settings:
                       Toast.makeText(EasyNavActivity.this, "Settings is clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_share:
                       Toast.makeText(EasyNavActivity.this, "Share is clicked", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_login:
                       Toast.makeText(EasyNavActivity.this, "Logged IN", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_logout:
                       Toast.makeText(EasyNavActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_close:
                       Toast.makeText(EasyNavActivity.this, "Closed", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.nav_profile:
                       Toast.makeText(EasyNavActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                       break;
                   default:
                       return true;
               }
               return true;
           }
       });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
}