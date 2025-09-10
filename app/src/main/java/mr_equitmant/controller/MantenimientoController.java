package mr_equitmant.controller;

import mr_equitmant.model.Mantenimiento;
import mr_equitmant.service.MantenimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@Tag(name = "Mantenimientos", description = "Gestión de Mantenimientos Preventivos y Correctivos")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;

    @Operation(summary = "Listar todos los mantenimientos")
    @GetMapping
    public ResponseEntity<List<Mantenimiento>> listarMantenimientos() {
        return ResponseEntity.ok(mantenimientoService.listarTodos());
    }

    @Operation(summary = "Programar un nuevo mantenimiento")
    @PostMapping
    public ResponseEntity<Mantenimiento> programarMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevo = mantenimientoService.programarMantenimiento(mantenimiento);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();
        return ResponseEntity.created(location).body(nuevo);
    }

    @Operation(summary = "Actualizar estado de un mantenimiento")
    @PutMapping("/{id}/estado")
    public ResponseEntity<Mantenimiento> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam(required = false) LocalDate fechaEjecucion) {
        Mantenimiento actualizado = mantenimientoService.actualizarEstado(id, estado, fechaEjecucion);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Listar mantenimientos pendientes")
    @GetMapping("/pendientes")
    public ResponseEntity<List<Mantenimiento>> listarPendientes() {
        return ResponseEntity.ok(mantenimientoService.listarPorEstado("PENDIENTE"));
    }
}