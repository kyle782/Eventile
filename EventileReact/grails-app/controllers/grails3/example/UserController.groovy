package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.web.RequestParameter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestParam


class UserController extends RestfulController {

    static responseFormats = ['json']

    def userService

    def userPage() {
        def user = springSecurityService.currentUser
        def payload = [username: user.username, location: user.location, age: user.age] as Object
        respond payload, status: HttpStatus.ACCEPTED
    }

    UserController() {
        super(UserController)
    }

    def signUp(@RequestParameter('username') String username, @RequestParameter('password') String password, @RequestParameter('age') String age, @RequestParameter('location') String location) {
        log.debug("Signing up a new user: ${username}:[******]")
        def user = userService.signUp(username, password, age, location)
        def payload = [username: user.username] as Object
        respond payload, status: HttpStatus.CREATED
    }

    def handleUserExists(UserExistsException userExistsException) {
        def payload = [error: userExistsException.message] as Object
        respond payload, status: HttpStatus.BAD_REQUEST
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        def payload = [error: ex.message] as Object
        respond payload, status: HttpStatus.BAD_REQUEST
    }

}
