package com.example.thanhhuyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhhuyapp.Adapter.UserApdater;
import com.example.thanhhuyapp.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class MainActivity extends AppCompatActivity implements UserApdater.OnUserClickedListener {
    EditText edt_tenkhachhang,edt_sodienthoai,edt_diachinha,edt_loaigas,edt_date_use;
    TextView tv_date,tv_date_instead;
    public Button bt_add,bt_them,bt_huy;
    DatabaseReference mData;
    RecyclerView recyclerView;
    UserApdater apdater;
    public ArrayList<User> users ;
    public int ngay,thang,nam;
    public String start_date,end_date;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);
        bt_add = findViewById(R.id.bt_add);


        mData = FirebaseDatabase.getInstance().getReference();

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_themkhachhang();

            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = new ArrayList<User>();
                for(DataSnapshot user :dataSnapshot.getChildren()) {
                    String id = user.getKey();
                    User userData = user.getValue(User.class);
                    users.add(userData);
                    users.get(users.size()-1).setId(id);
                }
                apdater = new UserApdater(MainActivity.this,users);
                recyclerView.setAdapter(apdater);
                apdater.setOnUserClickedListener(MainActivity.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public  void dialog_themkhachhang() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_addinfomation);
        dialog.setCanceledOnTouchOutside(false);
        bt_them = dialog.findViewById(R.id.bt_them);
        bt_huy = dialog.findViewById(R.id.bt_huy);
        edt_tenkhachhang = dialog.findViewById(R.id.edt_tenkhachhang);
        edt_sodienthoai = dialog.findViewById(R.id.edt_sodienthoai);
        edt_diachinha = dialog.findViewById(R.id.edt_diachinha);
        edt_loaigas = dialog.findViewById(R.id.edt_loaigas);
        edt_date_use = dialog.findViewById(R.id.edt_date_use);
        tv_date = dialog.findViewById(R.id.tv_date);


        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia_log_thoigian();
            }
        });
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenkhachhang = edt_tenkhachhang.getText().toString();
                String sodienthoai = edt_sodienthoai.getText().toString();
                String diachinha = edt_diachinha.getText().toString();
                String loaigas = edt_loaigas.getText().toString();
                String ngaybatdau = tv_date.getText().toString();

                String number_date = edt_date_use.getText().toString();
                if( TextUtils.equals(number_date,""))
                {
                    edt_date_use.setError("Chưa nhập số ngày dùng gas");
                }
                else
                {
                    calendar.add(Calendar.DATE, Integer.parseInt(number_date));  // number of days to add
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    end_date = simpleDateFormat.format(calendar.getTime());
                }
                if (tenkhachhang.isEmpty() || sodienthoai.isEmpty() || diachinha.isEmpty() || loaigas.isEmpty() || ngaybatdau.isEmpty()  ) {
                    edt_tenkhachhang.setError("Chưa nhập Tên khách hàng");
                    edt_sodienthoai.setError("Chưa nhập số điện thoại");
                    edt_diachinha.setError("Chưa nhập địa chỉ nhà");
                    edt_loaigas.setError("Chưa nhập loại gas");
                    tv_date.setError("Chưa chọn ngày dùng gas");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                     mData = database.getReference("User");
                    String id = mData.push().getKey();
                   final User user = new User(tenkhachhang, sodienthoai, diachinha, loaigas, ngaybatdau, end_date);
                   mData.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(MainActivity.this,"Thêm thành công!",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(MainActivity.this,"Thêm thất bại! " + e.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.dismiss();
                }


                }

        });
        bt_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dialog.cancel();
            }
        });


        dialog.show();


    }
    public void dia_log_thoigian() {

        ngay = calendar.get(Calendar.DATE);
        thang = calendar.get(Calendar.MONTH);
        nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("dd/MM/yyyy");
                tv_date.setText(simpleDateFormat.format(calendar.getTime()));
                start_date = simpleDateFormat.format(calendar.getTime());
            }
        },nam,thang,ngay);
        datePickerDialog.show();

    }

    @Override
    public void onUserClickedListener(String id) {
        Log.d("hihilan2", id);
        mData.child("User").child(id).removeValue();
    }
}
