package com.example.androidinterntaskshubhamkumardubey;

import android.content.Context;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CoustomAdaptor  extends RecyclerView.Adapter<CoustomAdaptor.MyViewHolder> {

    private Context context ;
    private ArrayList make_id, car_model;

    CoustomAdaptor(Context context,
                   ArrayList make_id,
                   ArrayList car_model){
        this.context = context;
        this.make_id = make_id;
        this.car_model = car_model;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.carMake.setText(String.valueOf(make_id.get(position)));
        holder.carModel.setText(String.valueOf(car_model.get(position)));
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper mydb = new MyDatabaseHelper(context);
                mydb.DeleteData(String.valueOf(make_id.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return make_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView carMake,carModel;
        Button delete_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            carMake = itemView.findViewById(R.id.car_Make);
            carModel = itemView.findViewById(R.id.car_model);
            delete_btn = itemView.findViewById(R.id.deleteButton);


        }
    }
}
