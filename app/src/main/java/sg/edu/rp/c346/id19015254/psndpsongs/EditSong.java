package sg.edu.rp.c346.id19015254.psndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditSong extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rg;
    RadioButton r1, r2, r3, r4, r5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rg = findViewById(R.id.rg);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.get_id()));
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        if (data.getStar() == 1)
        {
            r1.setChecked(true);
        }
        else if (data.getStar() == 2)
        {
            r2.setChecked(true);
        }
        else if (data.getStar() == 3)
        {
            r3.setChecked(true);
        }
        else if (data.getStar() == 4)
        {
            r4.setChecked(true);
        }
        else if (data.getStar() == 5)
        {
            r5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                data.set_id(Integer.parseInt(etID.getText().toString()));
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

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

                data.setStars(stars);
                dbh.updateSong(data);
                dbh.close();
                Intent i = new Intent(EditSong.this, SongList.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.get_id());
                Intent i = new Intent(EditSong.this, SongList.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditSong.this,
                        SongList.class);
                startActivity(i);
            }
        });
    }
}