package com.lamzone.mareu.di;

import com.lamzone.mareu.model.Collaborateur;
import com.lamzone.mareu.model.Reunion;
import com.lamzone.mareu.model.Salle;
import com.lamzone.mareu.service.DummyReunionApiService;
import com.lamzone.mareu.service.ReunionApiService;

import java.util.List;

/**
 * Dependency injector to get instance of services
 */
public class DI {
    private static ReunionApiService service = new DummyReunionApiService();

    /**
     * Get an instance on @{@link ReunionApiService}
     * @return
     */
    public static ReunionApiService getReunionApiService() { return service; }

    /**
     * Get always a new instance on @{@link ReunionApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static ReunionApiService getNewInstanceApiService() { return new DummyReunionApiService(); }
}
