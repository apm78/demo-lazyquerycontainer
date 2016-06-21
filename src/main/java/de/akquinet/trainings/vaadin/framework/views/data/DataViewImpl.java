package de.akquinet.trainings.vaadin.framework.views.data;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import de.akquinet.trainings.vaadin.framework.backend.Product;
import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
@CDIView(DataViewImpl.VIEW_NAME)
public class DataViewImpl implements DataView, View, QueryFactory
{
    public final static String VIEW_NAME = "data";

    private final static String PROP_ID = "id";
    private final static String PROP_NAME = "name";
    private final static String PROP_MODEL_NUMBER = "modelNumber";
    private final static int BATCH_SIZE = 50;

    private final VerticalLayout rootLayout = new VerticalLayout();

    @Inject
    private ProductProvider productProvider;

    public DataViewImpl()
    {
        final Grid grid = new Grid("Product List");
        final LazyQueryContainer container = new LazyQueryContainer(this, PROP_ID, BATCH_SIZE, false);
        container.addContainerProperty(PROP_ID, Long.class, 0L, true, true);
        container.addContainerProperty(PROP_NAME, String.class, "", true, false);
        container.addContainerProperty(PROP_MODEL_NUMBER, String.class, "", true, false);
        grid.setContainerDataSource(container);
        grid.getDefaultHeaderRow().getCell(PROP_ID).setHtml("<b>ID</b>");
        grid.getColumn(PROP_NAME).setHeaderCaption("ProductName");
        grid.getColumn(PROP_MODEL_NUMBER).setHeaderCaption("Model Number");
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(10d);
        grid.setWidth("100%");
        rootLayout.setMargin(true);
        rootLayout.addComponent(grid);
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
        return new QueryImpl(queryDefinition);
    }

    private class QueryImpl implements Query
    {
        final QueryDefinition queryDefinition;

        public QueryImpl(final QueryDefinition queryDefinition)
        {
            this.queryDefinition = queryDefinition;
        }

        @Override
        public int size()
        {
            return productProvider.size();
        }

        @Override
        public List<Item> loadItems(int startIndex, int count)
        {
            final String sortBy = queryDefinition.getSortPropertyIds().length > 0
                    ? (String)queryDefinition.getSortPropertyIds()[0]
                    : "";
            final boolean ascending = queryDefinition.getSortPropertyAscendingStates().length > 0
                    && queryDefinition.getSortPropertyAscendingStates()[0];

            final List<Product> productList = productProvider.loadItems(startIndex, count,
                    sortBy,
                    ascending);
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
