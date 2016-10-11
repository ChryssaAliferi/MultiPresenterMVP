package chryssa.aliferi.multipresentermvp.presenters.payment;

import chryssa.aliferi.multipresentermvp.screens.PaymentScreen;

public class SelectPaymentFlow extends PaymentPresenter {

    public SelectPaymentFlow(PaymentScreen screen) {

        super(screen);
    }

    @Override
    public void setTitle() {

        screen.setTitle("Select Payment Means");
    }
}
