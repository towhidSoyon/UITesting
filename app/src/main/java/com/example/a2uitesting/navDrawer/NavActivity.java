package com.example.a2uitesting.navDrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.a2uitesting.R;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

public class NavActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener{

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_NEARBY_RES = 3;
    private static final int POS_SETTINGS = 4;
    private static final int POS_ABOUT_US = 5;
    private static final int POS_LOGOUT = 7;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        Toolbar toolbar=findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);

        slidingRootNav=new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();
        
        screenIcons=loadScreenIcons();
        screenTitles=loadScreenTitle();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_MY_PROFILE),
                createItemFor(POS_NEARBY_RES),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_ABOUT_US),
                new SpaceItem(260),
                createItemFor(POS_LOGOUT)
        ));

        adapter.setListener(this);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setSelected(POS_DASHBOARD);
    }

    private DrawerItem createItemFor (int position){
        return new SimpleItem(screenIcons[position],screenTitles[position])
                .withIconTint(color(R.color.purple_200))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.purple_200))
                .withSelectedTextTint(color(R.color.purple_200));
    }

    private int color(int res){
        return ContextCompat.getColor(this,res);
    }

    private String[] loadScreenTitle() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenIcon);
        Drawable[] icons=new Drawable[ta.length()];

        for (int i=0; i< ta.length();i++){
            int id=ta.getResourceId(i,0);
            if(id != 0){
                icons[i]=ContextCompat.getDrawable(this,id);
            }
        }
        ta.recycle();
        return icons;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        if(position == POS_DASHBOARD){
            DashBoardFragment dashBoardFragment=new DashBoardFragment();
            transaction.replace(R.id.container,dashBoardFragment);
        }
        else if(position == POS_MY_PROFILE){
            ProfileFragment profileFragment=new ProfileFragment();
            transaction.replace(R.id.container,profileFragment);
        }
        else if(position == POS_NEARBY_RES){
            NearbyResFragment nearbyResFragment=new NearbyResFragment();
            transaction.replace(R.id.container,nearbyResFragment);
        }
        else if(position == POS_SETTINGS){
            SettingsFragment settingsFragment=new SettingsFragment();
            transaction.replace(R.id.container,settingsFragment);
        }
        else if(position == POS_ABOUT_US){
            AboutUsFragment aboutUsFragment =new AboutUsFragment();
            transaction.replace(R.id.container,aboutUsFragment);
        }
        else if (position==POS_LOGOUT){
            finish();
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
}