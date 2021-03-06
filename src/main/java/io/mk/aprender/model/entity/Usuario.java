package io.mk.aprender.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mk.aprender.configuration.base.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuario extends BaseEntity implements UserDetails {

    @Id
    private String id;

    private String username;

    @JsonIgnore
    private String password;

    @DBRef
    private Pessoa pessoa;

    @DBRef
    private Collection<Perfil> perfis;

    @Override
    @JsonIgnore
    public Collection<Perfil> getAuthorities() {
        return getPerfis();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
