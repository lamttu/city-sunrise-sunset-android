package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LocationRowAdapter extends RecyclerView.Adapter<LocationRowAdapter.ViewHolder> {
    private ArrayList<AULocation> locations;
    private Context context;

    public LocationRowAdapter(ArrayList<AULocation> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.location_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationRowAdapter.ViewHolder holder, final int position) {
        final AULocation location =  locations.get(position);
        TextView cityTextView = holder.cityTV;
        cityTextView.setText(location.getCityName());
        TextView locationTextView = holder.locationTV;
        locationTextView.setText("Timezone: GMT " + location.getTimezone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeatherActivity.class);
                intent.putExtra("location", locations.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView cityTV;
        public TextView locationTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityTV = itemView.findViewById(R.id.cityName);
            locationTV = itemView.findViewById(R.id.timezone);
        }
    }
}
