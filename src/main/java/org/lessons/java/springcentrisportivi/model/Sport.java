package org.lessons.java.springcentrisportivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "È necessario inserire un nome")
    @Column(unique = true, nullable = false)
    private String nome;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relazione N a N con tabella 'membri'
    @JsonIgnore
    @ManyToMany(mappedBy = "sports")
    private List<Membro> membri = new ArrayList<>();

    //Relazione N a N tra tabelle 'membri' e 'centri_sportivi', scompattata in 1 a N tra 'centri_sportivi' e 'centro_sportivo_sport' e 1 a N tra 'sport' e 'centro_sportivo_sport'
    @JsonIgnore
    @OneToMany(mappedBy = "sport")
    private Set<CentroSportivoSport> centriSportivi = new HashSet<>();

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

    public Set<CentroSportivoSport> getCentriSportivi() {
        return centriSportivi;
    }

    public void setCentriSportivi(Set<CentroSportivoSport> centriSportivi) {
        this.centriSportivi = centriSportivi;
    }
}
