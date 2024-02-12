package com.example.currencyconverterjavafx;

import java.io.IOException;

public class ControllerImpl implements Controller {
    private final Model model;

    public ControllerImpl(Model model) {
        this.model = model;
    }

    @Override
    public void update_model(double amount, String from_currency, String to_currency) {
        model.set_amount(amount);
        model.set_from_currency(from_currency);
        model.set_to_currency(to_currency);
    }

    @Override
    public double get_result() throws IOException {
        return model.get_result();
    }
}
