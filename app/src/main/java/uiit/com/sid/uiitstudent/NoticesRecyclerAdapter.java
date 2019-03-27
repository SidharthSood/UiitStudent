package uiit.com.sid.uiitstudent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uiit.com.sid.uiitstudent.Properties.Notice;

public class NoticesRecyclerAdapter extends RecyclerView.Adapter<NoticesRecyclerAdapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Notice> noticeArrayList;

    public NoticesRecyclerAdapter(Context context, ArrayList<Notice> noticeArrayList) {
        this.context = context;
        this.noticeArrayList = noticeArrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_item_layout,viewGroup,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int i) {
        final Notice noticeItem = noticeArrayList.get(i);
        viewHolder.noticeTitleTextView.setText(noticeItem.getTitle());
        viewHolder.noticeTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri noticeItemWebAddress = Uri.parse(noticeItem.getHyperlink());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW,noticeItemWebAddress);
                context.getApplicationContext().startActivity(launchBrowser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeArrayList.size();
    }

    public class CustomViewHolder extends  RecyclerView.ViewHolder {

        TextView noticeTitleTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTitleTextView = itemView.findViewById(R.id.notice_card_title_view);
            noticeTitleTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
