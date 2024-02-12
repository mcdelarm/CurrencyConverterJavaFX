package com.example.currencyconverterjavafx;

import java.io.IOException;
import java.util.HashMap;

public class ModelImpl implements Model {
    private double amount;
    private double result;
    private String from_currency;
    private String to_currency;
    private APIConsumer apiConsumer;

    public ModelImpl() {
        this.apiConsumer = new APIConsumer();
        this.amount = 0;
        this.result = 0;
        this.from_currency = null;
        this.to_currency = null;
    }

    @Override
    public void set_amount(double amount) {
        this.amount = amount;
    }

    @Override
    public void set_from_currency(String from_currency) {
        this.from_currency = from_currency;
    }

    @Override
    public void set_to_currency(String to_currency) {
        this.to_currency = to_currency;
    }

    @Override
    public double get_amount() {
        return this.amount;
    }

    @Override
    public String get_from_currency() {
        return this.from_currency;
    }

    @Override
    public String get_to_currency() {
        return this.to_currency;
    }

    @Override
    public Double get_result() throws IOException {
        this.result = apiConsumer.sendHttpGETRequest(from_currency, to_currency, amount);
        return this.result;
    }
}
