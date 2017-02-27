package eventile

import grails.rest.*

@Resource(uri='/api/events')
class Search {

    String query;

    static constraints = {
    }
}
