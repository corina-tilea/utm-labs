package com.corina.android.lab4_2_pam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.corina.android.lab4_2_pam.util.DoctorAdapter;


/**
 * Created by Corina on 12.12.2017.
 */

public class DoctorsActivity extends Activity {

    GridView grid;
    String[] web = {
            "Doctor nr.1",
            "Doctor nr.2",
            "Doctor nr.3",
    } ;
    String[] point = {
            "5.0",
            "4.8",
            "4.3",
    } ;

    String[] address = {
            "Doctor 1 speciality",
            "Doctor 2 speciality",
            "Doctor 3 speciality",
    } ;
    String[] special = {
            "Doctor 1 address",
            "Doctor 2 address",
            "Doctor 3 address",
    } ;
    int[] imageId = {
            R.drawable.doc1,
            R.drawable.doc2,
            R.drawable.doc3,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        DoctorAdapter adapter = new DoctorAdapter(DoctorsActivity.this, web,point,special,address, imageId);
        grid = (GridView) findViewById(R.id.doctorlist);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DoctorsActivity.this, "You Selected  " + web[+position], Toast.LENGTH_SHORT).show();

            }
        });

    }}

