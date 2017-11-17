package com.choncoder.caloriecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    private Button buttonStartDay;
    private EditText editTextTotalForDay;
    private Button buttonAddFood;
    private Button buttonRemoveFoodItem;
    private Button buttonAddFoodItem;
    private Button buttonRemoveFood;
    private Button buttonStartNewDay;
    private EditText addCalorieEditText;
    private EditText removeCaloriesEditText;
    private TextView calorieTotalRemove;
    private TextView calorieTotalAdd;
    private TextView calorieTotal;
    private int caloriesForDay;
    private int calorieOriginal;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_background);
        buttonStartDay = findViewById(R.id.buttonBegin);
        editTextTotalForDay = findViewById(R.id.editTextCalorieTotalForDay);
        buttonAddFood = findViewById(R.id.buttonAddFood);
        buttonRemoveFood = findViewById(R.id.buttonRemoveFood2);
        buttonStartNewDay = findViewById(R.id.buttonNewDay);
        flipper = findViewById(R.id.viewFlipper);
        addCalorieEditText = findViewById(R.id.addCalorieEditText);
        removeCaloriesEditText = findViewById(R.id.subtractCaloriesEditText);
        buttonRemoveFoodItem = findViewById(R.id.buttonRemoveFoodItem);
        buttonAddFoodItem = findViewById(R.id.buttonAddFoodItem);
        calorieTotal = findViewById(R.id.calorieTotal);
        calorieTotalRemove = findViewById(R.id.calorieTotalRemove);
        calorieTotalAdd = findViewById(R.id.calorieTotalAdd);


    }

    public void beginDay(View view) {
        flipper.showNext();
        caloriesForDay = Integer.valueOf(editTextTotalForDay.getText().toString());
        calorieOriginal = caloriesForDay;
        calorieTotal.setText(caloriesForDay + "");
    }



    public void flipNextOne(View view) {
        calorieTotalRemove.setText(caloriesForDay + "");
        flipper.showNext();
    }


    public void flipNextTwo(View view){
        calorieTotalAdd.setText(caloriesForDay + "");
        flipper.showNext();
        flipper.showNext();
    }

    public void addCalories(View view){
        int calories = 0;
        try{
            calories = Integer.parseInt(addCalorieEditText.getText().toString());
        } catch (NumberFormatException e){
            flipper.showPrevious();
            flipper.showPrevious();
        }

        caloriesForDay -= calories;
        calorieTotal.setText(caloriesForDay + "");
        calorieTotalRemove.setText(caloriesForDay + "");
        calorieTotalAdd.setText(caloriesForDay + "");

        flipper.showPrevious();
        flipper.showPrevious();
        if (caloriesForDay > 0) {
            toast = Toast.makeText(this, calorieTotal.getText() + " more calories to go!", Toast.LENGTH_LONG);
        } else if (caloriesForDay == 0){
            toast = Toast.makeText(this, "You have reached your daily goal!", Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(this, "You have exceeded your calorie limit!", Toast.LENGTH_LONG);
        }
        addCalorieEditText.setText("");
        toast.show();
    }

    public void subtractCalories(View view) {
        int calories = 0;
        try {
            calories += Integer.valueOf(removeCaloriesEditText.getText().toString());
        } catch (NumberFormatException e) {
            flipper.showPrevious();
        }

            caloriesForDay += calories;
            if (caloriesForDay > calorieOriginal) {
                calorieTotal.setText(calorieOriginal + "");
                calorieTotalRemove.setText(calorieOriginal + "");
                calorieTotalAdd.setText(calorieOriginal + "");
            } else {
                calorieTotal.setText(caloriesForDay + "");
                calorieTotalRemove.setText(caloriesForDay + "");
                calorieTotalAdd.setText(caloriesForDay + "");
            }

        flipper.showPrevious();

        removeCaloriesEditText.setText("");

    }

    public void newDay(View view) {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
