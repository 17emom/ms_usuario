package ar.com.uala.ms_usuario.builder;

import java.util.ArrayList;
import java.util.List;

import ar.com.uala.ms_usuario.domain.Usuario;
import jakarta.persistence.EntityManager;

public class UsuarioBuilder {

    private Long id;
    private String nombre;
    private List<Long> seguidores;

    public static UsuarioBuilder crear() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder conSeguidores(List<Long> seguidores) {
        this.seguidores = seguidores;
        return this;
    }

    public UsuarioBuilder sinSeguidores() {
        this.seguidores = new ArrayList<>();
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nombre, seguidores);
    }

    public Usuario buildAndPersist(EntityManager em) {
        Usuario usuario = this.build();
        em.persist(usuario);
        em.flush();
        em.detach(usuario);
        return usuario;
    }
}

