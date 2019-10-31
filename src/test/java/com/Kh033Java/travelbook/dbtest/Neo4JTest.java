package com.Kh033Java.travelbook.dbtest;

import com.Kh033Java.travelbook.entity.City;
import com.Kh033Java.travelbook.entity.Country;
import com.Kh033Java.travelbook.entity.Description;
import com.Kh033Java.travelbook.repository.CityRepository;
import com.Kh033Java.travelbook.repository.CountryRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ContextConfiguration(classes = {PersistenceContext.class})
@RunWith(SpringRunner.class)
@Ignore
public class Neo4JTest {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CityRepository cityRepository;

    @Before
    public void setup() {
        Country italy = new Country("Italy");
        Description italyDescription = new Description("Common", "Rome", "Very nice", "Warm");
        italy.describe(italyDescription);

        Country ukraine = new Country("Ukraine");

        City kyiv = new City("Kyiv");
        City kharkiv = new City("Kharkiv");
        City rome = new City("Rome");

        italy.hasCity(rome);
        ukraine.hasCity(kyiv);
        ukraine.hasCity(kharkiv);

        countryRepository.save(italy);
        countryRepository.save(ukraine);
    }

    @Test
    public void checkIfAllAreSaved() {
        List<Country> countries = countryRepository.findAll();
        Assert.assertEquals("Rome", countries.get(0).getDescription().getCapital());
        Assert.assertEquals(2, countries.size());

        List<City> cities = cityRepository.findAll();
        Assert.assertEquals(3, cities.size());
    }

    @After
    public void cleanUp() {
        countryRepository.deleteAll();
    }

}
