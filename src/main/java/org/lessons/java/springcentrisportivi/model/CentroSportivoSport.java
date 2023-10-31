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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "giorni_disponibili", nullable = false)
    @NotNull(message = "È necessario dichiarare i giorni in cui è possibile praticare questo sport")
    private Set<String> giorniDisponibili = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relazione N a N tra tabelle 'membri' e 'centri_sportivi', scompattata in 1 a N tra 'sport' e 'centro_sportivo_sport' e 1 a N tra 'centri_sportivi' e 'centro_sportivo_sport'
    //Seguono le due controparti di tali relazioni
    @ManyToOne
    @JoinColumn(name = "id_sport")
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "id_centro_sportivo")
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
