package com.axcent.User.controllers;

import com.axcent.User.dto.UtenteDto;
import com.axcent.User.entities.Utente;
import com.axcent.User.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UtenteController
{
    private final UtenteService utenteService;

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDto> getUtenteById(@PathVariable Long id, Principal principal) {
        String username = principal.getName(); // preso dal JWT
        Utente utente = utenteService.findByUsername(username);

        if (!utente.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // accesso negato
        }

        UtenteDto uDto = utenteService.findById(id);
        return ResponseEntity.ok(uDto);
    }

}
