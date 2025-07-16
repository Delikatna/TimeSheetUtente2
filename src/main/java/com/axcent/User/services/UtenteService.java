package com.axcent.User.services;

import com.axcent.User.dto.UtenteDto;
import com.axcent.User.entities.Utente;
import com.axcent.User.entities.enums.Ruolo;
import com.axcent.User.repositories.UtenteDao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteService
{
    private final UtenteDao udao;
    private final PasswordEncoder passwordEncoder;

    public Utente registrazioneUtente(Utente ut)
    {

        ut.setRuolo(Ruolo.USER);
        ut.setPassword(passwordEncoder.encode(ut.getPassword()));

        return udao.save(ut);
    }

    public Utente findByUsername(String username)
    {
        Utente ut = udao.findByUsername(username);
        if(ut==null)
            throw new RuntimeException("non è stato trovato nessun utente");


        return ut;
    }

    public UtenteDto findById(Long id)
    {
        UtenteDto ut = udao.findById(id)
                .map(utente -> new UtenteDto(utente, utente.getAnagraficaUtente()))
                .orElseThrow(() -> new EntityNotFoundException("Utente con id " + id + " non trovato"));
        return ut;
    }

    public List<UtenteDto> getAllAnagraficaUtenti()
    {
        return udao.findAll().stream().map(utente -> new UtenteDto(utente, utente.getAnagraficaUtente())).toList();
    }
}
