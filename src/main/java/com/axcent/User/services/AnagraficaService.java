package com.axcent.User.services;

import com.axcent.User.dto.AnagraficaUtenteDto;
import com.axcent.User.dto.UtenteDto;
import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.entities.Utente;
import com.axcent.User.repositories.AnagraficaUtenteDao;
import com.axcent.User.repositories.UtenteDao;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnagraficaService
{
    private final AnagraficaUtenteDao adao;
    private final UtenteDao utenteDao;
    public AnagraficaUtente registrazioneAnagrafica(AnagraficaUtente au,Utente utente)
    {
        au.setNome(au.getNome());
        au.setCognome(au.getCognome());
        au.setNumTelefono(au.getNumTelefono());
        au.setDob(au.getDob());
        au.setSede(au.getSede());
        au.setMansione(au.getMansione());
        au.setUtente(utente);

        return adao.save(au);

    }

    public AnagraficaUtente findByIdUtente(Long id)
    {
        return adao.findByUtenteId(id);
    }

     public ResponseEntity<?> modificaAnagrafica(AnagraficaUtente nuovaAnagrafica, Long id)
     {
         Optional<AnagraficaUtente> anagraficaUtente = adao.findById(id);

         if (anagraficaUtente.isEmpty())
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Anagrafica non trovata per l'ID specificato.");

         AnagraficaUtente anagraficaEsistente = anagraficaUtente.get();

         anagraficaEsistente.setNome(nuovaAnagrafica.getNome());
         anagraficaEsistente.setCognome(nuovaAnagrafica.getCognome());
         anagraficaEsistente.setDob(nuovaAnagrafica.getDob());
         anagraficaEsistente.setNumTelefono(nuovaAnagrafica.getNumTelefono());
         anagraficaEsistente.setMansione(nuovaAnagrafica.getMansione());
         anagraficaEsistente.setSede(nuovaAnagrafica.getSede());

         adao.save(anagraficaEsistente);
         return ResponseEntity.status(HttpStatus.OK).body("Angrafica aggiornata con successo!");
     }


}
