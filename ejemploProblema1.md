
## Consideraciones NATACHA
Explicación del Código

	1.	Interfaz Prototype: Define el método clone() que cada clase concreta de héroe implementará.
	2.	Clase Habilidad: Representa una habilidad específica de un héroe.
	3.	Clase Heroe: Implementa la interfaz Prototype y contiene las propiedades comunes de todos los héroes.
	4.	Clases Concretas de Héroes (Guerrero, Mago, Arquero): Extienden la clase Heroe y especifican las clases de héroes.
	5.	Clase JuegoAventuras: Contiene el método main donde se crea un héroe original y se clona, mostrando cómo personalizar el clon después de su creación.

Esta implementación permite clonar héroes y personalizarlos, manteniendo la eficiencia y evitando la duplicación innecesaria de datos.

```java

import java.util.ArrayList;
import java.util.List;

interface Prototype {
    Prototype clone();
}

class Habilidad {
    private String nombre;

    public Habilidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class Heroe implements Prototype {
    private String nombre;
    private String clase;
    private List<Habilidad> habilidades;
    private int nivel;
    private int puntosExperiencia;

    public Heroe(String nombre, String clase, List<Habilidad> habilidades, int nivel, int puntosExperiencia) {
        this.nombre = nombre;
        this.clase = clase;
        this.habilidades = new ArrayList<>(habilidades);
        this.nivel = nivel;
        this.puntosExperiencia = puntosExperiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPuntosExperiencia() {
        return puntosExperiencia;
    }

    @Override
    public Heroe clone() {
        return new Heroe(nombre, clase, habilidades, nivel, puntosExperiencia);
    }

    @Override
    public String toString() {
        return "Heroe{" +
                "nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                ", habilidades=" + habilidades +
                ", nivel=" + nivel +
                ", puntosExperiencia=" + puntosExperiencia +
                '}';
    }
}

class Guerrero extends Heroe {
    public Guerrero(String nombre, List<Habilidad> habilidades, int nivel, int puntosExperiencia) {
        super(nombre, "Guerrero", habilidades, nivel, puntosExperiencia);
    }
}

class Mago extends Heroe {
    public Mago(String nombre, List<Habilidad> habilidades, int nivel, int puntosExperiencia) {
        super(nombre, "Mago", habilidades, nivel, puntosExperiencia);
    }
}

class Arquero extends Heroe {
    public Arquero(String nombre, List<Habilidad> habilidades, int nivel, int puntosExperiencia) {
        super(nombre, "Arquero", habilidades, nivel, puntosExperiencia);
    }
}

public class JuegoAventuras {
    public static void main(String[] args) {
        List<Habilidad> habilidadesGuerrero = new ArrayList<>();
        habilidadesGuerrero.add(new Habilidad("Ataque Fuerte"));
        habilidadesGuerrero.add(new Habilidad("Defensa Rápida"));

        Guerrero guerreroOriginal = new Guerrero("Thor", habilidadesGuerrero, 5, 1000);

        // Clonar el guerrero original
        Guerrero clonGuerrero = (Guerrero) guerreroOriginal.clone();
        clonGuerrero.setNombre("Clon de Thor");

        // Personalizar habilidades del clon
        clonGuerrero.getHabilidades().add(new Habilidad("Habilidad Especial"));

        System.out.println("Guerrero Original: " + guerreroOriginal);
        System.out.println("Clon Guerrero: " + clonGuerrero);
    }
}
```