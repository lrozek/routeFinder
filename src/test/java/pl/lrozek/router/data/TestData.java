package pl.lrozek.router.data;

import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.coordinates.Coordinates;
import pl.lrozek.router.domain.coordinates.Latitude;
import pl.lrozek.router.domain.coordinates.Longitude;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.math.BigDecimal;
import java.util.List;

public interface TestData
{

    VesselId imo_9483671 = new VesselId("imo_9483671");
    VesselId imo_9491472 = new VesselId("imo_9491472");
    VesselId imo_9647473 = new VesselId("imo_9647473");
    VesselId imo_9462794 = new VesselId("imo_9462794");
    Sequence seq_127 = new Sequence(127);
    Sequence seq_128 = new Sequence(128);

    Port debrv = new Port("DEBRV");
    Port deham = new Port("DEHAM");

    Route debrvToDeham = new Route(debrv, deham);


    Coordinates coord1 = new Coordinates(new Longitude(new BigDecimal("8.489074")), new Latitude(new BigDecimal("53.615707")));
    Coordinates coord2 = new Coordinates(new Longitude(new BigDecimal("8.476499")), new Latitude(new BigDecimal("53.621193")));
    Coordinates coord3 = new Coordinates(new Longitude(new BigDecimal("9.88288")), new Latitude(new BigDecimal("53.5429")));
    Coordinates coord4 = new Coordinates(new Longitude(new BigDecimal("9.852458")), new Latitude(new BigDecimal("53.546154")));
    Coordinates coord5 = new Coordinates(new Longitude(new BigDecimal("7.973953")), new Latitude(new BigDecimal("53.857887")));
    Coordinates coord6 = new Coordinates(new Longitude(new BigDecimal("7.957948")), new Latitude(new BigDecimal("53.860317")));

    VesselRoute vesselRoute1 = new VesselRoute(imo_9483671, seq_127, seq_128, debrvToDeham, List.of(coord1, coord2));
    VesselRoute vesselRoute2 = new VesselRoute(imo_9491472, seq_127, seq_128, debrvToDeham, List.of(coord3, coord4, coord5));
    VesselRoute vesselRoute3 = new VesselRoute(imo_9647473, seq_127, seq_128, debrvToDeham, List.of(coord1, coord6));
    VesselRoute vesselRoute4 = new VesselRoute(imo_9462794, seq_127, seq_128, debrvToDeham, List.of(coord1, coord2, coord3, coord6));
}
