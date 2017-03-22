package com.example.lee.guaguacard;

import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by Lee on 2017/3/22.
 */

public class CardData implements Serializable
{

    private int cardColor;

    public int getCardColor() {
        return cardColor;
    }

    public void setCardColor(int cardColor) {
        this.cardColor = cardColor;
    }

    private String showString ;
    private int showTextSize;

    public String getShowString() {
        return showString;
    }

    public void setShowString(String showString) {
        this.showString = showString;
    }

    public int getShowTextSize() {
        return showTextSize;
    }

    public void setShowTextSize(int showTextSize) {
        this.showTextSize = showTextSize;
    }
}
