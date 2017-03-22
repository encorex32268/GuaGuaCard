package com.example.lee.guaguacard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.pdf.PdfRenderer;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.IndicatorMode;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;
import com.winsontan520.WScratchView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private WScratchView guaCardView;
    private EditText inputEditText;
    private SeekBar textSizeSeekBar;
    private Button colorButton;
    private Button createButton;
    private TextView resultTextView;
    private ChromaDialog.Builder chromaBuilder;
    private CardData cardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        guaCardView = (WScratchView)findViewById(R.id.guaCardView);
        inputEditText = (EditText)findViewById(R.id.inputEditText);
        textSizeSeekBar = (SeekBar)findViewById(R.id.textSizeSeekBar);
        colorButton = (Button)findViewById(R.id.colorButton);
        createButton = (Button)findViewById(R.id.createButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        cardData = new CardData();
        cardData.setCardColor(Color.RED);
        cardData.setShowString(resultTextView.getText().toString());
        cardData.setShowTextSize(40);

        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               resultTextView.setText(""+s);
                cardData.setShowString(""+s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorPicker();
            }
        });
        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int size =10+progress;
                resultTextView.setTextSize(size);
                cardData.setShowTextSize(size);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GuaActivity.class);
                intent.putExtra("card_Data",cardData);
                startActivity(intent);
                overridePendingTransition(R.anim.intent_start,R.anim.intent_end);

            }
        });


    }












    private void showColorPicker()
    {
        //https://github.com/MrBIMC/VintageChroma
        if (chromaBuilder==null) {
           chromaBuilder = new ChromaDialog.Builder();

        }else {
            chromaBuilder
                    .initialColor(Color.GREEN)
                    .colorMode(ColorMode.ARGB) // RGB, ARGB, HVS, CMYK, CMYK255, HSL
                    .indicatorMode(IndicatorMode.HEX) //HEX or DECIMAL; Note that (HSV || HSL || CMYK) && IndicatorMode.HEX is a bad idea
                    .onColorSelected(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(@ColorInt int color) {
                            colorButton.setBackgroundColor(color);
                            cardData.setCardColor(color);
                        }
                    })
                    .create()
                    .show(getSupportFragmentManager(), "ChromaDialog");
        }
    }



    /*
       做下一頁
       seekbar 問題 版本
       //gua activity
          backonpress

     */

}
