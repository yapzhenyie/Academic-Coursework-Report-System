package oodj.group5.guis.subpages;

public abstract class ISubPage {

    /**
     * For user page, to show all left panel components.
     */
    protected abstract void initComponents();

    /**
     * To show all components in the working panel.
     */
    public abstract void showAllComponents();

    /**
     * To hide all components in the working panel.
     */
    public abstract void hideAllComponents();

    /**
     * To hide some components that need some inputs before showing.
     */
    public abstract void hideSomeComponents();

    /**
     * To update/refresh page information.
     */
    public abstract void updateInfo();

    /**
     * To clear page information.
     */
    public abstract void clearInfo();
}
