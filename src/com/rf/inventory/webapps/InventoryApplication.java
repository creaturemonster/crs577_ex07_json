package com.rf.inventory.webapps;

import javax.ws.rs.core.Application;
//import javax.ws.rs.ApplicationPath;

//TODO: Examine the JAX-RS Application subclass. You'll need to add the
//      fully-qualified name of this class to web.xml.
//      (no code changes required in this file).

// URL pattern can be defined here using @ApplicationPath or in web.xml.
// If URL is mapped in both places, the mapping in web.xml takes precedence
// @ApplicationPath("/rest") 
public class InventoryApplication extends Application {

    // JAX-RS will scan class files WEB-INF/classes and jars in WEB-INF/lib
    // for classes with @Path
    
    // To override default scanning, define getClasses() method and return 
    // a Set of classes that JAX-RS should scan for annotations:
    // @Override
    // public Set<Class<?>> getClasses() {
    //     Set<Class<?>> classes = new HashSet<Class<?>>() {{
    //         add(InventoryEndpointImpl.class);
    //     }};
    //     return classes;
    // }
    
}
