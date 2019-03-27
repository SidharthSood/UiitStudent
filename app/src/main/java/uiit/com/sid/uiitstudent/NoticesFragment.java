package uiit.com.sid.uiitstudent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uiit.com.sid.uiitstudent.Helper.Console;
import uiit.com.sid.uiitstudent.Properties.Notice;

public class NoticesFragment extends Fragment {

    RecyclerView noticesRecyclerView;
    NoticesRecyclerAdapter noticesRecyclerAdapter;
    private DatabaseReference userDataRef;
    ArrayList<Notice> noticesItemList = new ArrayList<>();

    public NoticesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notices, container, false);
        noticesRecyclerView = view.findViewById(R.id.notices_recycler_view);

        userDataRef = FirebaseDatabase.getInstance().getReference().child("Notices");
//        noticesRecyclerAdapter = new NoticesRecyclerAdapter(getActivity(),getData());
        noticesRecyclerAdapter = new NoticesRecyclerAdapter(getActivity(),noticesItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        noticesRecyclerView.setAdapter(noticesRecyclerAdapter);
        noticesRecyclerView.setHasFixedSize(true);
        noticesRecyclerView.setLayoutManager(layoutManager);
        noticesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        userDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noticesItemList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);

                    if (notice!=null && notice.getStatus()) {
                        noticesItemList.add(notice);
                    }
                }
                noticesRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Console.toast(getActivity(),"Error");
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //    private ArrayList<Notice> getData() {
//        ArrayList<Notice> noticesItemList = new ArrayList<>();
//
//        Notice notice1 = new Notice();
//        notice1.setTitle("Notice for fee receipt");
//        notice1.setHyperlink("http://uiit.ac.in/download/new_doc/Notice%20_for%20_Fee%20_Receipt.pdf");
//        noticesItemList.add(notice1);
//
//        Notice notice2 = new Notice();
//        notice2.setTitle("Notice for uniform");
//        notice2.setHyperlink("http://uiit.ac.in/download/new_doc/Notice_for_Uniform.pdf");
//        noticesItemList.add(notice2);
//
//        return noticesItemList;
//    }
}
