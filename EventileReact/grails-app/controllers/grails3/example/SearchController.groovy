package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus



@Secured(['ROLE_USER'])
class SearchController {

    static responseFormats = ['json']

    def searchService
    def springSecurityService

    def search(String q) {
        // Gets the current user name - can be used to control permissions
        def info = springSecurityService.currentUser.username
        log.debug("Searching by query = ${q}...")

        /** // perform a GET requestion, expecting text response
        def http = new HTTPBuilder("https://www.eventbriteapi.com/v3/events/search")
        http.request(Method.GET, ContentType.JSON) { req ->
            headers."Authorization" = "2S34UCIHKW5MXVP4S5M7"

            response.success = { resp, reader ->
                println "response status: ${resp.statusLine}"
            }

            response.'404' = { resp ->
                println 'Not found'
            }
        } **/

        def result = searchService.search(q.trim())
        respond result
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }
}
