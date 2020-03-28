package com.example.liv_ez;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.bind(post);

        holder.ibTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeAt(position);

            }
        });


    }

    private void removeAt(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, posts.size());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvDescription;
        private CheckBox CheckedBox;
        private ToggleButton tbAdd;
        private ImageView ibTrash;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            CheckedBox = itemView.findViewById(R.id.Checkedbox);
            tbAdd = itemView.findViewById(R.id.tbAdd);
            ibTrash = itemView.findViewById(R.id.ibTrash);


        }



        public void bind(final Post post) {

            tvUsername.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            CheckedBox.setChecked(post.getChecked());
            tbAdd.setChecked(post.getIfAssigned());

            CheckedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {

                        post.setChecked(true);
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e("PostsAdapter", "Checking went wrong", e);
                                }
                                else {
                                    Log.i("PostsAdapter","Checking successful");
                                }
                            }
                        });
                    }

                   else if (!isChecked) {
                        post.setChecked(false);
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e("PostsAdapter", "Checking went wrong", e);
                                }
                                else {
                                    Log.i("PostsAdapter", "Checking successful");
                                }
                            }
                        });
                    }
                }
            });

            tbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                    if (isChecked) {

                        post.setAssigned(ParseUser.getCurrentUser());
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e("PostsAdapter", "Saving assignedTo went wrong",e);
                                }
                                else {
                                    Log.i("PostsAdapter", "Saving assignedTo successful");
                                }
                            }
                        });
                    }

                    else if (!isChecked) {

                        post.setAssigned(null);
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.e("PostsAdapter", "Saving assignedTo went wrong",e);
                                }
                                else {
                                    Log.i("PostsAdapter", "Saving assignedTo successful");
                                }

                            }
                        });
                    }

                }
            });


        }
    }
}
