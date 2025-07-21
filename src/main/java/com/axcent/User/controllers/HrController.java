package com.axcent.User.controllers;

import com.axcent.User.dto.AnagraficaUtenteDto;
import com.axcent.User.dto.RegistrazioneDto;
import com.axcent.User.dto.UtenteDto;
import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.exception.RegistrazioneException;
import com.axcent.User.exception.UtenteNonTrovatoException;
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
public class HrController {

    private final UtenteService utenteService;
    private final AnagraficaService anagraficaService;

    /**
     * Registra un nuovo utente e la relativa anagrafica.
     * @param registrazione DTO contenente utente e anagrafica
     * @return messaggio di successo o errore
     */
    @PostMapping("/registra-utenti")
    public ResponseEntity<?> registrazione(@RequestBody RegistrazioneDto registrazione) {
        try {
            utenteService.registrazioneUtente(registrazione.getUtente());
            anagraficaService.registrazioneAnagrafica(
                    registrazione.getAnagraficaUtente(),
                    registrazione.getUtente()
            );
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo"));
        } catch (IllegalArgumentException e) {
            throw new RegistrazioneException("Errore nella registrazione: " + e.getMessage());
        }
    }

    /**
     * Restituisce l'anagrafica associata all'utente con l'id specificato.
     * @param id id dell'utente
     * @return DTO dell'anagrafica utente
     */
    @GetMapping("/utenti/{id}/anagrafica")
    public ResponseEntity<AnagraficaUtenteDto> getUtenteById(@PathVariable Long id) {
        AnagraficaUtente entity = anagraficaService.findByIdUtente(id);
        return ResponseEntity.ok(new AnagraficaUtenteDto(entity));
    }

    /**
     * Aggiorna l'anagrafica utente con il contenuto fornito.
     * @param anagraficaUtente nuovo oggetto anagrafica
     * @param id id dell'utente
     * @return risposta generica
     */
    @PostMapping("/utenti/aggiorna/{id}")
    public ResponseEntity<?> aggiornaAnagrafica(
            @RequestBody AnagraficaUtente anagraficaUtente,
            @PathVariable Long id
    ) {
        return anagraficaService.modificaAnagrafica(anagraficaUtente, id);
    }

    /**
     * Restituisce tutte le anagrafiche utenti.
     * @return lista di DTO
     */
    @GetMapping("/utenti    ")
    public List<UtenteDto> getTutteAnagrafiche() {
        List<UtenteDto> tutte = utenteService.getAllAnagraficaUtenti();
        if (tutte.isEmpty()) {
            throw new UtenteNonTrovatoException("Non ci sono utenti disponibili");
        }
        return tutte;
    }
}