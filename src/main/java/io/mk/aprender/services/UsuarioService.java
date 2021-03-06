package io.mk.aprender.services;

import io.mk.aprender.model.entity.Usuario;
import io.mk.aprender.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UsuarioService implements Serializable {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(final UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findByUsername(final String username){
        return usuarioRepository.findByUsername(username);
    }
}
