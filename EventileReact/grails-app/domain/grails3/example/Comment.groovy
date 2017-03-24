package grails3.example

class Comment {

    static belongsTo = [event: Event]

    String comment_body
    String dateCreated


    static constraints = {
        event nullable: true
        comment_body nullable: true
        dateCreated nullable: true
    }

}
