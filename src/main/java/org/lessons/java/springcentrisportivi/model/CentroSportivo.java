package org.lessons.java.springcentrisportivi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "centri_sportivi")
public class CentroSportivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    private String descrizione;
    @NotBlank(message = "È necessario inserire un indirizzo")
    @Column(unique = true, nullable = false)
    private String indirizzo;
    @NotBlank(message = "È necessario inserire una città")
    @Column(nullable = false)
    private String citta;
    @Column(nullable = false)
    @NotNull(message = "È necessario inserire una quota di iscrizione")
    private Integer quotaIscrizione;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Relazione 1 a N con tabella 'membri'
    @JsonIgnore
    @OneToMany(mappedBy = "centroSportivo", cascade = {CascadeType.ALL})
    private List<Membro> membri = new ArrayList<>();

    //Relazione N a N tra tabelle 'membri' e 'centri_sportivi', scompattata in 1 a N tra 'centri_sportivi' e 'centro_sportivo_sport' e 1 a N tra 'sport' e 'centro_sportivo_sport'
    @JsonIgnore
    @OneToMany(mappedBy = "centroSportivo")
    private Set<CentroSportivoSport> sports = new HashSet<>();

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

    public Set<CentroSportivoSport> getSports() {
        return sports;
    }

    public void setSports(Set<CentroSportivoSport> sports) {
        this.sports = sports;
    }

//    public List<Sport> getSportDisponibili() {
//        List<Sport> sportDisponibili = new ArrayList<>();
//        for (CentroSportivoSport centroSportivoSport : sports) {
//            sportDisponibili.add(centroSportivoSport.getSport());
//        }
//        return sportDisponibili;
//    }
}
