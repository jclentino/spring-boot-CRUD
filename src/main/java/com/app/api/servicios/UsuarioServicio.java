package com.app.api.servicios;

import com.app.api.modelos.UsuarioModelo;
import com.app.api.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    public ArrayList<UsuarioModelo> getUsers(){
        try {
            return (ArrayList<UsuarioModelo>)  usuarioRepositorio.findAll();
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al obtener usuarios! Detalles: " + e.getMessage());
        }
    }

    public UsuarioModelo saveUser(UsuarioModelo usuario){
        try {
            return usuarioRepositorio.save(usuario);
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al guardar el nuevo usuario! Detalles: " + e.getMessage());
        }
    }

    public Optional<UsuarioModelo> getById(Long id){
        try {
            return usuarioRepositorio.findById(id);
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al obtener usuario! Detalles: " + e.getMessage());
        }
    }

    public UsuarioModelo updateById(UsuarioModelo request, Long id){
        try {
            UsuarioModelo usuario = usuarioRepositorio.findById(id).orElse(null);

            if (usuario != null){

                if (request.getClave() != null){
                    usuario.setClave(request.getClave());
                }
                if (request.getNombre() != null){
                    usuario.setNombre(request.getNombre());
                }
                if (request.getTelefono() != null){
                    usuario.setTelefono(request.getTelefono());
                }
                if (request.getEmail() != null){
                    usuario.setEmail(request.getEmail());
                }

                usuarioRepositorio.save(usuario);
                return  usuario;
            }else {
                throw new Exception("El usuario no existe");
            }
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al editar usuario! Detalles: " + e.getMessage());
        }
    }

    public Boolean deleteUser (Long id){
        try {
            Optional<UsuarioModelo> usuarioOptional = usuarioRepositorio.findById(id);

            if (usuarioOptional.isPresent()){
                usuarioRepositorio.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}

