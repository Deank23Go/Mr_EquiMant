package mr_equitmant.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_mantenimiento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // "PREVENTIVO", "CORRECTIVO"

    private String descripcion;
}