package com.example.zadanie_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    public static final String KEY_EXTRA_TASK_ID = "extra_task_id";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Przycisk "Dodaj pracownika"
        Button btnAddTask = view.findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otwórz nową aktywność do dodawania nowego pracownika
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
            }
        });

        // Przycisk "Opcje"
        Button btnOptions = view.findViewById(R.id.btnOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
            }
        });

        //przycisk lokalizacji API
        Button btnLocation = view.findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otwórz nową aktywność związana z lokalizacją
                Intent intent = new Intent(getActivity(), LokalizacjaActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks) {
            this.tasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = tasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }

    private void updateView() {
        TaskStorage taskStorage = TaskStorage.getInstance();
        List<Task> tasks = taskStorage.getTasks();
        if (adapter == null) {
            adapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTextView, dateTextView;
        private Task task;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));
            itemView.setOnClickListener(this);

            nameTextView = itemView.findViewById(R.id.task_item_name);
            dateTextView = itemView.findViewById(R.id.task_id);
        }

        public void bind(Task task) {
            this.task = task;
            nameTextView.setText(task.getName());
            dateTextView.setText(task.getId().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
            startActivity(intent);
        }
    }
}

