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
    ArrayList<Grade> c347, c302;
    String module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module__info);

        Intent i = getIntent();
        module = i.getStringExtra("module");

        btnAdd = findViewById(R.id.buttonAdd);
        btnEmail = findViewById(R.id.buttonEmail);
        btnInfo = findViewById(R.id.buttonInfo);

        lvInfo = findViewById(R.id.lvWeek);

        c347 = new ArrayList<Grade>();
        c347.add(new Grade("Week 1", "B"));
        c347.add(new Grade("Week 2", "C"));
        c347.add(new Grade("Week 3", "A"));

        c302 = new ArrayList<Grade>();
        c302.add(new Grade("Week 1", "A"));
        c302.add(new Grade("Week 2", "B"));
        c302.add(new Grade("Week 3", "C"));

        if (module.equalsIgnoreCase("c347")) {
            aa = new CustomAdapter(this, R.layout.row, c347);
        }else{
            aa = new CustomAdapter(this, R.layout.row, c302);
        }
        lvInfo.setAdapter(aa);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpIntent = new Intent(Intent.ACTION_VIEW);
                // Set the URL to be used.
                rpIntent.setData(Uri.parse("https://www.rp.edu.sg/schools-courses/courses/full-time-diplomas/full-time-courses/modules/index/" + module));
                startActivity(rpIntent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = "Hi faci,\nI am ...\nPlease see my remarks so far, thank you!\n\n";
                if (module.equalsIgnoreCase("c347")) {
                    for (int i = 0; i < c347.size(); i++) {
                        msg += c347.get(i).getWeek() + ": DG: " + c347.get(i).getGrade() + "\n";
                    }
                }else{
                    for (int i = 0; i < c302.size(); i++) {
                        msg += c302.get(i).getWeek() + ": DG: " + c302.get(i).getGrade() + "\n";
                    }
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
                Integer num=0;
                if (module.equalsIgnoreCase("c347")) {
                    num = c347.size() + 1;
                }else{
                    num = c302.size() + 1;
                }
                String week = "Week " + num.toString();
                i.putExtra("week", week);
                startActivityForResult(i, requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Integer num;
        if (module.equalsIgnoreCase("c347")) {
            num = c347.size() + 1;
        }else{
            num = c302.size() + 1;
        }

        if (resultCode == RESULT_OK) {
            if (data != null) {
                // Get data passed back from 2nd activity
                String grade = data.getStringExtra("grade");
                if (module.equalsIgnoreCase("c347")) {
                    c347.add(new Grade("Week " + num.toString(), grade));
                }else{
                    c302.add(new Grade("Week " + num.toString(), grade));
                }
                aa.notifyDataSetChanged();
            }
        }
    }
}
