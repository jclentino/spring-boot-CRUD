package com.app.api.servicios;

import com.app.api.modelos.DocenteModelo;
import com.app.api.modelos.UsuarioModelo;
import com.app.api.repositorios.IDocenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DocenteServicio {
    @Autowired
    IDocenteRepositorio docenteRepositorio;


    public ArrayList<DocenteModelo> getTeachers(){
        try {
            return (ArrayList<DocenteModelo>)  docenteRepositorio.findAll();
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al obtener docentes! Detalles: " + e.getMessage());
        }
    }

    public DocenteModelo saveTeacher(DocenteModelo docente){
        try {
            return docenteRepositorio.save(docente);
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al guardar el nuevo docente! Detalles: " + e.getMessage());
        }
    }

    public Optional<DocenteModelo> getById(Long id){
        try {
            return docenteRepositorio.findById(id);
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al obtener docente! Detalles: " + e.getMessage());
        }
    }

    public DocenteModelo updateById(DocenteModelo request, Long id){

        try {
            DocenteModelo docente = docenteRepositorio.findById(id).orElse(null);

            if (docente != null){

                if (request.getNombre() != null){
                    docente.setNombre(request.getNombre());
                }
                if (request.getApellidos() != null){
                    docente.setApellidos(request.getApellidos());
                }
                if (request.getEmail() != null){
                    docente.setEmail(request.getEmail());
                }
                if (request.getTelefono() != null){
                    docente.setTelefono(request.getTelefono());
                }
                if (request.getBlog() != null){
                    docente.setBlog(request.getBlog());
                }
                if (request.isProfesional()){
                    docente.setProfesional(request.isProfesional());
                }
                if (request.isEscalaron()){
                    docente.setEscalaron(request.isEscalaron());
                }
                if (request.getIdioma() != null){
                    docente.setIdioma(request.getIdioma());
                }
                if (request.getAñosExperiencia() >= 0){
                    docente.setAñosExperiencia(request.getAñosExperiencia());
                }
                if (request.getAreaTrabajo() != null){
                    docente.setAreaTrabajo(request.getAreaTrabajo());
                }

                docenteRepositorio.save(docente);
                return  docente;
            }else {
                throw new Exception("El docente no existe");
            }
        } catch (Exception e){
            throw new RuntimeException("¡Error en la consulta al editar docente! Detalles: " + e.getMessage());
        }
    }

    public Boolean deleteTeacher (Long id){
        try {
            Optional<DocenteModelo> docenteOptional = docenteRepositorio.findById(id);

            if (docenteOptional.isPresent()){
                docenteRepositorio.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}

