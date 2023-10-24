package org.lessons.java.springcentrisportivi.repository;

import org.lessons.java.springcentrisportivi.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Integer> {

    //per l' unicità del nome
    boolean existsByNome(String nome);
}
