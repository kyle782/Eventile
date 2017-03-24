package grails3.example

class Event {

    static hasMany = [comments: Comment, ratings: Rating]

    static belongsTo = [creator: User, attendees: User, new_attendees: User, previous_attendees: User]

    static constraints = {
        // need to make nullable since grails rejects storing the event in the database if they are null
        description nullable: true
        start_date_local nullable: true
        start_date_local nullable: true
        start_date_timezone nullable: true
        total_rating nullable: true
        num_ratings nullable: true
        average_rating nullable: true
        img_url nullable: true
        location nullable: true
        venue_address nullable: true
        longitude nullable: true
        latitude nullable: true
        eventbrite_venue_id nullable: true
        creator nullable: true
        attendees nullable: true
        ratings nullable: true
        new_attendees nullable: true
        previous_attendees nullable: true
    }

    String name
    String description
    String eventbrite_id
    String start_date_local
    String start_date_timezone
    String eventbrite_url
    String category_name
    int total_rating
    int num_ratings
    float average_rating
    String img_url
    String location
    String eventbrite_venue_id
    String venue_address
    String longitude
    String latitude


}
