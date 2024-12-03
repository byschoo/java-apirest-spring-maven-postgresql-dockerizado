package com.apirest.postgresql.dockerizado.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.postgresql.dockerizado.Entities.Productos;

public interface IProductoRepository extends JpaRepository<Productos, Long>{

}
