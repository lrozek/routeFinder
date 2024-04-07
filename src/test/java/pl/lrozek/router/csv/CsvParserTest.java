package pl.lrozek.router.csv;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import pl.lrozek.router.data.TestData;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.coordinates.Coordinates;

import java.io.IOException;
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
        MatcherAssert.assertThat(points.get(0), Matchers.equalTo(TestData.coord1));
        MatcherAssert.assertThat(points.get(1), Matchers.equalTo(TestData.coord2));

    }

    private static void assertVesselRoute(VesselRoute vesselRoute)
    {
        MatcherAssert.assertThat(vesselRoute, Matchers.notNullValue());
        MatcherAssert.assertThat(vesselRoute.vesselId(), Matchers.equalTo(TestData.imo_9462794));
        MatcherAssert.assertThat(vesselRoute.from(), Matchers.equalTo(TestData.seq_127));
        MatcherAssert.assertThat(vesselRoute.to(), Matchers.equalTo(TestData.seq_128));
        MatcherAssert.assertThat(vesselRoute.route(), Matchers.equalTo(TestData.debrvToDeham));
        assertPoints(vesselRoute.coordinates());

    }

    private static void assertVesselRoutes(List<VesselRoute> vesselRoutes)
    {
        MatcherAssert.assertThat(vesselRoutes, Matchers.notNullValue());
        MatcherAssert.assertThat(vesselRoutes, Matchers.hasSize(2));
    }

}
