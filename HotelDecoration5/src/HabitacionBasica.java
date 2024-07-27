// Implementación concreta de la habitación básica
public class HabitacionBasica implements Habitacion {
    @Override
    public String getDescripcion() {
        return "Habitación básica";
    }

    @Override
    public double getCosto() {
        return 100.00;
    }
}