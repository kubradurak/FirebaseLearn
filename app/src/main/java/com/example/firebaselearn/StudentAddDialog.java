package com.example.firebaselearn;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentAddDialog  extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    EditText edtStudentName;
    EditText edtStudentTelNo;
    EditText edtStudentMail;
    EditText edtDate;

    DatePickerDialog picker;
    Spinner class_spinner;
    Spinner departman_spinner;


    StudentData studentData;
    DatabaseReference databaseReference;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.add_dialog_layout, null);
        builder.setView(view)
                .setTitle("Öğrenci Ekle")
                .setNegativeButton("Vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String name = edtStudentName.getText().toString();
                        String mail = edtStudentMail.getText().toString();
                        String tel = edtStudentTelNo.getText().toString();
                        String date = edtDate.getText().toString();
                        String studentDeparmant = departman_spinner.getSelectedItem().toString();
                        String studentClass = class_spinner.getSelectedItem().toString();


                        if (!name.isEmpty() && !mail.isEmpty()  && !tel.isEmpty() && !date.isEmpty() && !studentDeparmant.isEmpty() && !studentClass.isEmpty() ){
                            addStudentOnFirebase();
                        }

                          else{
                            Toast.makeText(getActivity() ,"Lütfen boş alanları doldurunuz.",Toast.LENGTH_SHORT).show();

                        }



                    }
                });

        databaseReference = FirebaseDatabase.getInstance().getReference("student");

        edtStudentName = view.findViewById(R.id.edt_student_name);
        edtStudentTelNo = view.findViewById(R.id.edt_student_telno);
        edtStudentMail = view.findViewById(R.id.edt_student_mail);
        edtDate = view.findViewById(R.id.edt_date);
        studentData =new StudentData();



        edtDate.setInputType(InputType.TYPE_NULL);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        departman_spinner = view.findViewById(R.id.sp_student_departman);
        ArrayAdapter<CharSequence> departman_adapter = ArrayAdapter.createFromResource(getActivity(),R.array.departmans, android.R.layout.simple_spinner_item);
        departman_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departman_spinner.setAdapter(departman_adapter);
        departman_spinner.setOnItemSelectedListener(this);

        class_spinner = view.findViewById(R.id.sp_student_class);
        ArrayAdapter<CharSequence>  class_adapter = ArrayAdapter.createFromResource(getActivity(),R.array.departman_class, android.R.layout.simple_spinner_item);
        class_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        class_spinner.setAdapter(class_adapter);
        class_spinner.setOnItemSelectedListener(this);


        return builder.create();
    }

    private void addStudentOnFirebase() {

        String studentName = edtStudentName.getText().toString().trim();
        String dateformat = edtDate.getText().toString().trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date studentDate = format.parse(dateformat,new ParsePosition(3));
        int studentTelNo = Integer.parseInt(edtStudentTelNo.getText().toString().trim());
        String studentMail = edtStudentMail.getText().toString().trim();
        String studentDeparmant = departman_spinner.getSelectedItem().toString();
        String studentClass = class_spinner.getSelectedItem().toString();

        String id = databaseReference.push().getKey();
        StudentData sd = new StudentData(id,studentName,studentDate,studentTelNo,studentMail,studentDeparmant,studentClass);
        databaseReference.child(id).setValue(sd);
        Toast.makeText(getActivity(), "Öğrenci Eklendi", Toast.LENGTH_SHORT).show();


    }

    private void showDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year  = calendar.get(Calendar.YEAR);

        picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        picker.show();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
