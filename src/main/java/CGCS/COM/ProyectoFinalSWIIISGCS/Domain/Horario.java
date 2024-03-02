package CGCS.COM.ProyectoFinalSWIIISGCS.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idhorario;
    // DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}