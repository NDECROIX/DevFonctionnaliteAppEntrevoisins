package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Favourite
 */
public class DeleteFavouriteEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour favourite;

    /**
     * Constructor.
     * @param favourite Favourite to be deleted
     */
    public DeleteFavouriteEvent(Neighbour favourite) {
        this.favourite = favourite;
    }
}
