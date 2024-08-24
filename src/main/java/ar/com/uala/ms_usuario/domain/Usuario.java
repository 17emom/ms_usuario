package ar.com.uala.ms_usuario.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ElementCollection
    @CollectionTable(
            name = "seguidor",
            joinColumns = @JoinColumn(name = "seguidor_id")
    )
    @Column(name = "seguido_id")
    private List<Long> seguidos;
}
