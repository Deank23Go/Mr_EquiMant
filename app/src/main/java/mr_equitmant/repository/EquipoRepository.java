package mr_equitmant.repository;

import mr_equitmant.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByNumeroSerie(String numeroSerie);
    Optional<Equipo> findByNumeroSerie(String numeroSerie);
}