package com.samples.location.findbestproviders;

import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class FindBestProvidersActivity extends AppCompatActivity {

    private TextView text;
    private LocationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_best_providers);

        text = (TextView)findViewById(R.id.text);
        manager = (LocationManager)getSystemService(LOCATION_SERVICE);

        text.append("List of Location providers\n");

        List<String> providers = manager.getAllProviders();
        for (int i=0; i<providers.size(); i++){
            String provider = providers.get(i);
            text.append("\nProvider: " + provider);
            text.append("\nEnabled: " +
                    manager.isProviderEnabled(provider) + "\n");
        }

        // Создаем объект Criteria и задаем
        // требуемые характеристики провайдера
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        criteria.setCostAllowed(true);
        criteria.setSpeedRequired(true);

        String bestProvider = manager.getBestProvider(criteria, true);
        text.append("\nBest provider: " + bestProvider);
    }
}
