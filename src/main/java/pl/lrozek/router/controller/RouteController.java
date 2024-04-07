package pl.lrozek.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.finder.VesselRouteFinder;

import java.util.Optional;

@RestController
public class RouteController
{

    @Autowired
    private VesselRouteFinder vesselRouteFinder;

    @GetMapping
    public ResponseEntity<VesselRoute> findRouter(@RequestParam Port from, @RequestParam Port to)
    {
        Route route = new Route(from, to);
        Optional<VesselRoute> vesselRoute = vesselRouteFinder.findRoute(route);
        return vesselRoute.map(vesselRoute1 -> ResponseEntity.ok(vesselRoute1)).orElse(ResponseEntity.notFound().build());
    }
}
