package com.example.firebaselearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAddStudent;
    Button btnShowStudent;
    ListView studentList;
    DatabaseReference databaseReference;
    List<StudentData> studentDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddStudent = findViewById(R.id.btn_add_student);
        btnShowStudent = findViewById(R.id.btn_show_student);
        studentList = findViewById(R.id.list_student);
        databaseReference = FirebaseDatabase.getInstance().getReference("student");

        studentDataList = new ArrayList<>();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudentDialog();


            }
        });

        btnShowStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Öğrenci Listesi", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentDataList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    StudentData studentData = snapshot.getValue(StudentData.class);
                    studentDataList.add(studentData);

                }
                StudentList adapter = new StudentList(MainActivity.this,studentDataList);
                studentList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void openAddStudentDialog() {
        StudentAddDialog dialog = new StudentAddDialog();
        dialog.show(getSupportFragmentManager(),"ghgdsddfh");
    }


}
