package com.corina.android.lab4_2_pam.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.corina.android.lab4_2_pam.R;

/**
 * Created by Corina on 9.12.2017.
 */

public class DoctorAdapter extends BaseAdapter {
    private final String[] point;
    private final String[] special;
    private final String[] address;
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;

    public DoctorAdapter(Context c, String[] web, String[] point, String[] address, String[] special, int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        this.point = point;
        this.special = special;
        this.address = address;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.doctor_item, null);
            TextView txtDoctorName = (TextView) grid.findViewById(R.id.txtDoctorName);
            TextView txtDoctorLoc = (TextView) grid.findViewById(R.id.txtDoctorLoc);
            TextView txtDocPoint = (TextView) grid.findViewById(R.id.txtDocPoint);
            TextView txtDoctorSp = (TextView) grid.findViewById(R.id.txtDoctorSp);
            ImageView imageView = (ImageView)grid.findViewById(R.id.imageView7);
            txtDoctorName.setText(web[position]);
            txtDocPoint.setText(point[position]);
            txtDoctorSp.setText(special[position]);
            txtDoctorLoc.setText(address[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}

