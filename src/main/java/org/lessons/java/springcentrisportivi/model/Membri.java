package org.lessons.java.springcentrisportivi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "membri")
public class Membri {

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
    @ManyToOne
    @JoinColumn(name = "id_centro_sportivo", nullable = false)
    private CentriSportivi centroSportivo;
    @NotNull(message = "E' necessario inserire una data di iscrizione")
    @Column(nullable = false)
    private LocalDate dataIscrizione;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public CentriSportivi getCentroSportivo() {
        return centroSportivo;
    }

    public void setCentroSportivo(CentriSportivi centroSportivo) {
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
}
