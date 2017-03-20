
package grails3.example


class Comment {

    static belongsTo = [event:Event]
    String comment_Body
    static constraints = {

    }
}
