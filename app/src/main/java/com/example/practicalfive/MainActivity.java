package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMunicipality = findViewById(R.id.etMunicipality);

        // Respond to the keyboard "Go" action, or the Enter key on a hardware keyboard.
        etMunicipality.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean goAction = actionId == EditorInfo.IME_ACTION_GO;
                boolean enterKey = event != null
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN;
                if (goAction || enterKey) {
                    processInput();
                    return true;
                }
                return false;
            }
        });
    }

    private void processInput() {
        String raw = etMunicipality.getText().toString();

        if (raw.trim().isEmpty()) {
            etMunicipality.setError(getString(R.string.error_empty));
            return;
        }

        String normalised = TextProcessor.processMunicipality(raw);
        if (normalised == null) {
            etMunicipality.setError(getString(R.string.error_invalid));
            return;
        }

        // Valid input: hand the normalised name to the second Activity with an explicit Intent.
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(Municipality.EXTRA_MUNICIPALITY, normalised);
        startActivity(intent);
    }
}
