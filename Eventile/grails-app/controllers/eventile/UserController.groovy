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
            redirect(controller:"user", action:"index")
        } else {
            flash.message = "Sorry, ${params.login}. Please try again."
            redirect(action:"login")
        }
    }

    def logout = {
        flash.message = "Goodbye ${session.user.name}"
        session.user = null
        redirect(controller:"entry", action:"list")
    }

    def index() { }
}
