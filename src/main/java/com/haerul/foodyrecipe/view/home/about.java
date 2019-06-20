package com.haerul.foodyrecipe.view.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.haerul.foodyrecipe.R;

import butterknife.ButterKnife;

public class about extends AppCompatActivity {

    private Switch myswitch;
    private Button button;
    AdView madview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.DayTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        myswitch = (Switch) findViewById(R.id.myswitch);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });
        button = (Button) findViewById(R.id.developerinfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        button = (Button) findViewById(R.id.aboutapp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });

        button = (Button) findViewById(R.id.ratefoodyapp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openActivity4();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));   //getPackageName()
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.bhargav.worldfamousfoodyrecipeapp")));
                }
            }
        });
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");

        madview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        madview.loadAd(adRequest);
    }


    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), about.class);
        startActivity(i);
        finish();
    }

    public void openActivity3() {
        Intent intent = new Intent(this, developer_info_activity.class);
        startActivity(intent);

    }
    public void openActivity2() {
        Intent intent = new Intent(this, about_app_activity.class);
        startActivity(intent);

    }
    public void openActivity4() {
        Intent intent = new Intent(this, rate_foody_app_activity.class);
        startActivity(intent);

    }
}
