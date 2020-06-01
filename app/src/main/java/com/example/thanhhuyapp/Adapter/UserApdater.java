package com.example.thanhhuyapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thanhhuyapp.MainActivity;
import com.example.thanhhuyapp.R;
import com.example.thanhhuyapp.model.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserApdater extends RecyclerView.Adapter<UserApdater.ViewHolder> {
    public ArrayList<User> users;
    Context mContext;
    DatabaseReference mData;
    OnUserClickedListener callback;

    public UserApdater(Context mContext,ArrayList<User> users) {
        this.users = users;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext();
        LayoutInflater inflater =  LayoutInflater.from(mcontext);

        View usersview = inflater.inflate(R.layout.user_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(usersview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserApdater.ViewHolder holder, final int position) {
        final User user = users.get(position);

        holder.tv_tenkhachhang.setText(users.get(position).getTenkhachhang());
        holder.tv_sodienthoai.setText(users.get(position).getSodienthoai());
        holder.tv_diachinha.setText(users.get(position).getDiachinha());
        holder.tv_loaigas.setText(users.get(position).getLoaigas());
        holder.tv_ngaybatdau.setText(users.get(position).getNgaybatdau());
        holder.tv_ngaythaygas.setText(users.get(position).getNgaythaygas());

        holder.img_delete.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hihi", users.get(position).getId());
                String id = users.get(position).getId();
                callback.onUserClickedListener(id);
            }
        });





    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView tv_tenkhachhang,tv_sodienthoai,tv_diachinha,tv_loaigas,tv_ngaybatdau,tv_ngaythaygas;
        public ImageView img_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenkhachhang = itemView.findViewById(R.id.tv_tenkhachhang);
            tv_sodienthoai = itemView.findViewById(R.id.tv_sodienthoai);
            tv_diachinha = itemView.findViewById(R.id.tv_diachinha);
            tv_loaigas = itemView.findViewById(R.id.tv_loaigas);
            tv_ngaybatdau = itemView.findViewById(R.id.tv_ngaybatdau);
            tv_ngaythaygas = itemView.findViewById(R.id.tv_ngaythaygas);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
    public void setOnUserClickedListener(OnUserClickedListener callback){
        this.callback = callback;
    }

    public interface OnUserClickedListener {
        void onUserClickedListener(String id);
    }

}
