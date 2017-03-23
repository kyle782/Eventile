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

        "/api/event/get_user_created_events"(controller: "event", action: "get_user_created_events", method: "GET", parseRequest: true)

        "/welcome_search"(controller: "welcome", action:"search", method: "GET", parseRequest: true)

        "/api/dashboard"(controller: "dashboard", action:"search", method: "GET", parseRequest: true)

        "/api/user/addRSVP"(controller: "user", action: "add_user_RSVP", method: "PUT")

        "/api/user/get_user_rsvp_events"(controller: "user", action: "get_rsvp_events", method: "GET", parseRequest: true)

    }
}
