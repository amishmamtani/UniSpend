package view;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import interface_adapter.ViewManagerModel;

/**
 * ViewManager class is responsible for managing and switching between different views
 * in the application using a CardLayout. It listens for changes in the view model's state
 * and updates the displayed view accordingly.
 */
public class ViewManager implements PropertyChangeListener {

    /** The CardLayout used to manage the different views */
    private final CardLayout cardLayout;

    /** The JPanel that holds the different views */
    private final JPanel views;

    /** The ViewManagerModel responsible for maintaining the current view's state */
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a ViewManager with the specified views panel, card layout, and view model.
     * Initializes the ViewManager to listen for changes in the view model's state.
     *
     * @param views The JPanel containing all the views.
     * @param cardLayout The CardLayout used to switch between views.
     * @param viewManagerModel The ViewManagerModel to manage the current view's state.
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Responds to changes in the view manager model's state by switching to the appropriate view.
     *
     * @param evt The PropertyChangeEvent containing the new view name.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("in the view manager: " + evt.getPropertyName());
        // Check if the event is related to the view state change
        if (evt.getPropertyName().equals("state")) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName); // Show the new view based on the name
        }
    }
}
