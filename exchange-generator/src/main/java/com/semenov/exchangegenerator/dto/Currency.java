package com.semenov.exchangegenerator.dto;


public enum Currency {
    RUB("Russian ruble", "Ruble"),
    USD("Unated State Dollar", "Usd"),
    CNY("China currency", "CNY"),
    ;

    private final String title;
    private final String name;


    Currency(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}
