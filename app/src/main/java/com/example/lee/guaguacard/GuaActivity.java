package com.example.lee.guaguacard;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.winsontan520.WScratchView;

public class GuaActivity extends AppCompatActivity {

    private WScratchView guaWscratchView ;
    private Button resetButton;
    private TextView guaResultTextView;
    private float percentfloat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gua);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        guaWscratchView = (WScratchView)findViewById(R.id.guawscratchView);
        resetButton = (Button)findViewById(R.id.resetButton);
        guaResultTextView = (TextView)findViewById(R.id.guaResultTextView);

        CardData cardData = (CardData) getIntent().getSerializableExtra("card_Data");


        guaWscratchView.setOverlayColor(cardData.getCardColor());
        guaWscratchView.setScratchable(true);
        guaWscratchView.setAntiAlias(true);

        guaResultTextView.setText(cardData.getShowString());
        guaResultTextView.setTextSize(cardData.getShowTextSize());


        guaWscratchView.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v)
            {
                percentfloat=v;
            }
            @Override
            public void onDetach(boolean b)
            {
                if (percentfloat>90) {
                    guaWscratchView.setScratchAll(true);
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guaWscratchView.resetView();
                guaWscratchView.setScratchAll(false);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.back_start,R.anim.back_end);

        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                finish();
                overridePendingTransition(R.anim.back_start,R.anim.back_end);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
