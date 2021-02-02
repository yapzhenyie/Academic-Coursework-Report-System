package oodj.group5.guis;

import java.awt.Component;

public interface IPage {

    /**
     * Set the visibility of that page.
     *
     * @param flag True means visible otherwise is hidden.
     */
    public abstract void setPageVisible(boolean flag);
    
    /**
     * Update the user welcome message after login.
     */
    public default void updateWelcomeMessage() {
        
    }

    /**
     * Add component to the working panel. (right hand side panel)
     *
     */
    public default void addWorkingPanelComponent(Component component) {
        
    }
    
    /**
     * Set the current selected page label.
     * @param index Starts from 0
     */
    public default void setCurrentSelectedLabel(int index) {
        
    }
}
