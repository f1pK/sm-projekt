package com.example.zadanie_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final EditText editTextName = findViewById(R.id.task_name);
        final Button btnAddTask = findViewById(R.id.btnAddTask);
        final CheckBox checkBoxIsDone = findViewById(R.id.task_done);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobierz dane z formularza
                String name = editTextName.getText().toString();
                boolean isDone = checkBoxIsDone.isChecked();

                // Dodaj nowe zadanie do listy
                TaskStorage.getInstance().addTask(name, isDone);

                // Zakończ aktywność
                finish();
            }
        });
    }
}
