package mr_equitmant.repository;

import mr_equitmant.model.TipoMantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoMantenimientoRepository extends JpaRepository<TipoMantenimiento, Long> {
    Optional<TipoMantenimiento> findByNombre(String nombre);
}