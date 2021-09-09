/**
 *  Exercise 7.1
 */
package com.rf.inventory.backend;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data type corresponding to XML or JSON posted by server. 
 * The XML looks like this:
 *     <item> 
 *         <productId>3212</productId> 
 *         <quantity>4</quantity> 
 *     </item>
 * 
 * JSON looks like this:
 *     { "productId" : 3212, "quantity" : 4 }
 */
// TODO: Add annotation to indicate that Item should be used as
//       the root element of the exchanged XML or JSON
// HINT: See slide 7-17
@XmlRootElement
public class Item {
    private int productId;
    private int quantity;

    public Item() {
        this(-1, -1);
    }

    public Item(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // TODO: Note that JSON will use element data, not attribute data.
    //       No JAXB annotation is required, or you can be explicit with @XmlElement
    //       (no changes required)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // JSON will use element data, not attribute data
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
