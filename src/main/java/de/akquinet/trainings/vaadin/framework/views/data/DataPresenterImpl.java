package de.akquinet.trainings.vaadin.framework.views.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class DataPresenterImpl implements DataPresenter, ProductProvider
{
    final private Random random = new Random(new Date().getTime());

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
        final int endIndex = startIndex + count > size() ? size() : startIndex + count;
        final List<Product> result = new ArrayList<>(endIndex - startIndex);
        for (int i = startIndex; i < endIndex; ++i)
        {
            result.add(new Product(i, "Product", Integer.toString(random.nextInt())));
        }

        return result;
    }

    @Override
    public int size()
    {
        return 123456;
    }
}
