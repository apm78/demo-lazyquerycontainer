package de.akquinet.trainings.vaadin.framework.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a Dao that fakes the access to a real database.
 *
 * @author Axel Meier, akquinet engineering GmbH
 */
public class ProductDaoImpl implements ProductDao
{
    private static final int PRODUCTS_COUNT = 123456;
    private static final String[] PRODUCT_NAMES = {
        "hammer", "plane", "saw", "work bench", "wrench",
            "scissors", "file", "toolbox", "drill", "pliers"
    };

    @Override
    public List<Product> getProducts(final int startIndex, final int maxCount,
                                     final String sortBy, final boolean ascending)
    {
        if (startIndex < 0)
        {
            throw new IndexOutOfBoundsException("Invalid start index: " + startIndex);
        }
        if (maxCount < 0)
        {
            throw new IllegalArgumentException("Invalid count: " + maxCount);
        }
        if (startIndex >= size())
        {
            return Collections.emptyList();
        }
        final int endIndex = startIndex + maxCount > size() ? size() : startIndex + maxCount;
        final List<Product> result = new ArrayList<>(endIndex - startIndex);
        for (int i = startIndex; i < endIndex; ++i)
        {
            int id = "id".equals(sortBy) && !ascending ? PRODUCTS_COUNT - (i+1) : i;
            final String productName = generateProductName(id);
            result.add(new Product(id, productName, generateProductModelNumber(id, productName)));
        }

        return result;
    }

    private String generateProductName(int id)
    {
        return PRODUCT_NAMES[id % PRODUCT_NAMES.length];
    }

    private String generateProductModelNumber(final int id, final String productName)
    {
        return "MN" + Integer.toString(Math.abs((productName + Integer.toString(id)).hashCode()));
    }

    private int size()
    {
        return PRODUCTS_COUNT;
    }

    @Override
    public int getProductCount()
    {
        return size();
    }
}
