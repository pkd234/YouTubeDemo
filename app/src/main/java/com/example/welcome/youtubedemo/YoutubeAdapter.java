package com.example.welcome.youtubedemo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.net.ConnectException;
import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {
    Context context;
    List<ItemsItem> data;

    public YoutubeAdapter(Context context, List<ItemsItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_layout, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder holder, final int position) {
        Glide.with(context).load(data.get(position).getSnippet().getThumbnails().getHigh().getUrl()).into(holder.thumbnail);
        holder.title.setText(data.get(position).getSnippet().getTitle().toString());
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayer.class);
                intent.putExtra("DATA", data.get(position).getId().getVideoId());
                intent.putExtra("CID", data.get(position).getSnippet().getChannelTitle());
                context.startActivity(intent);
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.like.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.filled_heart).getConstantState()) {
                    Log.d("METHOD", "onClick: " + holder.like.getDrawable().getConstantState() );
                    Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/drawable/baseline");
                    holder.like.setImageURI(uri);


                } else {
                    Log.d("METHOD2", "onClick: " + holder.like.getDrawable().getConstantState() );
                    Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/drawable/filled_heart");
                    holder.like.setImageURI(uri);
                }


            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNew();
            }
        });
    }

    private void loadNew() {
        final Dialog fbDialogue = new Dialog(context, android.R.style.Theme_Black_NoTitleBar);

        fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        fbDialogue.setContentView(R.layout.text_box);
        fbDialogue.setCancelable(true);
        Button test = fbDialogue.findViewById(R.id.button_comment);
        final EditText editText = fbDialogue.findViewById(R.id.editText_comment);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                editText.setText("");


            }
        });
        fbDialogue.show();

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class YoutubeViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail, like, comment;
        TextView title;


        public YoutubeViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumnail);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.chatBox);
            title = itemView.findViewById(R.id.title);
        }
    }
}
