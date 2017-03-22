package grails3.example

class Event {

<<<<<<< HEAD
    static hasMany = [comments: Comment]
=======
    static hasMany = [comments:Comment]
>>>>>>> 786ff47e010fdb16a5dc76808a8ea5009a86256a

    static constraints = {
        // need to make nullable since grails rejects storing the event in the database if they are null
        description nullable: true
        start_date nullable: true
        total_rating nullable: true
        num_ratings nullable: true
        average_rating nullable: true
        img_url nullable: true


    }

    String name
    String description
    String eventbrite_id
    String start_date
    String eventbrite_url
    String category_name
    int total_rating
    int num_ratings
    float average_rating
    String img_url

}
