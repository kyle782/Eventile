package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus

class CommentController {

    static responseFormats = ['json']
    static allowedMethods = ['GET', 'POST', 'PUT']

    def springSecurityService

    @Secured(['ROLE_USER'])
    def update_comment(String q, String c){
        System.out.println("q = " + q + ", c = " + c)
        Event this_event = Event.findByEventbrite_id(q)
        Comment new_comment = new Comment(comment_body: c, dateCreated: "testing date", event: this_event)
        new_comment.save()
        System.out.println("saved comment to event? " + this_event.comments)
        respond new_comment
    }

   def index() { }
}
