package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my Favourites
     * @return {@link List}
     */
    List<Neighbour> getFavourites();

    /**
     * Add a Favourite
     * @param neighbour
     */
    void addFavourite(Neighbour neighbour);

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Deletes a Favourite
     * @param neighbour
     */
    void deleteFavourite(Neighbour neighbour);
}
