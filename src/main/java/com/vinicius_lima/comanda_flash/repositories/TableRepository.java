package com.vinicius_lima.comanda_flash.repositories;

import com.vinicius_lima.comanda_flash.dto.TableDTO;
import com.vinicius_lima.comanda_flash.entities.Table;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {

    Optional<Object> findByNumber(Integer tableNumber);

    @EntityGraph(attributePaths = {"orders"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT DISTINCT t FROM Table t JOIN FETCH t.orders o WHERE o.status = 'Aberta'")
    List<Table> findByHasOpenOrders();
}
