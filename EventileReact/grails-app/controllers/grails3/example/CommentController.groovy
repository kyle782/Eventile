package grails3.example

class CommentController {

    def save = {
        def comment = new Comment(params)
        comment.event = Event.get(params.postId)
        if(comment.save()) {
            redirect(controller:'post', action:'view', id:params.postId)
        } else {
            render(view:'edit', model:[comment:comment, postId:params.postId])
        }
    }

   def index() { }
}
