package com.manumorenodefrancisco.historiceventsrecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

public class EventosAdaptadorRV extends RecyclerView.Adapter<EventosAdaptadorRV.SostenDeVistas> {
    Context context;
    ArrayList<EventModel> events;

    public EventosAdaptadorRV(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public EventosAdaptadorRV.@NonNull SostenDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater instanciadorXML = LayoutInflater.from(context);
        View view = instanciadorXML.inflate(R.layout.cv_row, parent, false);

        return new EventosAdaptadorRV.SostenDeVistas(view);
    }

    @Override
    public void onBindViewHolder(EventosAdaptadorRV.@NonNull SostenDeVistas sosten, int position) {
        sosten.tvName.setText(events.get(position).getEventName());
        sosten.tvDate.setText(events.get(position).getEventDate());
        sosten.tvLocation.setText(events.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class SostenDeVistas extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvLocation;
        public SostenDeVistas(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvEventDate);
            tvLocation = itemView.findViewById(R.id.tvEventLocation);
        }
    }
}
