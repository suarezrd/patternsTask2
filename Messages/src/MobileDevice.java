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

