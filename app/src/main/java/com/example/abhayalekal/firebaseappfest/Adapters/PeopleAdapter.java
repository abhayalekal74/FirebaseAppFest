package com.example.abhayalekal.firebaseappfest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhayalekal.firebaseappfest.Activities.PeopleActivity;
import com.example.abhayalekal.firebaseappfest.Objects.User;
import com.example.abhayalekal.firebaseappfest.R;
import com.example.abhayalekal.firebaseappfest.firebase.FirebasePresenter;

import java.util.ArrayList;

/**
 * Created by gunjit on 21/06/17.
 */

public class PeopleAdapter extends  RecyclerView.Adapter<PeopleAdapter.ViewHolder>{

    Context context;
    ArrayList<User> users;
    FirebasePresenter firebasePresenter;

    public PeopleAdapter(Context context, ArrayList<User> users, FirebasePresenter firebasePresenter) {
        this.context = context;
        this.users = users;
        this.firebasePresenter = firebasePresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.follow_user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = users.get(position);

        if(((PeopleActivity)context).currentUser.usersFollowing.contains(user))
        {
            holder.followBtn.setText("FOLLOWED");
            holder.followBtn.setBackgroundColor(context.getResources().getColor(R.color.gray99));
            holder.followBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        else
        {
            holder.followBtn.setText("FOLLOW");
            holder.followBtn.setBackgroundColor(context.getResources().getColor(R.color.gray33));
            holder.followBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebasePresenter.follow(user, new FirebasePresenter.UserListener() {
                        @Override
                        public void success(User following) {
                            Toast.makeText(context, "Followed successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure() {
                            Toast.makeText(context, "Followed failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userEmailView;
        TextView followBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            userEmailView = (TextView) itemView.findViewById(R.id.user_email);
            followBtn = (TextView) itemView.findViewById(R.id.follow_btn);
        }
    }
}
