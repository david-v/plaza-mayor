package com.davidvelilla.plazamayor.controller;

import com.davidvelilla.plazamayor.model.Region;
import com.davidvelilla.plazamayor.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/regions"})
public class RegionController
{
    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService)
    {
        this.regionService = regionService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{regionId}", produces = "application/json")
    public @ResponseBody ResponseEntity findRegion(@PathVariable("regionId") int regionId)
    {
        Region region = this.regionService.findRegionById(regionId);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(region);
    }
}
