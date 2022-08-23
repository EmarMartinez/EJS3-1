package com.bosonit.ejs31.service;

import com.bosonit.ejs31.domain.Usuario;
import com.bosonit.ejs31.infraestructure.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario saveUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario makeAdmin(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        usuario.setAdmin(true);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUsuario(String username) {
       return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(usuario.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(usuario.getUsername(), usuario.getPassword(),authorities);

    }
}
