package ru.zenkov.skb_dz_7.exeptions;

import lombok.Getter;

@Getter
public class NotFoundByNameException extends Exception {

    private final String personName;

    public NotFoundByNameException(String personName) {
        this.personName = personName;
    }

}
