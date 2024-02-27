package com.example.easy_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutinTv;
    MaterialButton buttonC, buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button9, button8, button7, button6, button5, button4,button3, button2, button1, button0;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutinTv = findViewById(R.id.solution_tv);

        assignId(button0,R.id.button_0);
        assignId(button0,R.id.button_1);
        assignId(button0,R.id.button_2);
        assignId(button0,R.id.button_3);
        assignId(button0,R.id.button_4);
        assignId(button0,R.id.button_5);
        assignId(button0,R.id.button_6);
        assignId(button0,R.id.button_7);
        assignId(button0,R.id.button_8);
        assignId(button0,R.id.button_9);
        assignId(button0,R.id.button_0);
        assignId(button0,R.id.button_dot);
        assignId(button0,R.id.button_divide);
        assignId(button0,R.id.button_multiply);
        assignId(button0,R.id.button_plus);
        assignId(button0,R.id.button_minus);
        assignId(button0,R.id.button_c);
        assignId(button0,R.id.button_ac);
        assignId(button0,R.id.button_close_bracket);
        assignId(button0,R.id.button_open_bracket);
        assignId(button0,R.id.button_minus);
        assignId(button0,R.id.button_equals);


    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();
        String dataToCalculate = solutinTv.getText().toString();

        if(btnText.equals("AC")){
            solutinTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(btnText.equals("=")){
            solutinTv.setText(resultTv.getText());
            resultTv.setText("");
            return;
        }
        if (btnText.equals("C")){
            if(dataToCalculate.length()>0){
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }
        }
        else
        {
            dataToCalculate = dataToCalculate + btnText;
        }
        solutinTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}