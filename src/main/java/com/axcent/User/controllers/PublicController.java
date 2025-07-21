package com.axcent.User.controllers;

import com.axcent.User.dto.AnagraficaUtenteDto;
import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.exception.UtenteNonTrovatoException;
import com.axcent.User.services.AnagraficaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PublicController
{
    private final AnagraficaService anagraficaService;

    @GetMapping("/{id}")
    public ResponseEntity<AnagraficaUtenteDto> getUtenteById(@PathVariable Long id) {
        AnagraficaUtente utente = anagraficaService.findByIdUtente(id);
        if (utente == null) {
            throw  new UtenteNonTrovatoException("Non ci sono utenti con quell'id");
        }
        return ResponseEntity.ok(new AnagraficaUtenteDto(utente));
    }
}
