package com.davidvelilla.plazamayor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.davidvelilla.plazamayor.model.Towns;
import com.davidvelilla.plazamayor.service.TownService;
import com.davidvelilla.plazamayor.exception.BaseException;
import com.davidvelilla.plazamayor.model.Town;
import java.util.Collection;

@Controller
@RequestMapping(value = {"/towns"})
public class TownController
{
    private final TownService townService;

    @Autowired
    public TownController(TownService townService)
    {
        this.townService = townService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{town}", produces = "application/json")
    public ResponseEntity findTown(@PathVariable("town") String town)
    {
        Towns towns = new Towns();

        try {
            int townId = Integer.parseInt(town);
            Town result = this.townService.findTownById(townId);
            towns.getTownList().add(result);
        } catch (NumberFormatException e) {
            // not an integer!
            try {
                Collection<Town> results = this.townService.findTownsByName(town);
                towns.getTownList().addAll(results);
            } catch (BaseException be) {
                return ResponseEntity.status(be.getStatusCode()).body(be.getJsonMessage());
            }
        }

        return ResponseEntity.ok().body(towns);
    }
}
