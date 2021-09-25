package com.exam.nisum.app.services;

import com.exam.nisum.app.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
