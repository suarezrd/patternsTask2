public class CompleteTaskCommand implements Command {
    private Task task;
    private boolean previousState;

    public CompleteTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() {
        previousState = task.isCompleted();
        task.setCompleted(true);
        System.out.println("Task completed: " + task);
    }

    @Override
    public void undo() {
        task.setCompleted(previousState);
        System.out.println("Task completion undone: " + task);
    }
}
