package grails3.example

class UrlMappings {

    static mappings = {
        "/api/search"(controller: "search", action: "search", method: "GET", parseRequest: true)
        "/api/signup"(controller: "user", action: "signUp", method: "POST")

        "/api/user"(controller:"user", action: "show_user", method: "GET")

        "/"(view:"/index")
        "/**"(view:"/index")

        "/view/event"(controller: "event", action: "show", method: "GET", parseRequest: true)

        "/api/event/update_rating"(controller: "event", action: "update_rating", method: "PUT")

        "/api/event/create_event"(controller: "event", action: "create_event", method: "POST", parseRequest: true)

        "/welcome_search"(controller: "welcome", action:"search", method: "GET", parseRequest: true)

        "/api/dashboard"(controller: "dashboard", action:"search", method: "GET", parseRequest: true)

    }
}
