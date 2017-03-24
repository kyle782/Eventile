package grails3.example

class Comment {

    static belongsTo = [event: Event, author: User]

    String comment_body
    String dateCreated
    String author_name

    static constraints = {
        event nullable: true
        comment_body nullable: true
        dateCreated nullable: true
        author nullable: true
        author_name nullable: true
    }

}
