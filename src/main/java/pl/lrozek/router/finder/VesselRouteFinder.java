package pl.lrozek.router.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lrozek.router.dao.VesselRoutesDao;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.scoring.RoutesScorer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class VesselRouteFinder
{


    private RoutesScorer routesScorer;
    private VesselRoutesDao vesselRoutesDao;

    @Autowired
    public VesselRouteFinder(RoutesScorer routesScorer, VesselRoutesDao vesselRoutesDao)
    {
        this.routesScorer = routesScorer;
        this.vesselRoutesDao = vesselRoutesDao;
    }

    public Optional<VesselRoute> findRoute(Route route)
    {
        List<VesselRoute> vesselRoutes = vesselRoutesDao.find(route);
        Map<VesselRoute, Integer> scoreboard = routesScorer.score(vesselRoutes);
        //reduce simulates findLast
        return scoreboard.entrySet().stream().sorted(Map.Entry.comparingByValue()).reduce((first, second) -> second).map(entry -> entry.getKey());
    }
}
