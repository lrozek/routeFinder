package pl.lrozek.router.finder;

import com.spotify.hamcrest.optional.OptionalMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.lrozek.router.dao.VesselRoutesDao;
import pl.lrozek.router.data.TestData;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Route;
import pl.lrozek.router.scoring.RoutesScorer;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VesselRouteFinderUnitTest
{

    @Mock
    private VesselRoutesDao mockVesselRoutesDao;
    @Spy
    private RoutesScorer routesScorer = new RoutesScorer();

    @InjectMocks
    private VesselRouteFinder vesselRouteFinder;


    @Test
    public void itShouldNotReturnAnyVesselRouteIfThereIsNoMatchToDestination()
    {
        //given
        BDDMockito.given(mockVesselRoutesDao.find(TestData.debrvToDeham)).willReturn(List.of());

        //when
        Optional<VesselRoute> optionalVesselRoute = vesselRouteFinder.findRoute(TestData.debrvToDeham);

        //then
        MatcherAssert.assertThat(optionalVesselRoute, OptionalMatchers.emptyOptional());
    }

    @Test
    public void itShouldReturnOnlyVesselRoute()
    {
        //given
        VesselRoute vesselRoute = TestData.vesselRoute1;
        Route route = vesselRoute.route();
        BDDMockito.given(mockVesselRoutesDao.find(route)).willReturn(List.of(vesselRoute));

        //when
        Optional<VesselRoute> optionalVesselRoute = vesselRouteFinder.findRoute(route);

        //then
        MatcherAssert.assertThat(optionalVesselRoute, OptionalMatchers.optionalWithValue(vesselRoute));
    }

    @Test
    public void itShouldReturnVesselRouteWithHighestScore()
    {
        //given
        VesselRoute vesselRoute = TestData.vesselRoute4;
        Route route = vesselRoute.route();
        BDDMockito.given(mockVesselRoutesDao.find(route)).willReturn(List.of(TestData.vesselRoute1, TestData.vesselRoute2, TestData.vesselRoute3, vesselRoute));

        //when
        Optional<VesselRoute> optionalVesselRoute = vesselRouteFinder.findRoute(route);

        //then
        MatcherAssert.assertThat(optionalVesselRoute, OptionalMatchers.optionalWithValue(vesselRoute));
    }
}
