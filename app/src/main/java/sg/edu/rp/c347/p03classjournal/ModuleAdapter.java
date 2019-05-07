package sg.edu.rp.c347.p03classjournal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ModuleAdapter extends ArrayAdapter<Module> {
    private ArrayList<Module> module;
    private Context context;
    private TextView tvCode, tvModule;

    public ModuleAdapter(Context context, int resource, ArrayList<Module> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        module = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.module, parent, false);

        // Get the TextView object
        tvModule = rowView.findViewById(R.id.tvName);
        tvCode = rowView.findViewById(R.id.tvCode);

        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Module currentModule = module.get(position);
        // Set the TextView to show the food

        tvModule.setText(currentModule.getName());
        tvCode.setText(currentModule.getCode());
        //tvCode.setTextColor(Color.BLUE);
        // Return the nicely done up View to the ListView
        return rowView;
    }
}
