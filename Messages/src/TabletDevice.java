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