package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    Optional<Horario> findByDiaAndHoraInicioAndHoraFin(LocalDate dia, LocalTime horaInicio, LocalTime horaFin);
    List<Horario> findByDia(LocalDate dia);
}
