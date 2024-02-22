package com.denili.service;

import com.denili.model.Usuario;
import com.denili.model.dto.UsuarioRequestDto;
import com.denili.model.dto.UsuarioResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUsuarioService {

    List<UsuarioResponseDto> list();

    ResponseEntity<UsuarioResponseDto> listarPorId(Integer id );
    Usuario registrar( UsuarioRequestDto userDto );

    Usuario modificar( UsuarioRequestDto usuarioRequestDto, Integer id );

    void eliminar( Integer id );
}
