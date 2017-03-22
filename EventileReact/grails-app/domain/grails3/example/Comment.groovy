<<<<<<< HEAD
package grails3.example

class Comment {

    static constraints = {
    }

    static belongsTo = [event: Event]

    String comment_body

=======

package grails3.example


class Comment {

    static belongsTo = [event:Event]
    String comment_Body
    static constraints = {

    }
>>>>>>> 786ff47e010fdb16a5dc76808a8ea5009a86256a
}
