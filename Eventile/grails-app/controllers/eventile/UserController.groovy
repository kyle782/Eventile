package eventile

class UserController {

    def scaffold = User

    def login = {
        // renders login.gsp file
    }

    def authenticate = {
        def user = User.findByEmailAndPassword(params.email, params.password)
        if (user) {
            session.user = user
            flash.message = "Hello ${user.name}!"
            redirect(controller:"home", action:"index")
        } else {
            flash.message = "Sorry, ${params.login}. Please try again."
            redirect(action:"login")
        }
    }

    def logout = {
        session.user = null
        redirect(view:"/index")
    }

    def index() { }
}
