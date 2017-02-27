package eventile

import com.evdb.javaapi.APIConfiguration
import com.evdb.javaapi.EVDBAPIException
import com.evdb.javaapi.EVDBRuntimeException
import com.evdb.javaapi.data.SearchResult
import com.evdb.javaapi.data.request.EventSearchRequest
import com.evdb.javaapi.operations.EventOperations

class BootStrap {

    def init = { servletContext ->
        new Search(query:"drake").save()
        APIConfiguration.setApiKey("3GZZwncKn3BHQ4rw");
        APIConfiguration.setEvdbUser("camboppolo2@gmail.com");
        APIConfiguration.setEvdbPassword("testing123");

        EventOperations eo = new EventOperations();
        EventSearchRequest esr = new EventSearchRequest();

        esr.setLocation("London");
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
            System.out.println("YEYEYE " + sr.getEvents())
            if (sr.getTotalItems() > 1) {

                System.out.println("Total items: " + sr.getTotalItems());
            }
        }catch(EVDBRuntimeException var){
            System.out.println("Opps got runtime an error...");
        } catch( EVDBAPIException var){
            System.out.println("Opps got runtime an error...");
        }

    }
    def destroy = {
    }
}
