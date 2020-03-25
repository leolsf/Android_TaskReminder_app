package hk.hkucs.reminder_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/***
 * Created by Leo on 3/19/2020
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button button_Add, button_Calendar, button_Chat;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        button_Add = (Button)findViewById(R.id.button_Add);
        button_Calendar = (Button)findViewById(R.id.button_Calendar1);
        button_Chat = (Button)findViewById(R.id.button_Chat1);
        mDatabaseHelper = new DatabaseHelper(this);

        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
            }
        });

        button_Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData) {
            toastMessage("Data inserted successfully :)");
        } else {
            toastMessage("Something went wrong :(");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
