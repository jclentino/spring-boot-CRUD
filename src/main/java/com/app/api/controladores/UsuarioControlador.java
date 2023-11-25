package com.app.api.controladores;

import com.app.api.modelos.UsuarioModelo;
import com.app.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping(path = "/All")
    public ResponseEntity<ArrayList<UsuarioModelo>> getUsers(){
        try {
            ArrayList<UsuarioModelo> usuarios = this.usuarioServicio.getUsers();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/Create")
    public ResponseEntity<UsuarioModelo> saveUser (@RequestBody UsuarioModelo usuario){
        try {
            UsuarioModelo nuevoUsuario = this.usuarioServicio.saveUser(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/Find/{id}")
    public ResponseEntity<Optional<UsuarioModelo>> getUserById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(this.usuarioServicio.getById(id), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/Edit/{id}")
    public ResponseEntity<UsuarioModelo> updateUserById(@RequestBody UsuarioModelo request, @PathVariable("id") Long id){
        try {
            UsuarioModelo usuario = this.usuarioServicio.updateById(request, id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/Remove/{id}")
    public ResponseEntity<String> deleteById (@PathVariable("id") Long id){
        try {
            boolean rta = this.usuarioServicio.deleteUser(id);
            if (rta){
                return new ResponseEntity<>("¡usuario con cedula "+id+" elimnado exitosamente!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("¡Error al intentar eliminar el usuario con cedula "+id, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

