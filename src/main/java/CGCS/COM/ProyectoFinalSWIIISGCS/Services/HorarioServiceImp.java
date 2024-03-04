package CGCS.COM.ProyectoFinalSWIIISGCS.Services;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Horario;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.HorarioRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.ErrorMessage;
import CGCS.COM.ProyectoFinalSWIIISGCS.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación de HorarioService que gestiona las operaciones CRUD de los horarios.
 */

@Service
public class HorarioServiceImp implements HorarioService{

    @Autowired
    private HorarioRepository horarioRepository;

    /**
     * Obtiene una lista de todos los horarios.
     *
     * @return Lista de horarios.
     */
    @Override
    @Transactional
    public List<Horario> listarHorario() {
        return horarioRepository.findAll();
    }

    /**
     * Busca un horario por su ID.
     *
     * @param id El ID del horario a buscar.
     * @return El horario encontrado, si existe.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Horario> buscarPorId(Long id) {
        return horarioRepository.findById(id) ;
    }

    /**
     * Guarda un nuevo horario en la base de datos.
     *
     * @param horario El horario a guardar.
     * @return El horario guardado.
     * @throws IllegalOperationException Si existe solapamiento o duplicado con otro horario.
     */
    @Override
    public Horario guardadHorario(Horario horario) throws IllegalOperationException {
        if (existeSolapamiento(horario)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_SOLAPAMIENTO);
        }
        if (existeHorarioDuplicado(horario)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_DUPLICADO);
        }
        return horarioRepository.save(horario);
    }

    /**
     * Actualiza un horario existente.
     *
     * @param id      El ID del horario a actualizar.
     * @param horario El horario con los datos actualizados.
     * @return El horario actualizado.
     * @throws EntityNotFoundException  Si el horario no se encuentra en la base de datos.
     * @throws IllegalOperationException Si existe solapamiento o duplicado con otro horario.
     */
    @Override
    public Horario actualizarHorario(Long id, Horario horario) throws EntityNotFoundException, IllegalOperationException {
        Optional<Horario> horarioExistente = horarioRepository.findById(id);
        if (horarioExistente.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HORARIO_NOT_FOUND);
        }
        Horario horarioActualizado = horarioExistente.get();
        horarioActualizado.setDia(horario.getDia());
        horarioActualizado.setHoraInicio(horario.getHoraInicio());
        horarioActualizado.setHoraFin(horario.getHoraFin());
        if (existeSolapamiento(horarioActualizado)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_SOLAPAMIENTO);
        }
        if (existeHorarioDuplicado(horarioActualizado)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_DUPLICADO);
        }
        return horarioRepository.save(horarioActualizado);
    }

    /**
     * Elimina un horario de la base de datos.
     *
     * @param id El ID del horario a eliminar.
     * @throws EntityNotFoundException Si el horario no se encuentra en la base de datos.
     */
    @Override
    public void Eliminar(Long id) throws EntityNotFoundException {
        // Verificar si el horario con el ID proporcionado existe en la base de datos
        boolean existeHorario = horarioRepository.existsById(id);
        if (!existeHorario) {
            throw new EntityNotFoundException(ErrorMessage.HORARIO_NOT_FOUND);
        }
        // Si el horario existe, se procede a eliminarlo
        horarioRepository.deleteById(id);
    }


    /**
     * Actualiza parcialmente un horario con los cambios especificados.
     *
     * @param id      El ID del horario a actualizar.
     * @param cambios Mapa con los cambios a aplicar en el horario.
     * @return El horario actualizado.
     * @throws EntityNotFoundException  Si el horario no se encuentra en la base de datos.
     * @throws IllegalOperationException Si existe solapamiento o duplicado con otro horario.
     */
    @Override
    @Transactional
    public Horario actualizarParcial(Long id, Map<String, Object> cambios) throws EntityNotFoundException, IllegalOperationException {
        Optional<Horario> horarioExistente = horarioRepository.findById(id);
        if (horarioExistente.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HORARIO_NOT_FOUND);
        }
        Horario horario = horarioExistente.get();
        aplicarCambiosParciales(horario, cambios);
        if (existeSolapamiento(horario)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_SOLAPAMIENTO);
        }
        if (existeHorarioDuplicado(horario)) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_DUPLICADO);
        }
        return horarioRepository.save(horario);
    }





    /**
     * Aplica cambios parciales a un horario.
     *
     * @param horario El horario al que se le aplicarán los cambios.
     * @param cambios Mapa con los cambios a aplicar en el horario.
     */
    private void aplicarCambiosParciales(Horario horario, Map<String, Object> cambios) {
        cambios.forEach((key, value) -> {
            switch (key) {
                case "dia":
                    horario.setDia((LocalDate) value);
                    break;
                case "horaInicio":
                    horario.setHoraInicio((LocalTime) value);
                    break;
                case "horaFin":
                    horario.setHoraFin((LocalTime) value);
                    break;
                default:
                    throw new IllegalArgumentException("Campo desconocido: " + key);
            }
        });
    }



    /**
     * Verifica si hay solapamiento entre el nuevo horario y los horarios existentes.
     *
     * @param nuevoHorario El nuevo horario a verificar.
     * @return true si hay solapamiento, false de lo contrario.
     */
    private  boolean existeSolapamiento(Horario nuevoHorario) {
        List<Horario> horarios = horarioRepository.findByDia(nuevoHorario.getDia());
        for (Horario horario : horarios) {
            if (nuevoHorario.getHoraInicio().isBefore(horario.getHoraFin()) &&
                    nuevoHorario.getHoraFin().isAfter(horario.getHoraInicio())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Verifica si existe un horario duplicado en la base de datos.
     *
     * @param horario El horario a verificar.
     * @return true si existe un horario duplicado, false de lo contrario.
     */
    private boolean existeHorarioDuplicado(Horario horario) {
        return horarioRepository.findByDiaAndHoraInicioAndHoraFin(horario.getDia(), horario.getHoraInicio(), horario.getHoraFin()).isPresent();
    }

}