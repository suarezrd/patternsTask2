import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        System.out.println("Task removed: " + task);
    }

    public void updateTask(Task oldTask, Task newTask) {
        int index = tasks.indexOf(oldTask);
        if (index != -1) {
            tasks.set(index, newTask);
            System.out.println("Task updated: " + newTask);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}