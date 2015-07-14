package encryptdemo.service.factory;

import encryptdemo.service.*;

/**
 * This is a wrapper for IoC strategy to insulate service from implementation details
 * Created by esmiley on 7/13/15.
 */
public class ServiceFactory {
    private ServiceFactory(){}
    private static ChargeGateway cgInstance = new ChargeGatewayImpl(); // todo IOC
    public static ChargeGateway getChargeGateway(){
        return cgInstance;
    }
}
