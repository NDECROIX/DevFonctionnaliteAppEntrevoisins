package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavouriteEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour favourite;

    /**
     * Constructor.
     * @param favourite
     */
    public DeleteFavouriteEvent(Neighbour favourite) {
        this.favourite = favourite;
    }
}
