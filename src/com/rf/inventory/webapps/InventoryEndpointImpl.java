package com.rf.inventory.webapps;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rf.inventory.backend.InventoryDAO;
import com.rf.inventory.backend.InventoryDAOJDBCImpl;
import com.rf.inventory.backend.Item;
import com.rf.inventory.backend.ItemList;

/**
 * RESTful service implemented with JAX-RS 2.0
 * 
 * See https://jersey.java.net
 * See the Jersey User Guide http://jersey.java.net/documentation/latest/user-guide.html
 * See JSR 339 Javadocs http://jcp.org/aboutJava/communityprocess/final/jsr339/index.html
 * @author Mike Woinoski
 */
@Path("/item")
public class InventoryEndpointImpl {
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(InventoryEndpointImpl.class);

    private InventoryDAO dao = new InventoryDAOJDBCImpl();

    /**
     * Handles HTTP GET. Sends the complete inventory.
     * Request URL will be http://localhost:8080/inventory_json/rs/item/all
     */
    // TODO: Make modifications so that this method will produce content of 
    //       MIME types "application/xml" or "application/json"
    // HINT: See slide 7-16
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ItemList doGet() {
        ItemList itemList = getDao().getItems();
        return itemList;
    }

    /**
     * Handles HTTP Delete. The request URI (e.g. http://.../item/3012) identifies
     * the productId of the item to be deleted from inventory
     * Request URL will be http://localhost:8080/inventory_json/rs/item/3012
     */
    @DELETE
    @Path("/{productId}")
    public Response doDelete( @PathParam("productId") int id ) {
        if (id <= 0) {
            return Response.serverError().build();
        }
        getDao().removeItem(id);
        return Response.ok().build(); 
    }

    /**
     * Handles HTTP POST. Creates a new Item with a new unique id.
     * Incoming content should be something like <item productId="3212" quantity="4" />
     * Request URL will be http://localhost:8080/inventory_json/rs/item
     */
    // TODO: Make modifications so that this method will accept content of 
    //       MIME types "application/xml" or "application/json"
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    // omit @Path since it's the class' default
    public Response doPost(Item item) throws WebApplicationException {
        if (item.getProductId() <= 0 || item.getQuantity() < 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        getDao().addItem(item.getProductId(), item.getQuantity());
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Handles HTTP PUT. Updates the quantity of productId. 
     * Incoming content should be something like <item quantity="4" /> 
     * Request URI (e.g. http://.../item/3012) identifies productId
     * Request URL will be http://localhost:8080/inventory_json/rs/item/3012
     */
    // TODO: Make modifications so that this method will accept content of 
    //       MIME types "application/xml" or "application/json"
    @PUT
    @Path("/{productId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response doPut( @PathParam("productId") int id, Item item )
            throws WebApplicationException {
        // ignore the productId in the XML and use the URL parameter
        item.setProductId(id);
        if (item.getProductId() <= 0 || item.getQuantity() < 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        getDao().updateStockCount(item.getProductId(), item.getQuantity());
        return Response.accepted().build();
    }
    
    private InventoryDAO getDao() {
    	return dao;
    }
}
