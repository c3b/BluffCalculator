package fr.sebastiencaumes.bluffcalculator;

import android.graphics.Color;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by sebastien on 16/09/15.
 */
public class CustomCalc {


    //go to percentages
    public static Float goPercent(Float nbToConvert){
        return nbToConvert*=100;

    }

    //cut decimals
    public static Float cutDecimal(float nbToCut) {

        return (float)Math.round(nbToCut * 10) / 10;
    }

    //set the background color in green if positive and red if negative

    public static void setBack (EditText editText){
        Float numEditText = Float.parseFloat(editText.getText().toString());
        if (numEditText < 0){
            editText.setBackgroundColor(Color.rgb(225,0,0));
        }
        if (numEditText > 0){
            editText.setBackgroundColor(Color.rgb(0,225,0));
        }
    }
}
