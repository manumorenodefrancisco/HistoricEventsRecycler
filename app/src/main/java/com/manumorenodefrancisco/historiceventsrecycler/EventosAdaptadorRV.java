package com.manumorenodefrancisco.historiceventsrecycler;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
    public @NonNull SostenDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater instanciadorXML = LayoutInflater.from(context);
        View view = instanciadorXML.inflate(R.layout.cv_row, parent, false);

        return new SostenDeVistas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SostenDeVistas sosten, int position) {
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
        CardView card;
        ImageView iconoLibro;
        ImageView iconoCheck;
        ImageView iconoCross;

        public SostenDeVistas(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvEventDate);
            tvLocation = itemView.findViewById(R.id.tvEventLocation);
            card = itemView.findViewById(R.id.eventCard);
            iconoLibro = itemView.findViewById(R.id.imageViewLibro);
            iconoCheck = itemView.findViewById(R.id.imageViewCheck);
            iconoCross = itemView.findViewById(R.id.imageViewCross);
            //String nombre = itemView.findViewById(R.id.nombreTIL);
            //String año = itemView.findViewById(R.id.añoTIL).getText().toString();

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), tvName.getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setCardBackgroundColor(
                            itemView.getResources().getColor(R.color.gray)
                    );
                    //EventModel evento = events;
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
                    View alertPopUpView = inflater.inflate(R.layout.event_popup, null);
                    builder.setTitle(tvName.getText())
                            .setView(alertPopUpView)
                            //.setMessage("¿En qué año crees que ocurrió este evento?")
                            .setPositiveButton("Continuar", (dialog, which) -> {
                                //evento.remove(position);
                                //evento.incrementarFallos();

                                TextInputEditText fechaET = alertPopUpView.findViewById(R.id.fechaET);
                                String userInput = fechaET.getText().toString();

                                //TextInputLayout fechaTIL = alertPopUpView.findViewById(R.id.fechaTIL);
                                //TextInputEditText fechaET = (TextInputEditText) fechaTIL.getEditText();

                                String fecha = fechaET != null ? fechaET.getText().toString() : null;
                                String message;

                                if (fecha.trim().equals(tvDate.getText().toString())) {
                                    //Toast.makeText(itemView.getContext(), "Año " + fecha + " ES CORRECTO!!", Toast.LENGTH_SHORT).show();
                                    message = "ES CORRECTO";
                                    //card.setCardBackgroundColor(itemView.getResources().getColor(R.color.verdecillo));
                                    //iconoLibro.setColorFilter(itemView.getResources().getColor(R.color.verdecillo));

                                    iconoCheck.setVisibility(View.VISIBLE);

                                } else {
                                    message = "Has fallado :/";
                                    //iconoLibro.setColorFilter(itemView.getResources().getColor(R.color.rojillo));
                                    iconoCross.setVisibility(View.VISIBLE);
                                    //card.setCardBackgroundColor(itemView.getResources().getColor(R.color.rojillo));
                                }
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(itemView.getContext());
                                builder2.setTitle(tvName.getText())
                                        .setMessage(message)
                                        .setPositiveButton("Aceptar", (dialog2, which2) -> {})
                                        .setNegativeButton("Cancelar", (dialog2, which2) -> {})
                                        .show();

                            })
                            .setNegativeButton("Cancelar", (dialog, which) -> {

                            })
                            .show();
                }
            });

        }
}
}