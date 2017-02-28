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
        def result = searchService.search(q.trim())
        respond result
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        log.debug("Handling IllegalArgumentException ${ex}... Returning NO_CONTENT.")
        respond Collections.emptyList()
    }
}
