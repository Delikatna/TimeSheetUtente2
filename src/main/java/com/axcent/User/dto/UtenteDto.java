package com.axcent.User.dto;

import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.entities.Utente;
import com.axcent.User.entities.enums.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDto
{
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String sede;
    private String numTelefono;
    private Date dob;
    private String mansione;

    public UtenteDto(Utente ut, AnagraficaUtente au)
    {
        this.id=ut.getId();
        this.username= ut.getUsername();
        this.nome = au.getNome();
        this.cognome = au.getCognome();
        this.dob = au.getDob();
        this.sede = au.getSede();
        this.numTelefono = au.getNumTelefono();
        this.mansione = au.getMansione().toString();
    }
}
