package de.akquinet.trainings.vaadin.framework.views.home;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
@CDIView(HomeViewImpl.VIEW_NAME)
public class HomeViewImpl implements HomeView, View
{
    public final static String VIEW_NAME = "";

    private final VerticalLayout rootLayout = new VerticalLayout();

    public HomeViewImpl()
    {
        rootLayout.setMargin(true);
        rootLayout.addComponent(
                new Label(
                        "In this demo a LazyQueryContainer is used with a Grid component."));
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
}
