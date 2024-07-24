public class TaskManagerApp {
    private CommandHistory commandHistory = new CommandHistory();
    private TaskManager taskManager = new TaskManager();

    public void createTask(String taskName) {
        Task task = new Task(taskName);
        Command command = new CreateTaskCommand(taskManager, task);
        commandHistory.execute(command);
    }

    public void deleteTask(Task task) {
        Command command = new DeleteTaskCommand(taskManager, task);
        commandHistory.execute(command);
    }

    public void editTask(Task oldTask, String newTaskName) {
        Task newTask = new Task(newTaskName);
        Command command = new EditTaskCommand(taskManager, oldTask, newTask);
        commandHistory.execute(command);
    }

    public void completeTask(Task task) {
        Command command = new CompleteTaskCommand(task);
        commandHistory.execute(command);
    }

    public void undoLastAction() {
        commandHistory.undo();
    }

    public void listTasks() {
        System.out.println("Current tasks:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
    }
    public static void main(String[] args) {
        TaskManagerApp app = new TaskManagerApp();

        app.createTask("Task 1");
        app.createTask("Task 2");
        app.listTasks();

        app.completeTask(app.taskManager.getTasks().get(0));
        app.listTasks();

        app.undoLastAction();
        app.listTasks();

        app.editTask(app.taskManager.getTasks().get(1), "Updated Task 2");
        app.listTasks();

        app.deleteTask(app.taskManager.getTasks().get(1));
        app.listTasks();

        app.undoLastAction();
        app.listTasks();
    }
}
