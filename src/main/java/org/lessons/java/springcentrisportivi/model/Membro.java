package org.lessons.java.springcentrisportivi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membri")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "E' necessario inserire un nome")
    @Column(nullable = false)
    private String nome;
    @NotBlank(message = "E' necessario inserire un cognome")
    @Column(nullable = false)
    private String cognome;
    @NotNull(message = "E' necessario inserire una data di nascita")
    @Past(message = "La data deve essere nel passato")
    @Column(nullable = false)
    private LocalDate dataDiNascita;
    @NotBlank(message = "E' necessario inserire un indirizzo email")
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull(message = "E' necessario inserire una data di iscrizione")
    @Column(nullable = false)
    private LocalDate dataIscrizione;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relazione N a 1 con tabella 'centri_sportivi'
    @ManyToOne
    @JoinColumn(name = "id_centro_sportivo", nullable = false)
    private CentroSportivo centroSportivo;

    //Relazione N a N con tabella 'sports'
    @ManyToMany
    @JoinTable(name = "membro_sport", joinColumns = @JoinColumn(name = "id_membro"), inverseJoinColumns = @JoinColumn(name = "id_sport"))
    private List<Sport> sports = new ArrayList<>();

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CentroSportivo getCentroSportivo() {
        return centroSportivo;
    }

    public void setCentroSportivo(CentroSportivo centroSportivo) {
        this.centroSportivo = centroSportivo;
    }

    public LocalDate getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(LocalDate dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
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

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
}
