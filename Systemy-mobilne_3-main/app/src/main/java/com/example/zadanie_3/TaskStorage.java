package com.example.zadanie_3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;
public class TaskStorage {
    private static final TaskStorage taskStorage = new TaskStorage();
    private final List<Task> tasks;
    public static TaskStorage getInstance(){return taskStorage;};

    public List<Task> getTasks() {
        return tasks;
    }

    private TaskStorage(){
        tasks = new ArrayList<>();
        Task pracownik1 = new Task("Jan Kowalski");
        Task pracownik2 = new Task("Katarzyna Kowalczyk");
        Task pracownik3 = new Task("Mariusz Kędzierski");
        tasks.add(pracownik1);
        tasks.add(pracownik2);
        tasks.add(pracownik3);
    }

    private Date ustawDate(int liczba)
    {
        Calendar date = Calendar.getInstance();
        long timeInSecs=date.getTimeInMillis();
        Date data = new Date(timeInSecs + (liczba * 60 * 1000));
        return data;
    }

    public Task getTask(UUID id) {
        for (Task task : tasks) {
            if (task.getId().equals(id))
                return task;
        }
        return null;
    }

    public void addTask(String name, boolean isWorking) {
        Task newTask = new Task(name);
        newTask.setDone(isWorking);
        newTask.setDate(ustawDate(0));  // Ustawiamy datę, możesz dostosować do swoich potrzeb
        tasks.add(newTask);
    }

}
