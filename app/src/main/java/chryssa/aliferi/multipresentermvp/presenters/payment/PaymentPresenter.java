package chryssa.aliferi.multipresentermvp.presenters.payment;

import chryssa.aliferi.multipresentermvp.screens.PaymentScreen;

public class PaymentPresenter {

    PaymentScreen screen;

    public PaymentPresenter(PaymentScreen screen) {
        this.screen = screen;
    }

    public void setTitle() {

    }

    public void goToMap() {
        screen.finishTheActivity();
    }
}