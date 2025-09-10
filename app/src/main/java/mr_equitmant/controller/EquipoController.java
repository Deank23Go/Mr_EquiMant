package mr_equitmant.controller;

import mr_equitmant.model.Equipo;
import mr_equitmant.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipos", description = "Gestión CRUD de Equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Operation(summary = "Listar todos los equipos")
    @GetMapping
    public ResponseEntity<List<Equipo>> listarEquipos() {
        return ResponseEntity.ok(equipoService.listarTodos());
    }

    @Operation(summary = "Obtener equipo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable Long id) {
        return equipoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear nuevo equipo")
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo nuevo = equipoService.guardar(equipo);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();
        return ResponseEntity.created(location).body(nuevo);
    }

    @Operation(summary = "Actualizar equipo")
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        if (!equipoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        equipo.setId(id);
        Equipo actualizado = equipoService.guardar(equipo);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar equipo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}