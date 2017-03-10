package grails3.example

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class EventController {

    static responseFormats = ['json']

    def index() { }

    def EventService

    def show(String q) {

        // perform a GET requestion to Eventbrite's API using EventbriteService class
        def response_eventbrite = EventService.search(q)

        respond response_eventbrite

    }
}
