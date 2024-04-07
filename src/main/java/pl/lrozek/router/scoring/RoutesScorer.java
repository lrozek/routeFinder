package pl.lrozek.router.scoring;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.stereotype.Component;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.coordinates.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoutesScorer
{

    public Map<VesselRoute, Integer> score(List<VesselRoute> vesselRoutes)
    {
        Multimap<Coordinates, VesselRoute> commonCoordinates = ArrayListMultimap.create();
        vesselRoutes.stream().forEach(vesselRoute -> vesselRoute.coordinates().stream().forEach(coordinates -> commonCoordinates.put(coordinates, vesselRoute)));
        Map<VesselRoute, Integer> score = vesselRoutes.stream().collect(Collectors.toMap(vesselRoute -> vesselRoute, vesselRoute -> 0));
        commonCoordinates.asMap().forEach((coordinates, scoredVesselRoutes) ->
                {
                    if (scoredVesselRoutes.size() > 1)
                    {
                        scoredVesselRoutes.forEach(vesselRoute -> score.computeIfPresent(vesselRoute, (key, currentScore) -> currentScore + 1));
                    }
                }
        );
        return score;
    }
}
