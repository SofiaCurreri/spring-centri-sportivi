package org.lessons.java.springcentrisportivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "E' necessario inserire un nome")
    @Column(unique = true, nullable = false)
    private String nome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "sports")
    private List<Membro> membri = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    private List<CentroSportivoSport> centroSportivoSports;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Membro> getMembri() {
        return membri;
    }

    public void setMembri(List<Membro> membri) {
        this.membri = membri;
    }

    public List<CentroSportivoSport> getCentroSportivoSports() {
        return centroSportivoSports;
    }

    public void setCentroSportivoSports(List<CentroSportivoSport> centroSportivoSports) {
        this.centroSportivoSports = centroSportivoSports;
    }
}
