package grails3.example

class Comment {

    static constraints = {
    }

    static belongsTo = [event: Event]

    String comment_body

}
