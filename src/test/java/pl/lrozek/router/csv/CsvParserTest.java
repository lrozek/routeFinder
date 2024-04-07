package pl.lrozek.router.csv;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.coordinates.Coordinates;
import pl.lrozek.router.domain.coordinates.Latitude;
import pl.lrozek.router.domain.coordinates.Longitude;
import pl.lrozek.router.domain.destination.Port;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.domain.ids.Sequence;
import pl.lrozek.router.domain.ids.VesselId;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class CsvParserTest
{
    @Autowired
    private CsvParser csvParser;

    @Value("classpath:csv/DEBRV_DEHAM_two_historical_routes.csv")
    Resource csvFile;

    @Test
    public void itShouldParseCsvFile() throws IOException
    {
        //given

        //when
        List<VesselRoute> vesselRoutes = csvParser.parse(csvFile.getInputStream());

        //then
        assertVesselRoutes(vesselRoutes);
        assertVesselRoute(vesselRoutes.get(0));
    }


    private static void assertPoints(List<Coordinates> points)
    {
        MatcherAssert.assertThat(points, Matchers.notNullValue());
        MatcherAssert.assertThat(points, Matchers.hasSize(2));
        MatcherAssert.assertThat(points.get(0), Matchers.equalTo(new Coordinates(new Longitude(new BigDecimal("8.489074")), new Latitude(new BigDecimal("53.615707")))));
        MatcherAssert.assertThat(points.get(1), Matchers.equalTo(new Coordinates(new Longitude(new BigDecimal("8.476499")), new Latitude(new BigDecimal("53.621193")))));

    }

    private static void assertVesselRoute(VesselRoute vesselRoute)
    {
        MatcherAssert.assertThat(vesselRoute, Matchers.notNullValue());
        MatcherAssert.assertThat(vesselRoute.vesselId(), Matchers.equalTo(new VesselId("imo_9462794")));
        MatcherAssert.assertThat(vesselRoute.from(), Matchers.equalTo(new Sequence(127)));
        MatcherAssert.assertThat(vesselRoute.to(), Matchers.equalTo(new Sequence(128)));
        MatcherAssert.assertThat(vesselRoute.route(), Matchers.equalTo(new Route(new Port("DEBRV"), new Port("DEHAM"))));
        //assertPoints(vesselRoute.coordinates());

    }

    private static void assertVesselRoutes(List<VesselRoute> vesselRoutes)
    {
        MatcherAssert.assertThat(vesselRoutes, Matchers.notNullValue());
        MatcherAssert.assertThat(vesselRoutes, Matchers.hasSize(2));
    }

}
