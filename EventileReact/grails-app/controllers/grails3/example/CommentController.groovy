package grails3.example

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus

class CommentController {

    static responseFormats = ['json']
    static allowedMethods = ['GET', 'POST', 'PUT']

    def springSecurityService

    @Secured(['ROLE_USER'])
    def update_comment(String q, String c){

        Event this_event = Event.findByEventbrite_id(q)
        User author = User.get(springSecurityService.principal.id)
        Comment new_comment = new Comment(comment_body: c, dateCreated: "testing date",
                event: this_event, author: author, author_name: author.username)
        new_comment.save()
        respond new_comment
    }

   def index() { }
}
