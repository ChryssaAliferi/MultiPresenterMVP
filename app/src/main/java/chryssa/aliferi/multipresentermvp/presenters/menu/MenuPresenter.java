package chryssa.aliferi.multipresentermvp.presenters.menu;

import chryssa.aliferi.multipresentermvp.presenters.BaseFlow;
import chryssa.aliferi.multipresentermvp.screens.MenuScreen;

/**
 * Created by chryssa on 9/14/16.
 */

public class MenuPresenter extends BaseFlow {
    MenuScreen screen;

    public MenuPresenter(MenuScreen screen) {
        this.screen = screen;
    }

    public void initialize() {
        screen.initializeToolbar();
        screen.initializeMenu();
        screen.initializeNavigationView();
    }

    public void editPaymentMeansClicked() {
        screen.navigateToEditPaymentMeans();
        screen.closeMenu();
    }

    public void selectPaymentMeansClicked() {
        screen.navigateToSelectPaymentMeans();
        screen.closeMenu();
    }

    public void finishActivity() {
        screen.finishTheActivity();
    }

    public void closeMenu() {
        screen.closeMenu();
    }

    public void openMenu() {
        screen.openMenu();
    }
}
