package bus.notifier.busnotifier.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import bus.notifier.busnotifier.Models.Bus;
import bus.notifier.busnotifier.R;

public class BusAdapter extends ArrayAdapter<Bus> {

    private SharedPreferences preferences;

    public BusAdapter(@NonNull Context context, ArrayList<Bus> bus_list) {
        super(context, 0, bus_list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bus bus = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_buses, parent, false);
        }
        TextView busName = (TextView) convertView.findViewById(R.id.name_bus);
        ImageButton ringIcon = (ImageButton) convertView.findViewById(R.id.ring_button);
        busName.setText(bus.getNumber());
        ringIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }
}
