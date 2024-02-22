package com.denili.controller;

import com.denili.model.Usuario;
import com.denili.model.dto.UsuarioRequestDto;
import com.denili.model.dto.UsuarioResponseDto;
import com.denili.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/usuario" )
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping
    public List<UsuarioResponseDto> listar(){
        return service.list();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<UsuarioResponseDto> listarPorId(@PathVariable( "id" ) Integer id ) {
        return service.listarPorId( id );
    }

    @PostMapping
    public Usuario registrar(@Valid @RequestBody UsuarioRequestDto userDto ) {
        return service.registrar( userDto );
    }

    @PutMapping( "/{id}")
    public Usuario modificar(
            @Valid @RequestBody UsuarioRequestDto usuarioRequestDto,
            @PathVariable( "id" ) Integer id) {

        return service.modificar( usuarioRequestDto, id );
    }

    @DeleteMapping( "/{id}" )
    public void darDeBaja(@PathVariable( "id" ) Integer id) {
        service.eliminar( id );
    }
}
