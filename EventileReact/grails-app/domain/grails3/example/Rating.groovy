package grails3.example

class Rating {

    static constraints = {
        users_rating nullable: true
        event_name nullable: true
        eventbrite_id nullable: true
    }

    static belongsTo = [rater: User, event: Event]

    int users_rating
    String event_name
    String eventbrite_id

}
