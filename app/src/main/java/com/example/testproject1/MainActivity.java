package com.example.testproject1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.testproject1.databinding.ActivityMainBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation=findViewById(R.id.bottomNav);
        bottomNavigation.show(1, true);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();

        // add your bottom navigation icon here
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_groups_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_person_24)) ;


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                return null ;
            }
        });


        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                String name ;
                switch(model.getId()){
                    case 1 :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new HomeFragment()).commit();
                        break;
                    case 2 :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new TeamTrackerFragment()).commit();
                        break;
                    case 4 :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout , new MoreFragment()).commit();
                        break;
                }
                return null;
            }
        });


    }


}
