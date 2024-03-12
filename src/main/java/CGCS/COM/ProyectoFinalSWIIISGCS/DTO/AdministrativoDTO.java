package CGCS.COM.ProyectoFinalSWIIISGCS.DTO;

import lombok.Data;

/**
 * La clase AdministrativoDTO representa un objeto de transferencia de datos (DTO) para la entidad Administrativo.
 * Contiene los datos necesarios para realizar operaciones de transferencia de información sobre un administrativo.
 */
@Data
public class AdministrativoDTO {

    /** Identificador único del administrativo */
    private Long idAdministrativo;

    /** Nombres del administrativo */
    private String nombres;

    /** Correo electrónico del administrativo */
    private String email;

    // Los siguientes campos han sido comentados ya que no están siendo utilizados actualmente en la clase DTO.
    // En caso de necesitarlos en el futuro, pueden ser descomentados y adaptados según las necesidades del sistema.

    // /** Apellidos del administrativo */
    // private String apellidos;

    // /** DNI del administrativo */
    // private String dni;

    // /** Número de teléfono del administrativo */
    // private String telefono;

    // /** Cargo del administrativo */
    // private String cargo;

}
