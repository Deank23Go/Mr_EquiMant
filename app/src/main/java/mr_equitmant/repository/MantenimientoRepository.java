package mr_equitmant.repository;

import mr_equitmant.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
    List<Mantenimiento> findByEquipoId(Long equipoId);
    List<Mantenimiento> findByEstado(String estado);
}