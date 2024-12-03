package com.apirest.postgresql.dockerizado.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.postgresql.dockerizado.Entities.Productos;
import com.apirest.postgresql.dockerizado.Repositories.IProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private IProductoRepository productoRepository;

    @GetMapping
    public List<Productos> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Productos getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    @PostMapping
    public Productos createProducto(@RequestBody Productos producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Productos updateProducto(@PathVariable Long id, @RequestBody Productos detailProducto) {
        Productos producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(detailProducto.getNombre());
        producto.setPrecio(detailProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Productos producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);
        return "Se ha borrado el producto con el ID: " + id;
    }
    
}

