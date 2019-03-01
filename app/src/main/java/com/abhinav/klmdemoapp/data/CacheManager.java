package com.abhinav.klmdemoapp.data;

import com.abhinav.klmdemoapp.data.response.Airport;
import com.abhinav.klmdemoapp.data.response.AllMasterDataResponse;
import com.abhinav.klmdemoapp.data.response.Country;
import com.abhinav.klmdemoapp.data.response.Destination;
import com.abhinav.klmdemoapp.data.response.Region;

import java.util.ArrayList;
import java.util.List;

public class CacheManager {

    private static CacheManager instance;
    private List<Airport> airports;
    private List<Country> countries;
    private List<Destination> destinations;
    private List<Region> regions;
    private List<String> allAirportNames;

    private CacheManager() {

    }

    public static synchronized CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }

        return instance;
    }

    public void saveMasterData(AllMasterDataResponse allMasterDataResponse) {
        airports = allMasterDataResponse.getAirports();
        countries = allMasterDataResponse.getCountries();
        destinations = allMasterDataResponse.getDestinations();
        regions = allMasterDataResponse.getRegions();

        allAirportNames = new ArrayList<>();

        for (Airport airport : airports) {
            allAirportNames.add(airport.getCode());
        }
    }

    public List<String> getAllAirportNames() {
        return allAirportNames;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public List<Region> getRegions() {
        return regions;
    }
}
