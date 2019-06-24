package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<Neighbour> favorites = DummyNeighbourGenerator.generateFavorites();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavourites() {
        return favorites;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFavourite(Neighbour neighbour) {
        favorites.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFavourite(Neighbour neighbour) {
        return favorites.contains(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFavourite(Neighbour neighbour) {
        favorites.remove(neighbour);
    }
}
