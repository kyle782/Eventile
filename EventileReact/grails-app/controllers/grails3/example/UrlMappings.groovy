package grails3.example

import org.apache.tools.ant.taskdefs.Get

class UrlMappings {

    static mappings = {
        "/api/search"(controller: "search", action: "search", method: "GET", parseRequest: true)
        "/api/signup"(controller: "user", action: "signUp", method: "POST")
        "/api/userPage"(controller:"user", action: "display", method: "GET")

        "/"(view:"/index")
        "/**"(view:"/index")
        "/api/event"(controller: "event", action: "show", method: "GET", parseRequest: true)


    }
}
