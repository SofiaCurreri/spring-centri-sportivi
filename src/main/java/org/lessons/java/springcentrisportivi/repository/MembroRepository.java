package org.lessons.java.springcentrisportivi.repository;

import org.lessons.java.springcentrisportivi.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Integer> {

    //per unique constraint dato all' attributo email
    boolean existsByEmail(String email);
}
