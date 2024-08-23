package ar.com.uala.ms_usuario.controller.rest;

import ar.com.uala.ms_usuario.Usuario;
import ar.com.uala.ms_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{usuarioLogueadoId}/seguido/{usuarioASeguirId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario seguir(@PathVariable("usuarioLogueadoId") Long usuarioLogueadoId, @PathVariable("usuarioLogueadoId") Long usuarioASeguirId) {
        return usuarioService.seguir(usuarioLogueadoId, usuarioASeguirId);
    }
}
