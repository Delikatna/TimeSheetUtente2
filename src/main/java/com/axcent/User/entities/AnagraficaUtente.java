package com.axcent.User.entities;

import com.axcent.User.entities.enums.Mansione;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnagraficaUtente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome,cognome,sede,numTelefono;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Mansione mansione;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="utente_id", referencedColumnName = "id")
    @JsonBackReference
    private Utente utente;
}
