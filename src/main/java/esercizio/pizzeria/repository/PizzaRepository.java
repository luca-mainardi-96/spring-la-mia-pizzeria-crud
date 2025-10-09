package esercizio.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import esercizio.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

   List<Pizza> findByNameContainingIgnoreCase(String name);

}
