/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jonat.myapplication.backend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.repackaged.com.google.common.base.Pair;
import com.google.apphosting.api.ApiBasePb;

import javax.inject.Named;
import javax.naming.Context;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.jonat.example.com",
                ownerName = "backend.myapplication.jonat.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */

    @ApiMethod(name = "sayJokes")
    public MyBean sayJokes() {
       MyBean response = new MyBean();
        Jokes joker = new Jokes();
        response.setMyJoke(joker.getJokes());
        return response;
    }

}

