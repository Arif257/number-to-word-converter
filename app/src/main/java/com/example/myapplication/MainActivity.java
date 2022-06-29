package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;

    private static final String[] tensNames = {
            "",
            "Ten",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "ninety"
    };

    private static final String[] numNames = {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(convert(parseInt(editText.getText().toString())));

            }
        });
    }

    private static String convertLessThanOneThousand(int number){
        String soFar;
        if (number % 100 < 20){
            soFar = numNames[number % 10];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;

        }
        if (number == 0) return soFar;
        return numNames[number] + "hundred" + soFar;
    }
    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0){
            return "zero";
        }
        String snumber = Long.toString(number);
        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnn
        int billions = parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions = parseInt(snumber.substring(3,6));
        // nnnnnnnXXXnnnn
        int hundredThousands = parseInt(snumber.substring(6,9));
        //nnnnnnnnnnXXX
        int thousands = parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions){
            case 0:
                tradBillions = "";
                break;
            case 1:
                tradBillions = convertLessThanOneThousand(billions) + "billions";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions) + "billions";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions){
            case 0:
                tradMillions = "";
                break;
            case 1:
                tradMillions = convertLessThanOneThousand(millions) + "millions";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions) + "millions";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands){
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = " one thousand";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + "thousand";
        }
        result = result + tradHundredThousands;

        String tradThousand;

        tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        // remove extra spaces
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");





    }



}