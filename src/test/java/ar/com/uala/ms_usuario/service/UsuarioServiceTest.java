package ar.com.uala.ms_usuario.service;

import ar.com.uala.ms_usuario.domain.Usuario;
import ar.com.uala.ms_usuario.builder.UsuarioBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void seguir_conUsuarioLogueadoIdNulo_lanzaExcepcion() {
        Usuario usuario = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .sinSeguidores()
                .buildAndPersist(em);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(null, usuario.getId()))
                .withMessage("El ID del usuario no puede ser nulo");
    }

    @Test
    public void seguir_conUsuarioASeguirIdNulo_lanzaExcepcion() {
        Usuario usuario = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .sinSeguidores()
                .buildAndPersist(em);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(usuario.getId(), null))
                .withMessage("El ID del usuario no puede ser nulo");
    }

    @Test
    public void seguir_conUsuarioLogueadoNoPersistido_lanzaExcepcion() {
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conId(3L)
                .conNombre("ezortega")
                .sinSeguidores()
                .build();
        Usuario usuario2 = UsuarioBuilder
                .crear()
                .conNombre("mamateo")
                .sinSeguidores()
                .buildAndPersist(em);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(usuario1.getId(), usuario2.getId()))
                .withMessage("El usuario no existe");
    }

    @Test
    public void seguir_conUsuarioASeguirNoPersistido_lanzaExcepcion() {
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conNombre("mamateo")
                .sinSeguidores()
                .buildAndPersist(em);
        Usuario usuario2 = UsuarioBuilder
                .crear()
                .conId(3L)
                .conNombre("ezortega")
                .sinSeguidores()
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(usuario1.getId(), usuario2.getId()))
                .withMessage("El usuario no existe");
    }

    @Test
    public void seguir_conUsuarioPersistidoIntentandoSeguirseASiMismo_lanzaExcepcion() {
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .sinSeguidores()
                .buildAndPersist(em);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(usuario1.getId(), usuario1.getId()))
                .withMessage("El usuario no puede seguirse a si mismo");
    }

    @Test
    public void seguir_conUsuarioPersistidoIntentandoSeguirAUnUsuarioQueYaSigue_lanzaExcepcion() {
        Usuario usuario2 = UsuarioBuilder
                .crear()
                .conNombre("mamateo")
                .sinSeguidores()
                .buildAndPersist(em);
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .conSeguidores(asList(usuario2.getId()))
                .buildAndPersist(em);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> usuarioService.seguir(usuario1.getId(), usuario2.getId()))
                .withMessage("El usuario ya lo seguía");
    }

    @Test
    public void seguir_conUsuariosPersistidosValidos_sigueUsuario() {
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .sinSeguidores()
                .buildAndPersist(em);
        Usuario usuario2 = UsuarioBuilder
                .crear()
                .conNombre("mamateo")
                .sinSeguidores()
                .buildAndPersist(em);

        Usuario seguidor = usuarioService.seguir(usuario1.getId(), usuario2.getId());

        assertThat(seguidor.getSeguidos())
                .isNotEmpty()
                .containsOnly(usuario2.getId());
    }

    @Test
    public void seguir_conUsuariosPersistidosValidosYSeguiendoAOtroPreviamente_sigueUsuario() {
        Usuario usuario1 = UsuarioBuilder
                .crear()
                .conNombre("ezortega")
                .sinSeguidores()
                .buildAndPersist(em);
        Usuario usuario2 = UsuarioBuilder
                .crear()
                .conNombre("mamateo")
                .conSeguidores(asList(usuario1.getId()))
                .buildAndPersist(em);
        Usuario usuario3 = UsuarioBuilder
                .crear()
                .conNombre("juperez")
                .sinSeguidores()
                .buildAndPersist(em);

        Usuario seguidor = usuarioService.seguir(usuario2.getId(), usuario3.getId());

        assertThat(seguidor.getSeguidos())
                .isNotEmpty()
                .containsOnly(usuario1.getId(), usuario3.getId());
    }

}
