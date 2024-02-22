package com.denili.service;

import com.denili.model.Usuario;
import com.denili.model.dto.UsuarioRequestDto;
import com.denili.model.dto.UsuarioResponseDto;
import com.denili.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsuarioResponseDto> list() {
        List<Usuario> usuarios = repo.findAll();
        List<UsuarioResponseDto> usuariosDto = new ArrayList<>();

        for( Usuario usuario : usuarios )
            usuariosDto.add( modelMapper.map( usuario, UsuarioResponseDto.class ) );

        return usuariosDto;
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> listarPorId(Integer id ) {
        Optional<Usuario> op = repo.findById( id );
        Usuario usuario;

        if ( op.isPresent() )
            usuario = op.get();
        else
            usuario = new Usuario();

        UsuarioResponseDto usuarioDto = modelMapper.map( usuario, UsuarioResponseDto.class );
        return new ResponseEntity<>( usuarioDto, HttpStatus.OK );
    }

    @Override
    public Usuario registrar(UsuarioRequestDto userDto) {
        Usuario u = modelMapper.map( userDto, Usuario.class );
        u.setFechaCreacion( LocalDate.now() );
        return repo.save( u );
    }

    @Override
    public Usuario modificar(UsuarioRequestDto usuarioRequestDto, Integer id) {
        Usuario u = modelMapper.map( usuarioRequestDto, Usuario.class );
        u.setId( id );
        u.setFechaCreacion( repo.findById( id ).get().getFechaCreacion() );
        return repo.save( u );
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById( id );
    }
}
