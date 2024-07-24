## Consideraciones Jhan

Explicación

	1.	Interfaz Observer: Define el método update que será llamado por el sujeto cuando hay un nuevo mensaje.
	2.	Interfaz Subject: Define los métodos para registrar, eliminar y notificar a los observadores.
	3.	Clase MessageService: Implementa la interfaz Subject y gestiona la lista de observadores. Cuando recibe un nuevo mensaje, notifica a todos los observadores.
	4.	Clases MobileDevice y TabletDevice: Implementan la interfaz Observer y definen cómo deben reaccionar los dispositivos cuando reciben un nuevo mensaje.
	5.	Clase Main: Prueba la implementación del patrón Observer registrando varios dispositivos, enviando mensajes y demostrando la flexibilidad de agregar o eliminar dispositivos en tiempo de ejecución.

Este diseño asegura notificaciones en tiempo real, desacoplamiento y flexibilidad para añadir o eliminar dispositivos sin modificar la lógica principal de la aplicación.

```java

public interface Observer {
    void update(String message);
}

```
    
```java
import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

```

```java
import java.util.ArrayList;
import java.util.List;

public class MessageService implements Subject {
    private List<Observer> observers;

    public MessageService() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Método para recibir nuevos mensajes
    public void receiveMessage(String message) {
        System.out.println("Nuevo mensaje recibido: " + message);
        notifyObservers(message);
    }
}

```

```java
public class MobileDevice implements Observer {
    private String name;

    public MobileDevice(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recibió un nuevo mensaje: " + message);
    }
}

public class TabletDevice implements Observer {
    private String name;

    public TabletDevice(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " recibió un nuevo mensaje: " + message);
    }
}    
```

```java
public class Main {
    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        Observer mobile1 = new MobileDevice("Móvil 1");
        Observer mobile2 = new MobileDevice("Móvil 2");
        Observer tablet = new TabletDevice("Tablet");

        messageService.registerObserver(mobile1);
        messageService.registerObserver(mobile2);
        messageService.registerObserver(tablet);

        messageService.receiveMessage("Hola, este es un mensaje en tiempo real!");

        // Simular la eliminación de un observador
        messageService.removeObserver(mobile2);

        messageService.receiveMessage("Otro mensaje en tiempo real!");
    }
}
```