package pl.lrozek.router.scorer;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pl.lrozek.router.data.TestData;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.scoring.RoutesScorer;

import java.util.List;
import java.util.Map;


public class RoutesScorerUnitTest
{
    private RoutesScorer scorer = new RoutesScorer();

    private VesselRoute route1 = TestData.vesselRoute1;
    private VesselRoute route2 = TestData.vesselRoute2;
    private VesselRoute route3 = TestData.vesselRoute3;
    private VesselRoute route4 = TestData.vesselRoute4;


    @Test
    public void itShouldScoreSingleRoute()
    {
        //given
        List<VesselRoute> vesselRoutes = List.of(route1);

        //when
        Map<VesselRoute, Integer> score = scorer.score(vesselRoutes);

        //then
        MatcherAssert.assertThat(score, Matchers.notNullValue());
        MatcherAssert.assertThat(score, Matchers.aMapWithSize(vesselRoutes.size()));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route1, 0));
    }

    @Test
    public void itShouldScoreRoutesWithoutCommonCoords()
    {
        //given
        List<VesselRoute> vesselRoutes = List.of(route1, route2);

        //when
        Map<VesselRoute, Integer> score = scorer.score(vesselRoutes);

        //then
        MatcherAssert.assertThat(score, Matchers.notNullValue());
        MatcherAssert.assertThat(score, Matchers.aMapWithSize(vesselRoutes.size()));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route1, 0));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route2, 0));
    }

    @Test
    public void itShouldScoreRoutesWithCommonCoords()
    {
        //given
        List<VesselRoute> vesselRoutes = List.of(route1, route2, route3);

        //when
        Map<VesselRoute, Integer> score = scorer.score(vesselRoutes);

        //then
        MatcherAssert.assertThat(score, Matchers.notNullValue());
        MatcherAssert.assertThat(score, Matchers.aMapWithSize(vesselRoutes.size()));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route1, 1));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route2, 0));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route3, 1));
    }

    @Test
    public void itShouldScoreRoutesWithMoreCommonCoords()
    {
        //given
        List<VesselRoute> vesselRoutes = List.of(route1, route2, route3, route4);

        //when
        Map<VesselRoute, Integer> score = scorer.score(vesselRoutes);

        //then
        MatcherAssert.assertThat(score, Matchers.notNullValue());
        MatcherAssert.assertThat(score, Matchers.aMapWithSize(vesselRoutes.size()));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route1, 2));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route2, 1));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route3, 2));
        MatcherAssert.assertThat(score, Matchers.hasEntry(route4, 4));
    }
}