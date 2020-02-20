package com.app.datajson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    public ArrayList<SingleRowCreator> getList() {
        return list;
    }

    private ArrayList<SingleRowCreator> list = new ArrayList<>();
    private Context context;
    private ImageView imageView;
    private GlobalDataController globalDataController;

    CustomAdapter(Context context) {
        this.context = context;

        globalDataController = MainActivity.getGlobalDataController();
        GlobalDataModel m = globalDataController.getModel();

        for (int i = 0; i < globalDataController.getModel().getTitle().size(); i++) {

            if (m.getTitle().get(i).equals("null") || m.getTitle().get(i).equals("") ) {
                continue;
            }
            if(m.getDescription().get(i) == "null" || m.getDescription().get(i) == "") {
                list.add(new SingleRowCreator(m.getTitle().get(i), "", m.getUrl().get(i)));
            }
            else {
                list.add(new SingleRowCreator(m.getTitle().get(i), m.getDescription().get(i), m.getUrl().get(i)));
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lft = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = lft.inflate(R.layout.singlerow, (ViewGroup) parent, false);

        TextView text = row.findViewById(R.id.title1);
        TextView desc = row.findViewById(R.id.subTitle);
        imageView = row.findViewById(R.id.thumb);

        SingleRowCreator tmp = list.get(position);
        text.setText(tmp.getTitle());
        desc.setText(tmp.getDescription());
        Picasso.with(context).load(tmp.getUrl()).into(imageView);
        return row;
    }
}