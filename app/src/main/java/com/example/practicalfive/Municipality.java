package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Municipality extends AppCompatActivity {

    public static final String EXTRA_MUNICIPALITY = "municipality";

    private TextView tvName;
    private TextView tvInfo;
    private ImageView ivMunicipality;
    private MaterialButton btnVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        tvName = findViewById(R.id.tvMunicipalityName);
        tvInfo = findViewById(R.id.tvMunicipalityInfo);
        ivMunicipality = findViewById(R.id.ivMunicipality);
        btnVisit = findViewById(R.id.btnVisitSite);

        // Re-validate the incoming name so this Activity is safe on its own.
        String raw = getIntent().getStringExtra(EXTRA_MUNICIPALITY);
        String name = TextProcessor.processMunicipality(raw);
        if (name == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        applyMunicipality(name);
    }

    /**
     * Selects the colour, image and summary that belong to the validated municipality
     * and applies them to the UI using named Android resources.
     */
    private void applyMunicipality(String name) {
        int colorRes;
        int imageRes;
        int summaryIndex;

        switch (name) {
            case "Shanghai":
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                summaryIndex = 1;
                break;
            case "Chongqing":
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                summaryIndex = 2;
                break;
            case "Tianjin":
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                summaryIndex = 3;
                break;
            case "Beijing":
            default:
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                summaryIndex = 0;
                break;
        }

        int colour = ContextCompat.getColor(this, colorRes);

        tvName.setText(name);
        tvName.setBackgroundColor(colour);
        tvName.setTextColor(ContextCompat.getColor(this, R.color.color_text_light));

        ivMunicipality.setImageResource(imageRes);

        Resources res = getResources();
        String[] summaries = res.getStringArray(R.array.municipalities_array);
        tvInfo.setText(summaries[summaryIndex]);
        tvInfo.setTextColor(colour);

        btnVisit.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(colour));
    }
}
