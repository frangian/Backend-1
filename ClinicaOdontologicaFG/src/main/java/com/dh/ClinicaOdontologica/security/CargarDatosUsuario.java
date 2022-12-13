package com.dh.ClinicaOdontologica.security;

import com.dh.ClinicaOdontologica.entity.Usuario;
import com.dh.ClinicaOdontologica.entity.UsuarioRole;
import com.dh.ClinicaOdontologica.respository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosUsuario implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;

    public CargarDatosUsuario (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args){

        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCifrada = cifrador.encode("fran123");
        Usuario usuario =  new Usuario("Fran", "fran", "fran@gmail.com", passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        passCifrada = cifrador.encode("admin");
        usuario =  new Usuario("ADMIN", "admin", "admin@admin.com", passCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuario);

    }
}
