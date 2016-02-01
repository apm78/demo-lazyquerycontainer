package de.akquinet.trainings.vaadin.framework.views.data;

import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public interface DataProvider<T>
{
    /**
     * Gets number of items available.
     *
     * @return Number of items.
     */
    int size();

    /**
     * Load batch of items.
     *
     * @param startIndex Starting index of the item list.
     * @param count      Count of the items to be retrieved.
     * @return List of items.
     */
    List<T> loadItems(int startIndex, int count);
}
