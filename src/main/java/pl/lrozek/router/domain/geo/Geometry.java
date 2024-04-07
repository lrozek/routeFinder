package pl.lrozek.router.domain.geo;

import pl.lrozek.router.domain.coordinates.Coordinates;

import java.util.List;

public record Geometry(String type, List<Coordinates> coordinates)
{
}
