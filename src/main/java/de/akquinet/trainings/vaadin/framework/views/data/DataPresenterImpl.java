package de.akquinet.trainings.vaadin.framework.views.data;

import de.akquinet.trainings.vaadin.framework.backend.Product;
import de.akquinet.trainings.vaadin.framework.backend.ProductDao;
import de.akquinet.trainings.vaadin.framework.backend.ProductDaoImpl;

import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class DataPresenterImpl implements DataPresenter, ProductProvider
{
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> loadItems(final int startIndex, final int count)
    {
        if (startIndex < 0 || startIndex >= size())
        {
            throw new IndexOutOfBoundsException("Invalid start index: " + startIndex);
        }
        if (count < 0)
        {
            throw new IllegalArgumentException("Invalid count: " + count);
        }

        return productDao.getProducts(startIndex, count);
    }

    @Override
    public int size()
    {
        return productDao.getProductCount();
    }
}
