package grails3.example



class WelcomeController {

    static responseFormats = ['json']

    def WelcomeService

    def index() { }

    def search(String location){

        log.debug("Searching by location = ${location}...")

        // perform a GET requestion to Eventbrite's API using WelcomeService class
        def response_eventbrite = WelcomeService.search(location)

        respond response_eventbrite
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }

}
