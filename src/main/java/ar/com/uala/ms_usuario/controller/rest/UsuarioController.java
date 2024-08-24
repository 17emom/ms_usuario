package ar.com.uala.ms_usuario.controller.rest;

import ar.com.uala.ms_usuario.domain.Usuario;
import ar.com.uala.ms_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{usuarioLogueadoId}/seguido/{usuarioASeguirId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario seguir(@PathVariable("usuarioLogueadoId") Long usuarioLogueadoId, @PathVariable("usuarioASeguirId") Long usuarioASeguirId) {
        return usuarioService.seguir(usuarioLogueadoId, usuarioASeguirId);
    }

    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorId(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.buscarPorId(usuarioId);
    }
}
