
package com.example.anshul.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

import static com.example.anshul.calculator.R.id.bast;
import static com.example.anshul.calculator.R.id.bclear;
import static com.example.anshul.calculator.R.id.beql;
import static com.example.anshul.calculator.R.id.bmin;
import static com.example.anshul.calculator.R.id.bplu;
import static com.example.anshul.calculator.R.id.bsla;

public class Calculator extends AppCompatActivity {
    private TextView _screen;
    String currentOperator = "";
    private String display;
    private String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _screen = (TextView)findViewById(R.id.editText2);
       // eT2=(EditText)findViewById(R.id.editText2);
        final Button b1=(Button)findViewById(R.id.b1);
        final Button b2=(Button)findViewById(R.id.b2);
        final Button b3=(Button)findViewById(R.id.b3);
        final Button b4=(Button)findViewById(R.id.b4);
        final Button b5=(Button)findViewById(R.id.b5);
        final Button b6=(Button)findViewById(R.id.b6);
        final Button b7=(Button)findViewById(R.id.b7);
        final Button b8=(Button)findViewById(R.id.b8);
        final Button b9=(Button)findViewById(R.id.b9);
        final Button beql=(Button)findViewById(R.id.beql);
        final Button bmin=(Button)findViewById(R.id.bmin);
        final Button bast=(Button)findViewById(R.id.bast);
        final Button bplu=(Button)findViewById(R.id.bplu);
        final Button bsla=(Button)findViewById(R.id.bsla);
        final Button bclear=(Button)findViewById(R.id.bclear);

     //   b8.setOnClickListener(myOn);
        b1.setOnClickListener(myOn);
        b2.setOnClickListener(myOn);
        b3.setOnClickListener(myOn);
        b4.setOnClickListener(myOn);
        b5.setOnClickListener(myOn);
        b6.setOnClickListener(myOn);
        b7.setOnClickListener(myOn);
        b8.setOnClickListener(myOn);
        b9.setOnClickListener(myOn);
        beql.setOnClickListener(myOn);
        bplu.setOnClickListener(myOn);
        bsla.setOnClickListener(myOn);
        bast.setOnClickListener(myOn);
        bmin.setOnClickListener(myOn);
        bclear.setOnClickListener(myOn);
        //onClickNumber(b);
    }

    View.OnClickListener myOn= new View.OnClickListener() {
        public void onClick(View v) {
            if(v.getId()== bmin || v.getId() == bsla || v.getId()== bplu || v.getId()== bast)
                onClickOperator(v);
            else if(v.getId() == beql)
                onClickEqual(v);
            else if(v.getId()==bclear)
                clear();
            else
            onClickNumber(v);

//            if((CharSequence)v=="1")
//            _screen.setText(v);
        }
    };

    private void updateScreen(){
        _screen.setText(display);
    }


    protected void onClickNumber(View V){
        if(result!="")
        {
            clear();
            updateScreen();
        }

        Button b = (Button)V;
        Log.i("qerwe",b.getText().toString());
        if(display==null) {
            display = b.getText().toString();
        }
        else
        {
            display =display+ b.getText().toString();
        }
        updateScreen();
    }

    protected void onClickOperator(View V){

        Button b = (Button)V;
        Log.i("TRTRTR",b.getText().toString());
        if(result != "") {
            String _display = result;
          //  result = "";
            clear();
          //  currentOperator = "";
            display = _display;
        }

        if(currentOperator != "") {
            Log.d("Calc", "" + display.charAt(display.length() - 1));
            if(isOperator(display.charAt(display.length() - 1)))
            display = display.replace(display.charAt(display.length() - 1),b.getText().charAt(0));
            else
            {
                getResult();
                display = result;
                result = "";
            }

        }

        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();

    }

    private boolean isOperator(char x)
    {
        if(x == '+')
            return true;
        else
        if(x=='/')
            return true;
        else
        if(x=='*')
            return true;
        else
        if(x=='-')
            return true;
        else
            return false;

    }
    private void clear(){
        display = "";
        currentOperator = "";
        result = "";
        _screen.setText("0");
    }

    protected void onClickClear(){
        clear();
        updateScreen();
        result = "";
    }

    private double operate(String a, String b, String op){
        Log.i("checkcheck1",a);
        Log.i("checkcheck2",b);
        Log.i("checkcheck3",op);
        switch(op) {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "*":
                return Double.valueOf(a) * Double.valueOf(b);
            case "/":
                try {
                    return Double.valueOf(a) / Double.valueOf(b);
                }
                catch(Exception e){
                    Log.d("Calc",e.getMessage());

            }
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            default:
                return -1;
        }
    }

    private boolean getResult(){
        if(currentOperator == "") return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length < 2) return false;
        result = String.valueOf(operate(operation[0],operation[1],currentOperator));
        return true;
    }

    public void onClickEqual(View V){
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length < 2) {
            _screen.setText(display + "=" + String.valueOf(operation[0]));
         //   return;
        }
        else {
            result = String.valueOf(operate(operation[0], operation[1], currentOperator));
            _screen.setText(display + "=" + String.valueOf(result));
        }

    }

}
