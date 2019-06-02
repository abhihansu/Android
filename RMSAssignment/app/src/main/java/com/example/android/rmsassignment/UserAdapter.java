package com.example.android.rmsassignment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.UserViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        holder.mTextViewName.setText("Name : " + model.getName());
        holder.mTextViewAge.setText("Age : " + model.getAge());

        Timestamp timestamp = model.getCreatedDate();
        Date date = timestamp.toDate();

        holder.mTextViewDate.setText(formatDate(date));

    }

    private String formatDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item,
                viewGroup, false);
        return new UserViewHolder(view);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;
        private TextView mTextViewAge;
        private TextView mTextViewDate;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = (TextView) itemView.findViewById(R.id.tv_name);
            mTextViewAge = (TextView) itemView.findViewById(R.id.tv_age);
            mTextViewDate = (TextView) itemView.findViewById(R.id.tv_display_date);
        }
    }
}
