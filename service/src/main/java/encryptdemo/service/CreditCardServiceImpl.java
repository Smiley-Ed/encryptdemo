package encryptdemo.service;

import java.util.*;

import encryptdemo.model.*;
import encryptdemo.persist.*;
import encryptdemo.persist.factory.PersistenceFactory;
import encryptdemo.service.factory.ServiceFactory;
import encryptdemo.service.ifc.CreditCardService;
import encryptdemo.validation.PreValidator;

/**
 * Provide implementation of RESTful credit card services
 * Created by esmiley on 7/12/15.
 */
public class CreditCardServiceImpl implements CreditCardService {
    private static CardDao dao = PersistenceFactory.getCardDao();
    private static ChargeGateway gateway = ServiceFactory.getChargeGateway();

    @Override
    public Status create(Card card) {
        long start = (new Date().getTime());
        String message = "Created";
        boolean successful = true;

        try{
            dao.create(card);
        } catch (PersistException pe){
            message = pe.getMessage();
            successful = false;
        }

        long end = (new Date().getTime());
        return new Status(end - start, successful, message);
    }

    @Override
    public boolean preValidate(Card card) {
        return PreValidator.validate(card);
    }

    @Override
    public Status update(Card card) {
        long start = (new Date().getTime());
        String message = "Updated";
        boolean successful = true;

        try{
            dao.modify(card);
        } catch (PersistException pe){
            message = pe.getMessage();
            successful = false;
        }

        long end = (new Date().getTime());
        return new Status(end - start, successful, message);
    }

    @Override
    public Status delete(long seed) {
        long start = (new Date().getTime());
        String message = "Created";
        boolean successful = true;

        try{
            dao.remove(seed);
        } catch (PersistException pe){
            message = pe.getMessage();
            successful = false;
        }

        long end = (new Date().getTime());
        return new Status(end - start, successful, message);
    }

    @Override
    public Status charge(long seed, int charge) {
        long start = (new Date().getTime());
        String message = "Created";
        boolean successful = true;

        try{
            Card card = dao.read(seed);
            successful = gateway.chargeCard(card, charge);
        } catch (Exception pe){
            message = pe.getMessage();
            successful = false;
        }

        long end = (new Date().getTime());
        return new Status(end - start, successful, message);
    }

}
