package com.hatcher.app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hatcher.app.R;
import com.hatcher.app.ui.PersonInfoActivity;
import com.hatcher.app.ui.RankListActivity;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> listitem;

    public GridViewAdapter(Context context,List<Map<String, Object>> listitem) {
        this.context = context;
        this.listitem = listitem;
    }

    @Override
    public int getCount() {
        return listitem.size();
    }

    @Override
    public Object getItem(int position) {
        return listitem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_function, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_function_image);
        TextView textView = (TextView) convertView.findViewById(R.id.item_function_text);

        final Map<String, Object> map = listitem.get(position);
        final int temp = position;
        imageView.setImageResource((Integer) map.get("image"));
//        imageView.setBackgroundResource((Integer) map.get("image"));
        textView.setText(map.get("text") + "");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp == 0)
                {
                    context.startActivity(new Intent(context, PersonInfoActivity.class));
                }
                else if (temp == 1)
                {
                    context.startActivity(new Intent(context, RankListActivity.class));

                }
                Toast.makeText(context,map.get("text") + "",Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        return convertView;
    }

}
