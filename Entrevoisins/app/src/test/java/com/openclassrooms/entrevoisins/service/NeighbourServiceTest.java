package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavouritesWithSuccess(){
        List<Neighbour> favourites = service.getFavourites();
        List<Neighbour> expectedFavourites = DummyNeighbourGenerator.DUMMY_FAVORITES;
        assertThat(favourites, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavourites.toArray()));
    }

    @Test
    public void deleteFavouriteWithSuccess(){
        Neighbour favouriteToDelete = service.getFavourites().get(0);
        service.deleteFavourite(favouriteToDelete);
        assertFalse(service.getFavourites().contains(favouriteToDelete));
    }

    @Test
    public void isFavouriteWithSuccess(){
        List<Neighbour> neighbours = service.getNeighbours();
        assertTrue(service.isFavourite(neighbours.get(0)));
        assertFalse(service.isFavourite(neighbours.get(5)));
    }

    @Test
    public void addFavouriteWithSuccess(){
        Neighbour favouriteToAdd = service.getFavourites().get(0);
        service.addFavourite(favouriteToAdd);
        assertTrue(service.getFavourites().contains(favouriteToAdd));
    }
}
