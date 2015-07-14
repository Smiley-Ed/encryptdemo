package encryptdemo.service;

import encryptdemo.model.Card;

/**
 * TODO Stub.  Should use IoC to implement specific gateway, test gateway.
 * Created by esmiley on 7/13/15.
 */
public class ChargeGatewayImpl implements ChargeGateway {
    /**
     *
     * @param card the credit card information
     * @param charge the charge in cents TODO add currency localization and Locale
     * @return true if charge succeeded
     * @throws Exception on gatey failure
     */
    public boolean chargeCard (Card card, int charge) throws Exception{
        return true;
    }
}
