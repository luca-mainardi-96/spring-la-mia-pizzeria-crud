package esercizio.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esercizio.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

}
