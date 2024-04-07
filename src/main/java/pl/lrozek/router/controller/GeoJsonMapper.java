package pl.lrozek.router.controller;

import org.springframework.stereotype.Component;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.geo.Feature;
import pl.lrozek.router.domain.geo.FeatureCollection;
import pl.lrozek.router.domain.geo.Geometry;
import pl.lrozek.router.domain.geo.Properties;
import pl.lrozek.router.domain.ids.Id;

import java.util.List;

@Component
public class GeoJsonMapper
{

    private String stroke = "red";
    private double opacity = 0.3;

    private String featureType = "Feature";

    private String geometryType = "LineString";
    private String featureCollectionType = "FeatureCollection";

    public FeatureCollection map(VesselRoute vesselRoute)
    {
        Id id = new Id(vesselRoute.from(), vesselRoute.to());
        Properties properties = new Properties(id, vesselRoute.route().origin(), vesselRoute.route().destination(), vesselRoute.vesselId(), stroke, opacity);
        Geometry geometry = new Geometry(geometryType, vesselRoute.coordinates());
        Feature feature = new Feature(featureType, geometry, properties);
        return new FeatureCollection(List.of(feature), featureCollectionType);
    }
}
