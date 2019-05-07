package sg.edu.rp.c347.p03classjournal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Add_Daily_Grade extends AppCompatActivity {

    TextView tvWeek,tvDg;
    RadioGroup rg;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__daily__grade);

        tvDg = findViewById(R.id.textView3);
        tvWeek = findViewById(R.id.tvWeek);
        btnSubmit = findViewById(R.id.buttonSubmit);
        rg = findViewById(R.id.rg);
        tvDg.setTextColor(Color.YELLOW);

        Intent i = getIntent();

        tvWeek.setBackgroundColor(Color.BLUE);
        String week = i.getStringExtra("week");
        tvWeek.setText(week);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selected);
                String grade = rb.getText().toString();

                //Create intent & pass in String data
                Intent i = new Intent();
                i.putExtra("grade", grade);
                // Set result to RESULT_OK to indicate normal
                // response and pass in the intent containing the
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
