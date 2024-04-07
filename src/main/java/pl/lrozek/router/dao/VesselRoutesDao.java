package pl.lrozek.router.dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import pl.lrozek.router.domain.VesselRoute;
import pl.lrozek.router.domain.destination.Route;

import java.util.Collection;
import java.util.List;

public class VesselRoutesDao
{
    private Multimap<Route, VesselRoute> data = ArrayListMultimap.create();

    public VesselRoutesDao(List<VesselRoute> data)
    {
        data.forEach(vesselRoute -> this.data.put(vesselRoute.route(), vesselRoute));
    }

    public List<VesselRoute> find(Route route)
    {
        Collection<VesselRoute> vesselRoutes = data.get(route);
        return vesselRoutes == null ? List.of() : List.copyOf(vesselRoutes);
    }
}
