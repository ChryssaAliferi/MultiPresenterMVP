package chryssa.aliferi.multipresentermvp.presenters.payment;

import chryssa.aliferi.multipresentermvp.screens.PaymentScreen;

public class EditPaymentFlow extends PaymentPresenter {

    public EditPaymentFlow(PaymentScreen screen) {
        super(screen);
    }

    public void setTitle() {
        screen.setTitle("Edit Payment Means");
    }
}
