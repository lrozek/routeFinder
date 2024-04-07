package pl.lrozek.router.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import pl.lrozek.router.csv.CsvParser;
import pl.lrozek.router.domain.VesselRoute;

import java.io.IOException;
import java.util.List;

@Configuration
public class VesselRoutesDaoConfiguration

{

    @Value("classpath:DEBRV_DEHAM_historical_routes.csv")
    private Resource csv;

    @Autowired
    private CsvParser csvParser;

    @Bean
    public VesselRoutesDao vesselRoutesDao()
    {
        try
        {
            List<VesselRoute> vesselRoutes = csvParser.parse(csv.getInputStream());
            return new VesselRoutesDao(vesselRoutes);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
