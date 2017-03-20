package grails3.example

class CommentController {
    def edit(){
        def comment = Comment.get(params.id)
        comment = new Comment()
        System.out.println()
    }

    def save(){
        def comment = loadPost(params.id)
        comment.properties = params
    }

    def index(){
    }
}
