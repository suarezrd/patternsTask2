public class Main {
    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        Observer mobile1 = new MobileDevice("Móvil 1");
        Observer mobile2 = new MobileDevice("Móvil 2");
        Observer tablet = new TabletDevice("Tablet");
        Observer tablet3 = new TabletDevice("Tablet 3");

        messageService.registerObserver(mobile1);
        messageService.registerObserver(mobile2);
        messageService.registerObserver(tablet);
        messageService.registerObserver(tablet3);

        messageService.receiveMessage("Hola, este mensaje es para probar tablet 3!");

        // Simular la eliminación de un observador
        messageService.removeObserver(mobile2);

        messageService.receiveMessage("Otro mensaje en tiempo donde no debe salir en móvil 2");
    }
}