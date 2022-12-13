package com.dh.clase23.security;

import com.dh.clase23.model.Usuario;
import com.dh.clase23.model.UsuarioRole;
import com.dh.clase23.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //cargar un usuario para probar
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passCifrada=cifrador.encode("digital");
        Usuario usuario=new Usuario("Rodolfo","Rodolfo","rebaspineiro@gmail.com",
                passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);
    }
}
