package de.akquinet.trainings.vaadin.framework.views.data;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.trainings.vaadin.framework.backend.Product;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
public class DataViewImpl implements DataView, View, QueryFactory
{
    public final static String VIEW_NAME = "data";

    public final static String PROP_ID = "id";
    public final static String PROP_NAME = "name";
    public final static String PROP_MODEL_NUMBER = "modelNumber";
    public final static int BATCH_SIZE = 50;

    private final VerticalLayout rootLayout = new VerticalLayout();
    private final ProductProvider productProvider;

    public DataViewImpl(final ProductProvider productProvider)
    {
        final Table table = new Table("Product List");
        final LazyQueryContainer container = new LazyQueryContainer(this, PROP_ID, BATCH_SIZE, false);
        container.addContainerProperty(PROP_ID, Long.class, 0L, true, true);
        container.addContainerProperty(PROP_NAME, String.class, "", true, true);
        container.addContainerProperty(PROP_MODEL_NUMBER, String.class, "", true, true);
        table.setContainerDataSource(container);
        table.setColumnHeader(PROP_ID, "ID");
        table.setColumnHeader(PROP_NAME, "Product Name");
        table.setColumnHeader(PROP_MODEL_NUMBER, "Model Number");
        table.setSizeFull();
        rootLayout.addComponent(table);

        this.productProvider = productProvider;
    }


    @Override
    public <C> C getComponent(final Class<C> type)
    {
        return type.cast(rootLayout);
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event)
    {

    }

    @Override
    public Query constructQuery(final QueryDefinition queryDefinition)
    {
        return new QueryImpl();
    }

    private class QueryImpl implements Query
    {
        @Override
        public int size()
        {
            return productProvider.size();
        }

        @Override
        public List<Item> loadItems(int startIndex, int count)
        {
            final List<Product> productList = productProvider.loadItems(startIndex, count);
            final List<Item> resList = new ArrayList<>(productList.size());
            for (Product product : productList) {
                resList.add(obtainItemFromBean(product));
            }
            return resList;
        }

        private Item obtainItemFromBean(final Product product)
        {
            return new BeanItem<>(product, Product.class);
        }

        @Override
        public Item constructItem()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean deleteAllItems()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public void saveItems(List<Item> addedItems, List<Item> modifiedItems, List<Item> removedItems)
        {
            throw new UnsupportedOperationException();
        }
    }
}
