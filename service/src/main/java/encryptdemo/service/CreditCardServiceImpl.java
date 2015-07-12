package encryptdemo.service;

import java.util.*;

import encryptdemo.model.*;
import encryptdemo.service.ifc.CreditCardService;
import encryptdemo.validation.PreValidator;

/**
 * Created by esmiley on 7/12/15.
 */
public class CreditCardServiceImpl implements CreditCardService {
    @Override
    public Status create(Card card) {
        return null;// todo call dao impl create
    }

    @Override
    public boolean preValidate(Card card) {
        return PreValidator.validate(card);
    }

    @Override
    public Status update(Card card) {
        return null; //todo call dao impl modify
    }

    @Override
    public Status delete(long seed) {
        return null; // todo call dao impl remove
    }

    @Override
    public Status charge(long seed, int charge) {
        return null;// todo call dao impl read, call authorization gateway
    }

}
