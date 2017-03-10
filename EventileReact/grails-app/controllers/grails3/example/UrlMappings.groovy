package grails3.example

class UrlMappings {

    static mappings = {
        "/api/search"(controller: "search", action: "search", method: "GET", parseRequest: true)
        "/api/signup"(controller: "user", action: "signUp", method: "POST")


        "/"(view:"/index")
        "/**"(view:"/index")
        "/api/event"(controller: "event", action: "show", method: "GET", parseRequest: true)


    }
}
