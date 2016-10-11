package chryssa.aliferi.multipresentermvp.screens;

public interface MenuScreen extends BaseScreen {

    void initializeMenu();

    void initializeNavigationView();

    void navigateToSelectPaymentMeans();

    void navigateToEditPaymentMeans();

    void closeMenu();

    void openMenu();

    void initializeToolbar();
}
