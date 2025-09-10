package mr_equitmant.service;

import mr_equitmant.model.Equipo;
import mr_equitmant.model.Mantenimiento;
import mr_equitmant.model.TipoMantenimiento;
import mr_equitmant.repository.EquipoRepository;
import mr_equitmant.repository.MantenimientoRepository;
import mr_equitmant.repository.TipoMantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private TipoMantenimientoRepository tipoMantenimientoRepository;

    public List<Mantenimiento> listarTodos() {
        return mantenimientoRepository.findAll();
    }

    public Optional<Mantenimiento> buscarPorId(Long id) {
        return mantenimientoRepository.findById(id);
    }

    public Mantenimiento programarMantenimiento(Mantenimiento mantenimiento) {
        // Validar equipo
        Equipo equipo = equipoRepository.findById(mantenimiento.getEquipo().getId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + mantenimiento.getEquipo().getId()));

        // Validar tipo de mantenimiento
        TipoMantenimiento tipo = tipoMantenimientoRepository.findById(mantenimiento.getTipoMantenimiento().getId())
                .orElseThrow(() -> new RuntimeException("Tipo de mantenimiento no encontrado con ID: " + mantenimiento.getTipoMantenimiento().getId()));

        mantenimiento.setEquipo(equipo);
        mantenimiento.setTipoMantenimiento(tipo);
        return mantenimientoRepository.save(mantenimiento);
    }

    public Mantenimiento actualizarEstado(Long id, String nuevoEstado, LocalDate fechaEjecucion) {
        Mantenimiento m = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado con ID: " + id));

        m.setEstado(nuevoEstado);
        if (fechaEjecucion != null) {
            m.setFechaEjecucion(fechaEjecucion);
        }

        return mantenimientoRepository.save(m);
    }

    public List<Mantenimiento> listarPorEstado(String estado) {
        return mantenimientoRepository.findByEstado(estado);
    }
}