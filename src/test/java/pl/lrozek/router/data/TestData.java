package pl.lrozek.router.data;

import pl.lrozek.router.domain.coordinates.Coordinates;
import pl.lrozek.router.domain.coordinates.Latitude;
import pl.lrozek.router.domain.coordinates.Longitude;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.math.BigDecimal;

public interface TestData
{

    VesselId imo_9462794 = new VesselId("imo_9462794");
    Sequence seq_127 = new Sequence(127);
    Sequence seq_128 = new Sequence(128);

    Port debrv = new Port("DEBRV");
    Port deham = new Port("DEHAM");

    Route debrvToDeham = new Route(debrv, deham);


    Coordinates coord1 = new Coordinates(new Longitude(new BigDecimal("8.489074")), new Latitude(new BigDecimal("53.615707")));
    Coordinates coord2 = new Coordinates(new Longitude(new BigDecimal("8.476499")), new Latitude(new BigDecimal("53.621193")));

}
