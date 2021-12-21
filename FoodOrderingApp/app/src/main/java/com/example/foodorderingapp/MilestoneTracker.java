package com.example.foodorderingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class MilestoneTracker extends Fragment {


    String firebasebill;
    TextView bill, milestonedisp;
    ProgressBar progressBar;
    String reference = "reference_firebase_node";
    public MilestoneTracker() {
        // Required empty public constructor
    }


    public static MilestoneTracker newInstance(String param1, String param2) {
        MilestoneTracker fragment = new MilestoneTracker();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_milestone_tracker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bill = (TextView) view.findViewById(R.id.bill);
        milestonedisp = (TextView) view.findViewById(R.id.milestonedisp);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(reference);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                firebasebill = snapshot.getValue(String.class);
                bill.setText("₹"+firebasebill);
                String milestonemsg = getMilestoneTracker(Integer.parseInt(firebasebill));
                milestonedisp.setText(milestonemsg);
                int tocheck = Integer.parseInt(firebasebill);
                if(tocheck<2000){
                    double val = tocheck/2000.0;
                    int value = (int) (val*100);
                    progressBar.setProgress(value);

                }
                else if(tocheck>=2000&&tocheck<5000){
                    double val = (tocheck-2000)/3000.0;
                    int value = (int) (val*100);
                    Log.i("bar", String.valueOf(value));

                    progressBar.setProgress(value);
                }
                else if(tocheck>=5000&&tocheck<8000){
                    double val = (tocheck-5000)/3000.0;
                    int value = (int) (val*100);
                    Log.i("bar", String.valueOf(value));

                    progressBar.setProgress(value);
                }
                else if(tocheck>=8000&&tocheck<10000){
                    double val = (tocheck-8000)/2000.0;
                    int value = (int) (val*100);
                    Log.i("bar", String.valueOf(value));

                    progressBar.setProgress(value);
                }
                else {
                    progressBar.setProgress(100);
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    public String getMilestoneTracker(int value){
        if(value<2000){
            return "No Milestones yet.";
        }
        else if(value>=2000&&value<5000){
            return "Milestone 1";
        }
        else if(value>=5000&&value<8000){
            return "Milestone 2";
        }
        else if(value>=8000&&value<10000){
            return "Milestone 3";
        }
        else{
            return "Milestone 4";
        }
    }
}