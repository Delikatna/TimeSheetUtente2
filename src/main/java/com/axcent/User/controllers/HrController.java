package com.axcent.User.controllers;

import com.axcent.User.dto.AnagraficaUtenteDto;
import com.axcent.User.dto.RegistrazioneDto;
import com.axcent.User.dto.UtenteDto;
import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.entities.Utente;
import com.axcent.User.services.AnagraficaService;
import com.axcent.User.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HrController
{
    private final UtenteService utenteService;
    private final AnagraficaService anagraficaService;

    @PostMapping("/registrazione")
    public ResponseEntity<?> registrazione(@RequestBody RegistrazioneDto registrazione)
    {
        try{
            utenteService.registrazioneUtente(registrazione.getUtente());
            anagraficaService.registrazioneAnagrafica(registrazione.getAnagraficaUtente(),registrazione.getUtente());
            return ResponseEntity.ok(Map.of("message","Registrazione avvenuta con successo"));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public AnagraficaUtente getUtenteById(@PathVariable() Long id)
    {
        return anagraficaService.findByIdUtente(id);
    }

    @PostMapping("/aggiorna/{id}")
    public ResponseEntity<?> aggiornaAnagrafica
            (
                    @RequestBody AnagraficaUtente anagraficaUtente,
                    @PathVariable Long id
            )
    {
        return anagraficaService.modificaAnagrafica(anagraficaUtente, id);
    }

    @GetMapping("/tutte")
    public List<UtenteDto> getTutteAnagrafiche()
    {
        List<UtenteDto> tutte = utenteService.getAllAnagraficaUtenti();
        if (tutte.isEmpty())
            throw new RuntimeException("Non ci sono utenti disponibili");

        return tutte;
    }
}
