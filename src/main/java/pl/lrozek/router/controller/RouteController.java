package pl.lrozek.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.geo.Feature;
import pl.lrozek.router.finder.VesselRouteFinder;

import java.util.Optional;

@RestController
public class RouteController
{

    @Autowired
    private VesselRouteFinder vesselRouteFinder;

    @Autowired
    private GeoJsonMapper geoJsonMapper;

    @GetMapping
    public ResponseEntity<Feature> findRouter(@RequestParam Port from, @RequestParam Port to)
    {
        Route route = new Route(from, to);
        Optional<Feature> vesselRoute = vesselRouteFinder.findRoute(route).map(geoJsonMapper::map);
        return vesselRoute.map(vesselRoute1 -> ResponseEntity.ok(vesselRoute1)).orElse(ResponseEntity.notFound().build());
    }
}
