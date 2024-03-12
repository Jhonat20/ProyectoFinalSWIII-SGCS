package CGCS.COM.ProyectoFinalSWIIISGCS.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Clase que representa la entidad de usuario.
 *
 * @file: User
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 10:17 p. m.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Nombre de usuario del usuario.
     */
    @Column(nullable = false)
    private String username;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido del usuario.
     */
    private String apellido;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * País del usuario.
     */
    private String country;

    /**
     * Rol del usuario.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Obtiene la colección de autoridades (roles) del usuario.
     *
     * @return Colección de autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    /**
     * Indica si la cuenta del usuario no ha caducado.
     *
     * @return true si la cuenta no ha caducado, false de lo contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario no está bloqueada.
     *
     * @return true si la cuenta no está bloqueada, false de lo contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario no han caducado.
     *
     * @return true si las credenciales no han caducado, false de lo contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     *
     * @return true si el usuario está habilitado, false de lo contrario.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
