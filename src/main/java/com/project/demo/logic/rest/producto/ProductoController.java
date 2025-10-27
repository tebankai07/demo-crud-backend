package com.project.demo.logic.rest.producto;

import com.project.demo.logic.entity.producto.Producto;
import com.project.demo.logic.entity.producto.ProductoRepository;
import com.project.demo.logic.entity.categoria.CategoriaRepository; // nuevo
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoRepository repo;
    private final CategoriaRepository categoriaRepo; // nuevo

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Producto p) {
        if (p.getCategoria() != null && p.getCategoria().getId() != null) {
            var cat = categoriaRepo.findById(p.getCategoria().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
            p.setCategoria(cat);
        } else {
            return ResponseEntity.badRequest().body("categoriaId es requerido");
        }

        var saved = repo.save(p);
        return ResponseEntity.created(URI.create("/api/productos/" + saved.getId())).body(saved);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Producto p) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setNombre(p.getNombre());
                    existing.setDescripcion(p.getDescripcion());
                    existing.setPrecio(p.getPrecio());
                    existing.setCantidad(p.getCantidad());
                    existing.setCategoria(p.getCategoria());
                    return ResponseEntity.ok(repo.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
