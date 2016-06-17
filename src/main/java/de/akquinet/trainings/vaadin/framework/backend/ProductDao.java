package de.akquinet.trainings.vaadin.framework.backend;

import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public interface ProductDao
{
    List<Product> getProducts(int startIndex, int maxCount,
                              final String sortBy, final boolean ascending);

    int getProductCount();
}
