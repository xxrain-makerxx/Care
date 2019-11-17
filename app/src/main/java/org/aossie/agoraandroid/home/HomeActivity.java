package org.aossie.agoraandroid.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.utilities.SharedPrefs;


public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private HomeViewModel homeViewModel;
    private SharedPrefs sharedPrefs;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeViewModel = new HomeViewModel(getApplication(), this);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navView = findViewById(R.id.nav_view);

        View hView = navView.getHeaderView(0);
        TextView nav_user_name = hView.findViewById(R.id.header_user_name);
        TextView nav_user_email = hView.findViewById(R.id.header_email);
        nav_user_name.setText(sharedPrefs.getUserName());
        nav_user_email.setText(sharedPrefs.getEmail());


        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.fragment);// navController

        NavigationUI.setupWithNavController(navView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController,
                drawerLayout);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,
                drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_logout) {
            homeViewModel.doLogout(sharedPrefs.getToken());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
