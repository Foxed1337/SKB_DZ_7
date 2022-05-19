package ru.zenkov.skb_dz_7.exeptions;

import lombok.Getter;

@Getter
public class NotFoundByIdException extends Exception {
    private final Long personId;

    public NotFoundByIdException(Long personId) {
        this.personId = personId;
    }
}
