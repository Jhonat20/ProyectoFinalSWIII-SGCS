/**
 * @file: PacienteModelAssembler.java
 * @author: (c) Jhon Bravo
 * @created: 11/03/2024 23:25
 */

package CGCS.COM.ProyectoFinalSWIIISGCS.ImpHateoas.Paciente;

import CGCS.COM.ProyectoFinalSWIIISGCS.Controllers.PacienteController;
import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Paciente;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Ensamblador de modelos de HATEOAS para la entidad Paciente.
 */
@Component
public class PacienteModelAssembler extends RepresentationModelAssemblerSupport<Paciente, PacienteModel> {

    /**
     * Constructor que inicializa el ensamblador.
     */
    public PacienteModelAssembler() {
        super(PacienteController.class, PacienteModel.class);
    }

    /**
     * Convierte la entidad Paciente en un modelo PacienteModel.
     *
     * @param paciente La entidad Paciente a convertir.
     * @return El modelo PacienteModel resultante.
     */
    @Override
    public PacienteModel toModel(Paciente paciente) {
        PacienteModel pacienteModel = new PacienteModel();
        pacienteModel.setIdPaciente(paciente.getIdPaciente());
        pacienteModel.setNombre(paciente.getNombre());
        pacienteModel.setApellido(paciente.getApellido());
        pacienteModel.setDni(paciente.getDni());
        pacienteModel.setTelefono(paciente.getTelefono());
        pacienteModel.setEmail(paciente.getEmail());
        pacienteModel.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteModel.setDireccion(paciente.getDireccion());
        pacienteModel.setGrupoSanguineo(paciente.getGrupoSanguineo());
        return pacienteModel;
    }
}
