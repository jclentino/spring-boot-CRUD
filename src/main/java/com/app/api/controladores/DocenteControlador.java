package com.app.api.controladores;

import com.app.api.modelos.DocenteModelo;
import com.app.api.modelos.UsuarioModelo;
import com.app.api.servicios.DocenteServicio;
import com.app.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
public class DocenteControlador {

    @Autowired
    private DocenteServicio docenteServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping(path = "/All")
    public ResponseEntity<ArrayList<DocenteModelo>> getTeachers(){
        try {
            ArrayList<DocenteModelo> docentes = this.docenteServicio.getTeachers();
            return new ResponseEntity<>(docentes, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/Create/{userid}")
    public ResponseEntity<DocenteModelo> saveTeacher (@RequestBody DocenteModelo docente, @PathVariable("userid") Long userid ){
        try {
            Optional<UsuarioModelo> usuarioOptional = usuarioServicio.getById(userid);
            usuarioOptional.ifPresent(usuario -> {
                docente.setUsuario(usuario);
                usuario.getDocentes().add(docente);
                usuarioServicio.saveUser(usuario);
            });
            DocenteModelo nuevoDocente = this.docenteServicio.saveTeacher(docente);
            return new ResponseEntity<>(nuevoDocente, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/Find/{id}")
    public ResponseEntity<Optional<DocenteModelo>> getTeacherById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(this.docenteServicio.getById(id), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/Edit/{id}")
    public ResponseEntity<DocenteModelo> updateTeacherById(@RequestBody DocenteModelo request, @PathVariable("id") Long id){
        try {
            DocenteModelo docente = this.docenteServicio.updateById(request, id);
            return new ResponseEntity<>(docente, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/Remove/{id}")
    public ResponseEntity<String> deleteById (@PathVariable("id") Long id){
        try {
            boolean rta = this.docenteServicio.deleteTeacher(id);
            if (rta){
                return new ResponseEntity<>("¡docente con id "+id+" elimnado exitosamente!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("¡Error al intentar eliminar el docente con id "+id, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

