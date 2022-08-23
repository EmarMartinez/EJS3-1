package com.bosonit.ejs31.service;

import com.bosonit.ejs31.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario saveUser(Usuario usuario);
    Usuario makeAdmin(String username);
    Usuario getUsuario(String username);
    List<Usuario> findAll();
}
