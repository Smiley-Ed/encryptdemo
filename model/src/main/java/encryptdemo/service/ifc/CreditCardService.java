package encryptdemo.service.ifc;

import javax.ws.rs.*;

import encryptdemo.model.*;

/**
 * This should be serviced by a server over https, behind a firewall.
 * It should be called by authenticated clients.
 *
 * Design philosophy is to post the data with an associated seed, but never read the
 * data out again to the client which is encrypted and should not be decrypted.
 *
 * Goes into the "black hole".  Seed is special set of "tongs" for manipulating the credit card
 * record without accessing the data directly, but ony through the service.
 *
 * Charging for customer, recurring billing and so forth
 * can use the seed alone.
 *
 * Created by esmiley on 7/12/15.
 */
public interface CreditCardService {
    @Produces( { "application/xml", "application/json" })
    @GET
    @Path("create")
    /**
     * @param card contains seed and data for persisting
     * @return a status object, which does NOT contain the credit card ino
     */
    public Status create(Card card);

    @Produces( { "application/xml", "application/json" })
    @GET
    @Path("preValidate")
    /**
     * @param card contains data for persisting check that it is not garbage
     * @return a true if "reasonable"
     */
    public boolean preValidate(Card card);

    @Produces( { "application/xml", "application/json" })
    @POST
    @Path("update")
    /**
     * @param card contains seed and data for updating
     * @return a status object
     */
    public Status update(Card card);

    @Produces( { "application/xml", "application/json" })
    @DELETE
    @Path("delete")
    /**
     * @param remove the card
     * @return a status object
     */
    public Status delete(long seed);

    @Produces( { "application/xml", "application/json" })
    @POST
    @Path("charge")
    /**
     * @param seed for charging
     * @param int for charge in cents
     * @return a status object
     */
    public Status charge(long seed, int charge); // can modify for locale driven currency in the future, but this is a demo




}
