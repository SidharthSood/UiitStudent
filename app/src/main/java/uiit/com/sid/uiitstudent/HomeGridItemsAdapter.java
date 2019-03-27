package uiit.com.sid.uiitstudent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import uiit.com.sid.uiitstudent.Helper.Console;
import uiit.com.sid.uiitstudent.Properties.HomeGridItem;

public class HomeGridItemsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HomeGridItem> homeGridItems;
    private LayoutInflater inflater;

    public HomeGridItemsAdapter(Context context, ArrayList<HomeGridItem> homeGridItems) {
        this.context = context;
        this.homeGridItems = homeGridItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return homeGridItems.size();
    }

    @Override
    public Object getItem(int position) {
        return homeGridItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item_icon_layout,parent,false);
        }

        final HomeGridItem homeGridItem = (HomeGridItem) this.getItem(position);

        ImageView gridItemImage = convertView.findViewById(R.id.grid_item_image);
        TextView gridItemTitle = convertView.findViewById(R.id.grid_item_text);

        gridItemImage.setImageResource(homeGridItem.getImage());
        gridItemTitle.setText(homeGridItem.getTitle());

        return convertView;
    }
}
