package eventile

import com.evdb.javaapi.data.SearchResult
import grails.rest.*


class Search {

    String query
    SearchResult sr

    static constraints = {
    }
}
