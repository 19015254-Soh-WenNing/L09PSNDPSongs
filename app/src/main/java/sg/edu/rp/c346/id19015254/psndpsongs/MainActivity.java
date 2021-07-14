package sg.edu.rp.c346.id19015254.psndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    RadioButton r1, r2, r3, r4, r5;
    Button btnInsert, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnInsert = findViewById(R.id.btnInsert);
        btnList = findViewById(R.id.btnList);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stars = 0;

                if (r1.isChecked())
                {
                    stars = 1;
                }
                else if (r2.isChecked())
                {
                    stars = 2;
                }
                else if (r3.isChecked())
                {
                    stars = 3;
                }
                else if (r4.isChecked())
                {
                    stars = 4;
                }
                else if (r5.isChecked())
                {
                    stars = 5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(etTitle.getText().toString(), etSingers.getText().toString(), etYear.getText().toString(), String.valueOf(stars));

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SongList.class);
                startActivity(i);
            }
        });
    }
}