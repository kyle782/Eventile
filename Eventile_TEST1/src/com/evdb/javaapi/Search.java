package com.evdb.javaapi;

import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.request.EventSearchRequest;
import com.evdb.javaapi.operations.EventOperations;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;

/**
 * Created by gary on 2017-02-27.
 */
public class Search {

    public static void main(String[] args){
        APIConfiguration.setApiKey("3GZZwncKn3BHQ4rw");
        APIConfiguration.setEvdbUser("camboppolo2@gmail.com");
        APIConfiguration.setEvdbPassword("testing123");

        EventOperations eo = new EventOperations();
        EventSearchRequest esr = new EventSearchRequest();

        esr.setLocation("San Diego");
        esr.setDateRange("2016050200-2017052100");
        esr.setPageSize(20);
        esr.setPageNumber(1);
        // These 2 lines will set the timeout to 60 seconds.Normally not needed
        // Unless you are using Google App Engine
        esr.setConnectionTimeout(60000);  // Used with Google App Engine only
        esr.setReadTimeout(60000);        // Used with Google App Engine only
        SearchResult sr = null;
        try {
            sr = eo.search(esr);
            System.out.println("yeyeye");
            if (sr.getTotalItems() > 1) {

                System.out.println("Total items: " + sr.getTotalItems());
            }
        }catch(EVDBRuntimeException var){
            System.out.println("Opps got runtime an error...RUNTIME");
            var.printStackTrace();
        } catch( EVDBAPIException var){
            System.out.println("Opps got runtime an error...API");
            var.printStackTrace();
        }


    }

}
