package com.rf.inventory.backend;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 * Data type corresponding to XML or JSON posted by server. 
 * The XML looks like this:
 *     <items>
 *         <item> 
 *             <productId>3212</productId> 
 *             <quantity>4</quantity> 
 *         </item>
 *         <item> 
 *             <productId>3002</productId> 
 *             <quantity>6</quantity> 
 *         </item>
 *     <items>
 * 
 * JSON looks like this:
 *     { { "productId" : 3212, "quantity" : 4 },
 *       { "productId" : 3002, "quantity" : 6 } }
 */
// TODO: Add annotation to indicate that we will send/receive XML or JSON documents 
//       consisting of just one ItemList named "items"
// HINT: See slide 6-19
@XmlRootElement(name="items")
public class ItemList {
    private List<Item> items = new ArrayList<Item>();

    // TODO: Add annotation to indicate that the element or member name
    //       will be "item"
    @XmlElement(name="item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
