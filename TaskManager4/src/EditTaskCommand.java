public class EditTaskCommand implements Command {
    private TaskManager taskManager;
    private Task oldTask;
    private Task newTask;

    public EditTaskCommand(TaskManager taskManager, Task oldTask, Task newTask) {
        this.taskManager = taskManager;
        this.oldTask = oldTask;
        this.newTask = newTask;
    }

    @Override
    public void execute() {
        taskManager.updateTask(oldTask, newTask);
    }

    @Override
    public void undo() {
        taskManager.updateTask(newTask, oldTask);
    }
}
