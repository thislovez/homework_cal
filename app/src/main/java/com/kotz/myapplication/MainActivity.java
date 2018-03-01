package com.kotz.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends Activity {

    private TextView result;

    private String operand;

    private String operator;

    private Set<String> numbers;

    private Set<String> operators;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.result);
    }

    /**
     * Initialization of set of number values.
     */
    private void initNumbers() {
        numbers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Integer.toString(i));
        }
    }

    /**
     * Initialization of set of operators.
     */
    private void initOperators() {
        operators = new HashSet<String>();
        String[] ops = { "+", "-", "*", "/" };
        for (String operator : ops) {
            operators.add(operator);
        }
    }

    /**
     * Button click event handler.
     *
     * @param view clicked button
     * @return void
     */
    public void handleClick(View view) {
        Button clicked = (Button) view;
        String label = clicked.getText().toString();
        String display = result.getText().toString();

        if (isClear(label)) {
            result.setText(R.string.result_default);
        } else if (isNumerical(label)) {
            if (isDefaultResult(display) || isOperator(display)) {
                result.setText(label);
            } else {
                result.setText(display + label);
            }
        } else if (isOperator(label)) {
            operator = label;
            operand = display;
            result.setText(label);
        } else if (label.equals("=")) {
            double a, b, c;

            if (operator == null || operand == null) {
                return;
            }

            try {
                a = Double.parseDouble(operand);
                b = Double.parseDouble(display);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            if (operator.equals("+")) {
                c = a + b;
            } else if (operator.equals("-")) {
                c = a - b;
            } else if (operator.equals("*")) {
                c = a * b;
            } else  {
                c = a / b;
            }

            operand = Double.toString(c);
            result.setText(operand);
        }
    }

    /**
     * Test if value is the same as clear button's.
     *
     * @param value button value
     * @return true if button is clear button
     */
    private boolean isClear(String value) {
        return value.equals(getString(R.string.buttonClear));
    }

    /**
     * Test if value is operator.
     *
     * @param value
     *            button value
     * @return true if value is operator
     */
    private boolean isOperator(String value) {
        if (operators == null) {
            initOperators();
        }
        return operators.contains(value);
    }

    /**
     * Test if result was modified.
     *
     * @param value result value
     * @return true if result is default
     */
    private boolean isDefaultResult(String value) {
        return value.equals(getString(R.string.result_default));
    }

    /**
     * Test if value is numerical.
     *
     * @param value button's value
     * @return true if value is numerical
     */
    private boolean isNumerical(String value) {
        if (numbers == null) {
            initNumbers();
        }
        return numbers.contains(value);
    }

}