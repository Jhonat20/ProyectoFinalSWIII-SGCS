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
import java.util.Optional;

@Service
public class HorarioServiceImp implements HorarioService{

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    @Transactional
    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    @Override
    @Transactional
    public Horario BuscarPorId(Long id)throws EntityNotFoundException {
        Optional<Horario> horario = horarioRepository.findById(id);
        if (horario.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HORARIO_NOT_FOUND);
        }
        return horario.get();
    }

    @Override
    public Horario Grabar(Horario horario) throws IllegalOperationException {
        // Verificar si alguno de los campos es nulo o no tiene el tipo de dato esperado
        if (horario.getDia() == null || horario.getHoraInicio() == null  || horario.getHoraFin() == null) {
            throw new IllegalOperationException(ErrorMessage.HORARIO_INVALID);
        }

        return horarioRepository.save(horario);
    }




    @Override
    public Horario Actualizar(Long id, Horario horario) throws EntityNotFoundException {
      Optional<Horario> horarioExistente = horarioRepository.findById(id);
        if (horarioExistente.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessage.HORARIO_NOT_FOUND);
        }
        Horario horarioActualizado = horarioExistente.get();
        horarioActualizado.setDia(horario.getDia());
        horarioActualizado.setHoraInicio(horario.getHoraInicio());
        horarioActualizado.setHoraFin(horario.getHoraFin());
        return horarioRepository.save(horarioActualizado);
    }

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

}