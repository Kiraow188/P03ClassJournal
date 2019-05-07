package sg.edu.rp.c347.p03classjournal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Module_Info extends AppCompatActivity {

    int requestCode = 1;

    Button btnAdd, btnInfo, btnEmail;
    ArrayAdapter aa;
    ListView lvInfo;
    ArrayList<Grade> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module__info);

        btnAdd = findViewById(R.id.buttonAdd);
        btnEmail = findViewById(R.id.buttonEmail);
        btnInfo = findViewById(R.id.buttonInfo);

        lvInfo = findViewById(R.id.lvWeek);

        grades = new ArrayList<Grade>();
        grades.add(new Grade("Week 1", "B"));
        grades.add(new Grade("Week 2", "C"));
        grades.add(new Grade("Week 3", "A"));

        aa = new CustomAdapter(this, R.layout.row, grades);
        lvInfo.setAdapter(aa);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                // Set the URL to be used.
                rpIntent.setData(Uri.parse("http://www.rp.edu.sg"));
                startActivity(rpIntent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = "Hi faci,\nI am ...\nPlease see my remarks so far, thank you!\n\n";
                for(int i=0; i<grades.size(); i++){
                    msg+= grades.get(i).getWeek() + ": DG: " + grades.get(i).getGrade()+"\n";
                }
                // The action you want this intent to do;
                // ACTION_SEND is used to indicate sending text
                Intent email = new Intent(Intent.ACTION_SEND);
                // Put essentials like email address, subject & body text
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Daily Grades Remarks");
                email.putExtra(Intent.EXTRA_TEXT, msg);
                // This MIME type indicates email
                email.setType("message/rfc822");
                // createChooser shows user a list of app that can handle
                // this MIME type, which is, email
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Module_Info.this, Add_Daily_Grade.class);
                Integer num = grades.size() + 1;
                String week = "Week " + num.toString();
                i.putExtra("week", week);
                startActivityForResult(i, requestCode);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Integer num = grades.size() + 1;

        if (resultCode == RESULT_OK) {
            if (data != null) {
                // Get data passed back from 2nd activity
                String grade = data.getStringExtra("grade");
                grades.add(new Grade("Week " + num.toString(), grade));

                aa.notifyDataSetChanged();

            }
        }

    }
}
