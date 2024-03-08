package CGCS.COM.ProyectoFinalSWIIISGCS.Security;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import CGCS.COM.ProyectoFinalSWIIISGCS.Repositories.AdministrativoRepository;
import CGCS.COM.ProyectoFinalSWIIISGCS.Services.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @file: UserDetailServiceImp
 * @author: Angel Arribasplata
 * @created: 8/03/2024
 */
@Service
public class UserDetailServiceImp implements UserDetailsService {
@Autowired
    private AdministrativoRepository administrativoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrativo administrativo= administrativoRepository
                .findOneByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con email" + email + "no existe."));
        return new UserDetailsImp(administrativo);
    }
}
