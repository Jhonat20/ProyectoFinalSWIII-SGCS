/**
 * La clase CitaModel es una representación del modelo de una cita en el contexto de HATEOAS (Hypertext As The Engine Of Application State).
 *
 * @file: CitaModel.java
 * @created: [Fecha de creación]
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Cita;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa el modelo de una cita con HATEOAS.
 */
@Data
public class CitaModel extends RepresentationModel<CitaModel> {
    private Long idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private String estado;
}
