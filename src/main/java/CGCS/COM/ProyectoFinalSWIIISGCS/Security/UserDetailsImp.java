package CGCS.COM.ProyectoFinalSWIIISGCS.Security;

import CGCS.COM.ProyectoFinalSWIIISGCS.Domain.Administrativo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @file: UserDetailsImp
 * @author: Angel Arribasplata
 * @created: 8/03/2024
 */
@AllArgsConstructor
public class UserDetailsImp implements UserDetails {
    private final Administrativo administrativo;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return administrativo.getPassword();
    }

    @Override
    public String getUsername() {
        return administrativo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre(){
        return administrativo.getNombres();
    }
}
