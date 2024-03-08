package CGCS.COM.ProyectoFinalSWIIISGCS.Repositories;


import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

    Optional<Administrativo> findOneByEmail(String email);
}
