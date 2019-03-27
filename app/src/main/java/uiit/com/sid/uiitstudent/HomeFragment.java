package uiit.com.sid.uiitstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import uiit.com.sid.uiitstudent.Helper.Console;
import uiit.com.sid.uiitstudent.Properties.HomeGridItem;

public class HomeFragment extends Fragment {

    GridView iconsGridView;

    HomeGridItemsAdapter homeGridItemsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        iconsGridView = view.findViewById(R.id.home_icons_grid);

        homeGridItemsAdapter = new HomeGridItemsAdapter(getActivity(),getData());
        iconsGridView.setAdapter(homeGridItemsAdapter);

        final NavigationView navigationView = getActivity().findViewById(R.id.nav_view).findViewById(R.id.nav_view);
        iconsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_container,new NoticesFragment()).commit();
                        navigationView.setCheckedItem(R.id.nav_notices);
                        break;
                    default:
                        Console.toast(getActivity(),"Yet to add link");
                }
            }
        });

        return view;
    }

    private ArrayList<HomeGridItem> getData() {
        ArrayList<HomeGridItem> homeGridItemsList = new ArrayList<>();

        HomeGridItem item1 = new HomeGridItem();
        item1.setImage(R.drawable.ic_attendance);
        item1.setTitle(getString(R.string.attendance));
        homeGridItemsList.add(item1);

        HomeGridItem item2 = new HomeGridItem();
        item2.setImage(R.drawable.ic_notice);
        item2.setTitle(getString(R.string.notices));
        homeGridItemsList.add(item2);

        HomeGridItem item3 = new HomeGridItem();
        item3.setImage(R.drawable.ic_resources);
        item3.setTitle(getString(R.string.study_resources));
        homeGridItemsList.add(item3);

        HomeGridItem item4 = new HomeGridItem();
        item4.setImage(R.drawable.ic_clubs);
        item4.setTitle(getString(R.string.club_activities));
        homeGridItemsList.add(item4);

        HomeGridItem item5 = new HomeGridItem();
        item5.setImage(R.drawable.ic_profile);
        item5.setTitle(getString(R.string.profile));
        homeGridItemsList.add(item5);

        return homeGridItemsList;
    }

}
