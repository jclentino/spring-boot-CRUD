package com.app.api.repositorios;

import com.app.api.modelos.UsuarioModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<UsuarioModelo, Long> { }
