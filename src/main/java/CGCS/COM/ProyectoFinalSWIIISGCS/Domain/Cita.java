package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Clase que representa una cita dentro del dominio de la aplicación.
 * Utiliza anotaciones de JPA para mapear la clase a una tabla en una base de datos y
 * anotaciones de Jackson para gestionar la serialización/deserialización JSON.
 * También se incluyen validaciones para los campos de la cita.
 */
@Data
@Entity // Especifica que la clase es una entidad JPA.
@Table(name = "citas") // Especifica la tabla en la base de datos con la que esta entidad está vinculada.
public class Cita {

    @Id // Marca el campo como la clave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura la generación automática del valor del ID.
    @Column(name = "id_cita") // Mapea el campo al nombre de la columna especificada en la base de datos.
    private Long idCita; // Identificador único para la cita.

    private LocalDate fecha;
    private LocalTime hora;

    @NotBlank(message = "La descripción es obligatoria") // Asegura que la descripción no sea nula ni esté en blanco.
    @Column(nullable = false) // Indica que la columna no puede ser nula.
    private String descripcion; // Descripción de la cita.

    @Column(name = "estado") // Indica que la columna no puede ser nula.
    private String estado; // Estado actual de la cita (por ejemplo: "confirmado", "cancelado").

    @ManyToOne // Establece una relación muchos a uno con la entidad Doctor.
    @JoinColumn(name = "id_doctor") // Especifica la columna de unión con la clave foránea a Doctor.
    @JsonBackReference // Evita la recursión infinita al serializar a JSON.
    private Doctor doctor; // Referencia al doctor asignado a la cita.

    @ManyToOne // Establece una relación muchos a uno con la entidad Paciente.
    @JoinColumn(name = "id_paciente") // Especifica la columna de unión con la clave foránea a Paciente.
    @JsonIgnore // Evita que el paciente sea incluido en la serialización JSON.
    private Paciente paciente; // Referencia al paciente asignado a la cita.




    //**********Metodo adicional**************//

    @PrePersist
    public void prePersist() {
        if (fecha == null) {
            fecha = LocalDate.now();  // Fecha de creación por defecto
        }
        if (hora == null) {
            hora = LocalTime.now();  // Hora actual del servidor
        }
        if (estado == null) {
            estado = "Pendiente";  // Estado por defecto
        }
    }


}
