package ar.com.uala.ms_usuario.service;

import ar.com.uala.ms_usuario.domain.Usuario;
import ar.com.uala.ms_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario seguir(Long seguidorId, Long seguidoId) {
        validarDatoEntrada(seguidorId);
        validarDatoEntrada(seguidoId);
        validarAutoSeguimiento(seguidorId, seguidoId);
        Usuario usuarioSeguidor = usuarioRepository.findById(seguidorId).orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
        validarSeguimientoExistente(usuarioSeguidor, seguidoId);
        usuarioSeguidor.getSeguidos().add(seguidoId);

        return usuarioRepository.save(usuarioSeguidor);
    }

    private void validarDatoEntrada(Long id) {
        Assert.notNull(id, "El ID del usuario no puede ser nulo");
        Assert.isTrue(usuarioRepository.existsById(id), "El usuario no existe");
    }

    private void validarAutoSeguimiento(Long seguidorId, Long seguidoId) {
        Assert.isTrue(!seguidorId.equals(seguidoId), "El usuario no puede seguirse a si mismo");
    }

    private void validarSeguimientoExistente(Usuario usuarioSeguidor, Long seguidoId) {
        Assert.isTrue(!usuarioSeguidor.getSeguidos().contains(seguidoId), "El usuario ya lo seguía");
    }
}
