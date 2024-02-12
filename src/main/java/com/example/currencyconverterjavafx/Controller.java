package com.example.currencyconverterjavafx;

import java.io.IOException;

public interface Controller {
    void update_model(double amount, String from_currency, String to_currency);
    double get_result() throws IOException;
}
