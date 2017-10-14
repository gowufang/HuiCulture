package ahu.bigdata.huiculture.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.module.cloud.GridItem;
import ahu.bigdata.huiculture.utils.ImageLoaderManager;

/**
 * Created by YCH on 2017/10/14.
 * Function:
 */
public class GridViewAdapter extends ArrayAdapter <GridItem>{

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();


    public GridViewAdapter(Context context, int resource, ArrayList<GridItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResourceId = resource;
        this.mGridData = objects;
    }

    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt_item);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GridItem item = mGridData.get(position);
        holder.textView.setText(item.getTitle());
//        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        ImageLoaderManager.getInstance(mContext).displayImage(holder.imageView,item.getImage());
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

}
