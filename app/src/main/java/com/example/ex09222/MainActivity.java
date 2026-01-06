package com.example.ex09222;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder builder1, builder2, builder3, builder4;
    TextView tv;
    final String[] activityList = {"Movie", "restaurant", "concert"};
    final String[] addonesList = {"snacks", "money", "bag" , "phone"};

    LinearLayout main;
    String activityName;
    String selectedAddonsString;
    String nameString;
    boolean[] checkedAddons;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        builder1 = new AlertDialog.Builder(this);
        builder2 = new AlertDialog.Builder(this);
        builder3 = new AlertDialog.Builder(this);
        builder4 = new AlertDialog.Builder(this);
        activityName = "";
        checkedAddons = new boolean[addonesList.length];
        selectedAddonsString = "";
        nameString = "";
        tv = findViewById(R.id.tv);
    }

    private void updateTextView() {
        String textToShow = activityName + "\n" + selectedAddonsString + "\n" + nameString;
        tv.setText(textToShow);
    }

    public void chooseActivity(View v) {
        builder1.setTitle("Choose Activity");
        builder1.setCancelable(true);
        builder1.setItems(activityList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activityName = activityList[which];
                switch (which){
                    case 0:
                            main.setBackgroundColor(Color.RED);
                            break;
                    case 1:
                            main.setBackgroundColor(Color.BLUE);
                            break;
                    case 2:
                            main.setBackgroundColor(Color.YELLOW);
                        break;
                }
                updateTextView();
            }
        });
        AlertDialog alert = builder1.create();
        alert.show();
    }

    public void chooseAddones(View v){
        builder2.setTitle("Choose addons for " + activityName);
        builder2.setMultiChoiceItems(addonesList, checkedAddons, new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedAddons[which] = isChecked;
        }});
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < addonesList.length; i++) {
                            if (checkedAddons[i]) {
                                sb.append(addonesList[i]).append(", ");
                            }
                        }
                        if (sb.length() > 0) {
                            sb.setLength(sb.length() - 2);
                        }
                        selectedAddonsString = sb.toString();
                        updateTextView();
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder2.create();
        alert.show();
    }

    public void chooseName(View v) {
        builder3.setTitle("Choose name");
        final EditText editText = new EditText(this);
        editText.setHint("enter your name");
        builder3.setView(editText);
        builder3.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editText.getText().toString();
                if (!name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "have fun " + name, Toast.LENGTH_SHORT).show();
                    nameString = name;
                    updateTextView();
                }
            }
        });
        AlertDialog alert = builder3.create();
        alert.show();
    }
    
    public void reset(View v){
        builder4.setTitle("reset");
        builder4.setMessage("are you sure you want to reset?");
        builder4.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activityName = "";
                selectedAddonsString = "";
                nameString = "";
                main.setBackgroundColor(Color.WHITE);
                checkedAddons = new boolean[addonesList.length];
                updateTextView();
            }
        });
        builder4.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder4.create();
        alert.show();

    }
}
