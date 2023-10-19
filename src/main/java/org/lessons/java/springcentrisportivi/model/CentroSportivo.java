package org.lessons.java.springcentrisportivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "centri_sportivi")
public class CentroSportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "E' necessario inserire un nome")
    @Column(nullable = false)
    private String nome;
    private String descrizione;
    @NotBlank(message = "E' necessario inserire un indirizzo")
    @Column(unique = true, nullable = false)
    private String indirizzo;
    @NotBlank(message = "E' necessario inserire una città")
    @Column(nullable = false)
    private String citta;
    @Column(nullable = false)
    @NotNull(message = "E' necessario inserire una quota di iscrizione")
    private Integer quotaIscrizione;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "centroSportivo", cascade = {CascadeType.ALL})
    private List<Membro> membri = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "centroSportivo")
    private CentroSportivoSport centroSportivoSport;

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public void setQuotaIscrizione(Integer quotaIscrizione) {
        this.quotaIscrizione = quotaIscrizione;
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

    public CentroSportivoSport getCentroSportivoSport() {
        return centroSportivoSport;
    }

    public void setCentroSportivoSport(CentroSportivoSport centroSportivoSport) {
        this.centroSportivoSport = centroSportivoSport;
    }
}
