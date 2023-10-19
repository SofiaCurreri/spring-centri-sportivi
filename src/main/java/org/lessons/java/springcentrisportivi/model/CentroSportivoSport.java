package org.lessons.java.springcentrisportivi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "centro_sportivo_sport")
public class CentroSportivoSport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "E' necessario dichiarare i giorni in cui è possibile praticare questo sport")
    private Set<String> giorniDisponibili = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_sport", nullable = false)
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "id_centro_sportivo", nullable = false)
    private CentroSportivo centroSportivo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<String> getGiorniDisponibili() {
        return giorniDisponibili;
    }

    public void setGiorniDisponibili(Set<String> giorniDisponibili) {
        this.giorniDisponibili = giorniDisponibili;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public CentroSportivo getCentroSportivo() {
        return centroSportivo;
    }

    public void setCentroSportivo(CentroSportivo centroSportivo) {
        this.centroSportivo = centroSportivo;
    }
}
