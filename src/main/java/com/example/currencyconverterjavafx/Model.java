package com.example.currencyconverterjavafx;

import java.io.IOException;

public interface Model {
    void set_amount(double amount);

    void set_from_currency(String from_currency);

    void set_to_currency(String to_currency);
    double get_amount();
    String get_from_currency();
    String get_to_currency();
    Double get_result() throws IOException;
}
