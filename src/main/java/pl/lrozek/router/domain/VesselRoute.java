package pl.lrozek.router.domain;

import pl.lrozek.router.domain.coordinates.Coordinates;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.util.List;

public record VesselRoute(VesselId vesselId, Sequence from, Sequence to, Route route, List<Coordinates> coordinates)
{
}
