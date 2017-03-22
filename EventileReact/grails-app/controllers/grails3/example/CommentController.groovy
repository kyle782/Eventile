package grails3.example

class CommentController {
<<<<<<< HEAD
   def save (){

   }

   def edit (){

   }

   def index() { }
=======
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
>>>>>>> 786ff47e010fdb16a5dc76808a8ea5009a86256a
}
