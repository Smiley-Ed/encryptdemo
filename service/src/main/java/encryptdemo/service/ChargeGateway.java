package encryptdemo.service;

import encryptdemo.model.Card;

/**
 * Rudimentary gateway interface
 * Created by esmiley on 7/13/15.
 */
public interface ChargeGateway {
    boolean chargeCard (Card card, int charge) throws Exception;
}
