package com.example.firebaselearn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentList extends ArrayAdapter<StudentData> {

    private Activity context;
    private List<StudentData> studentDataList;

    public StudentList(Activity context, List<StudentData> studentDataList) {
        super(context, R.layout.show_student_layout, studentDataList);
        this.context = context;
        this.studentDataList = studentDataList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.show_student_layout, null,true);
        TextView txtStudentName= view.findViewById(R.id.txt_name);
        TextView txtStudenetDepartman = view.findViewById(R.id.txt_departman);
        TextView txtStudentClass = view.findViewById(R.id.txt_class);

        StudentData studentData = studentDataList.get(position);
        txtStudentName.setText(studentData.getStudentNameD());
        txtStudenetDepartman.setText(studentData.getStudentDeparmantD());
        txtStudentClass.setText(studentData.getStudentClassD());
        return view;

    }
}
