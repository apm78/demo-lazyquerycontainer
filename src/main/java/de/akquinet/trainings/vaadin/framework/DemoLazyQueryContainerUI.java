package de.akquinet.trainings.vaadin.framework;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import de.akquinet.trainings.vaadin.framework.views.data.DataViewImpl;
import de.akquinet.trainings.vaadin.framework.views.home.HomeViewImpl;

import javax.servlet.annotation.WebServlet;

/**
 * @author Axel Meier, akquinet engineering GmbH
 */
@Theme("demotheme")
public class DemoLazyQueryContainerUI extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        final Panel navigationPanel = new Panel();
        navigationPanel.setHeight("100%");
        navigationPanel.setWidth("300px");
        final Panel contentPanel = new Panel();
        contentPanel.setSizeFull();
        layout.addComponents(navigationPanel, contentPanel);
        layout.setExpandRatio(contentPanel, 1f);

        layout.setSpacing(true);
        setContent(layout);

        setNavigator(new Navigator(this, new CustomViewDisplay(contentPanel)));
        getNavigator().addView(HomeViewImpl.VIEW_NAME, new HomeViewImpl());
        getNavigator().addView(DataViewImpl.VIEW_NAME, new DataViewImpl());

        final VerticalLayout navigationLayout = new VerticalLayout();
        final Button homeButton = new Button("Home", e -> getNavigator().navigateTo(HomeViewImpl.VIEW_NAME));
        homeButton.addStyleName(BaseTheme.BUTTON_LINK);
        final Button dataButton = new Button("Data", e -> getNavigator().navigateTo(DataViewImpl.VIEW_NAME));
        dataButton.addStyleName(BaseTheme.BUTTON_LINK);
        navigationLayout.addComponents(homeButton, dataButton);
        navigationPanel.setContent(navigationLayout);
        getNavigator().navigateTo(HomeViewImpl.VIEW_NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "DemoLazyQueryContainerUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoLazyQueryContainerUI.class, productionMode = false)
    public static class DemoLazyQueryContainerUIServlet extends VaadinServlet
    {
    }
}
