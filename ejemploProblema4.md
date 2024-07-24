## Consideraciones Jefferson Juan David

Beneficios del Patrón Command en este Contexto:

	•	Desacoplamiento: La interfaz de usuario u otros componentes no necesitan conocer los detalles de cómo se realizan las acciones.
	•	Extensibilidad: Nuevas operaciones se pueden añadir fácilmente creando nuevas clases de comandos.
	•	Registro y Reversión: La pila de comandos permite registrar todas las acciones y proporcionar una funcionalidad de deshacer fácilmente.

Este enfoque proporciona una estructura flexible y extensible para manejar acciones de usuario en el sistema de gestión de tareas, alineándose perfectamente con los beneficios requeridos.

El patrón Memento es otro patrón de diseño que podría considerarse para el problema del “Sistema de Gestión de Tareas”. Sin embargo, existen varias razones por las cuales el Patrón Command es más adecuado que el Patrón Memento para este caso específico.

Diferencias entre Patrón Command y Patrón Memento:

	1.	Propósito y Enfoque:
	•	Patrón Command: Se utiliza principalmente para encapsular todas las acciones que se pueden realizar en un sistema, permitiendo que las operaciones se puedan encolar, registrar y deshacer. Está enfocado en ejecutar y deshacer operaciones de una manera desacoplada.
	•	Patrón Memento: Se utiliza para capturar y restaurar el estado interno de un objeto sin violar su encapsulamiento, permitiendo que el estado se guarde y se restaure posteriormente.
	2.	Desacoplamiento del Invocador y los Objetos que Realizan las Acciones:
	•	Patrón Command: Proporciona un mayor desacoplamiento entre el invocador (quien solicita la acción) y el receptor (quien ejecuta la acción) al encapsular una solicitud como un objeto.
	•	Patrón Memento: No proporciona un mecanismo de desacoplamiento entre el invocador y el receptor de la acción, ya que su enfoque principal es guardar y restaurar el estado.
	3.	Extensibilidad de Nuevas Operaciones:
	•	Patrón Command: Permite la adición de nuevas operaciones fácilmente creando nuevas clases de comandos sin necesidad de cambiar el código existente.
	•	Patrón Memento: Está centrado en el estado del objeto, no en la ejecución de nuevas operaciones, por lo que agregar nuevas operaciones puede requerir cambios más significativos.
	4.	Registro y Reversión de Acciones:
	•	Patrón Command: Facilita el registro de todas las acciones realizadas y proporciona una manera directa de implementar la reversión de estas acciones mediante una pila de comandos.
	•	Patrón Memento: Guarda estados completos del objeto, lo que puede ser menos eficiente si solo se necesita revertir una operación específica sin cambiar otros estados del objeto.

Aplicación del Patrón Command en el Sistema de Gestión de Tareas:

El Patrón Command se ajusta mejor a los requisitos específicos del “Sistema de Gestión de Tareas” debido a los siguientes beneficios:

	1.	Desacoplamiento del Invocador y los Objetos que Realizan las Acciones:
	•	Cada acción (crear, editar, eliminar, completar) se encapsula en su propia clase de comando. Esto permite que la interfaz de usuario u otros componentes simplemente invoquen comandos sin preocuparse por los detalles de implementación.
	2.	Extensibilidad de Nuevas Operaciones:
	•	Se pueden agregar nuevas acciones simplemente creando nuevas clases de comandos. No se necesita modificar las clases existentes, lo que facilita la evolución del sistema.
	3.	Registro y Reversión de Acciones:
	•	Cada comando puede implementar una operación de “deshacer” (undo), y una pila de comandos permite llevar un registro de todas las acciones realizadas. Esto proporciona una manera eficiente y clara de revertir acciones específicas.

Comparación con el Patrón Memento en este Contexto:

	•	Estado Completo vs. Acciones Específicas: El Patrón Memento guarda el estado completo del objeto, lo cual puede ser excesivo si solo se necesita revertir una acción específica. En contraste, el Patrón Command permite revertir acciones específicas sin afectar otros estados.
	•	Simplicidad y Desempeño: Guardar estados completos (como hace el Memento) puede ser costoso en términos de memoria y desempeño, especialmente si los estados son grandes o se realizan muchas operaciones. El Command, al guardar solo las acciones, suele ser más eficiente.
	•	Flexibilidad en la Evolución: El Command facilita la adición de nuevas operaciones sin necesidad de cambiar el código existente, mientras que el Memento requiere que el objeto tenga un mecanismo para guardar y restaurar su estado, lo que puede ser más restrictivo.

En resumen, el Patrón Command es más adecuado para el “Sistema de Gestión de Tareas” porque proporciona un mejor desacoplamiento, facilita la extensión de nuevas operaciones y es más eficiente y claro para el registro y reversión de acciones específicas.

```java
public interface Command {
    void execute();
    void undo();
}
```

```java
public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', completed=" + completed + '}';
    }
}
```

```java
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
```

```java
public class CreateTaskCommand implements Command {
    private TaskManager taskManager;
    private Task task;

    public CreateTaskCommand(TaskManager taskManager, Task task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    @Override
    public void execute() {
        taskManager.addTask(task);
    }

    @Override
    public void undo() {
        taskManager.removeTask(task);
    }
}
```

```java
public class DeleteTaskCommand implements Command {
    private TaskManager taskManager;
    private Task task;

    public DeleteTaskCommand(TaskManager taskManager, Task task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    @Override
    public void execute() {
        taskManager.removeTask(task);
    }

    @Override
    public void undo() {
        taskManager.addTask(task);
    }
}
```

```java
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
```

```java
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
```

```java
import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void execute(Command command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        }
    }
}
```

```java
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
    
```

