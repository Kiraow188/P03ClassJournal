package sg.edu.rp.c347.p03classjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Module> modules;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lvModules);

        modules = new ArrayList<Module>();
        modules.add(new Module("C347", "Android Programming II"));
        modules.add(new Module("C302", "Web Services"));

        aa = new ModuleAdapter(this, R.layout.module, modules);

        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, Module_Info.class);
                i.putExtra("module", modules.get(position).getCode());
                startActivity(i);
            }

        });


    }
}
