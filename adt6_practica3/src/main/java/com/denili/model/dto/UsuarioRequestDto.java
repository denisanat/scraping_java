package com.denili.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {

    @NotNull
    @Size( min = 2, max = 20, message = "Nombre debe tener entre 2 y 20 caracteres" )
    private String nombre;

    @NotNull
    @Size( min = 5, max = 30, message = "Descripcion debe tener entre 5 y 30 caracteres" )
    private String apellidos;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+")
    private String password;
}
