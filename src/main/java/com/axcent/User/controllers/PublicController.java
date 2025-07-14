package com.axcent.User.controllers;

import com.axcent.User.entities.AnagraficaUtente;
import com.axcent.User.services.AnagraficaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PublicController
{
    public final AnagraficaService anagraficaService;

    @GetMapping("/{id}")
    public AnagraficaUtente getUtenteById(@PathVariable() Long id)
    {
        return anagraficaService.findByIdUtente(id);
    }
}
