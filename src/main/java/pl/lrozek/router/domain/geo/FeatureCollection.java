package pl.lrozek.router.domain.geo;

import java.util.List;

public record FeatureCollection(List<Feature> features, String type)
{
}
