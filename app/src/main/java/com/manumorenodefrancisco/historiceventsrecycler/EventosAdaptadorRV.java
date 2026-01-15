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
        sosten.tvLocation.setText(events.get(position).getEventLocation());
        sosten.tvDate.setText("????");
        sosten.tvDate.setTag(events.get(position).getEventDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class SostenDeVistas extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvLocation;
        CardView card;
        ImageView iconoLibro;
        ImageView iconoCross;
        ImageView iconoCheck;
        int fallos = 0;

        public SostenDeVistas(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvEventName);
            tvDate = itemView.findViewById(R.id.tvEventDate);
            tvLocation = itemView.findViewById(R.id.tvEventLocation);
            card = itemView.findViewById(R.id.eventCard);
            iconoLibro = itemView.findViewById(R.id.imageViewLibro);
            iconoCross = itemView.findViewById(R.id.imageViewCross);
            iconoCheck = itemView.findViewById(R.id.imageViewCheck);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    card.setCardBackgroundColor(
                            itemView.getResources().getColor(R.color.gray)
                    );
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
                    View alertPopUpView = inflater.inflate(R.layout.event_popup, null);
                    builder.setTitle(tvName.getText())
                            .setView(alertPopUpView)
                            .setPositiveButton("Continuar", (dialog, which) -> {

                                TextInputEditText fechaET = alertPopUpView.findViewById(R.id.fechaET);
                                String fechaInput = fechaET.getText().toString();

                                String fecha = tvDate.getTag().toString();
                                String message;

                                if (fechaInput.trim().equals(fecha)) {
                                    message = "ES CORRECTO";
                                    iconoCheck.setVisibility(View.VISIBLE);
                                    //iconoLibro.setColorFilter(itemView.getResources().getColor(R.color.verdecillo));
                                    //card.setCardBackgroundColor(itemView.getResources().getColor(R.color.verdecillo));
                                } else {
                                    message = "HAS FALLADO. La respuesta era: " + fecha;
                                    iconoCross.setVisibility(View.VISIBLE);
                                    //iconoLibro.setColorFilter(itemView.getResources().getColor(R.color.rojillo));
                                    //card.setCardBackgroundColor(itemView.getResources().getColor(R.color.rojillo));
                                }

                                itemView.setOnClickListener(null);

                                AlertDialog.Builder builder2 = new AlertDialog.Builder(itemView.getContext());
                                builder2.setTitle(tvName.getText())
                                        .setMessage(message)
                                        .setPositiveButton("Ok", (dialog2, which2) -> {})
                                        //.setOnDismissListener(dialog2 -> {tvDate.setText(fecha);})
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
