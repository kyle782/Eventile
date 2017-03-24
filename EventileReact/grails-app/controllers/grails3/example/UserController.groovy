package grails3.example

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.web.RequestParameter
import grails3.example.util.OptionalCategory
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestParam


class UserController {

    static responseFormats = ['json']

    def userService
    def springSecurityService

    def signUp(@RequestParameter('username') String username, @RequestParameter('password') String password,
               @RequestParameter('age') String age, @RequestParameter('location') String location,
                @RequestParameter('pref_music') boolean pref_music,
               @RequestParameter('pref_bus_prof') boolean pref_bus_prof,
               @RequestParameter('pref_food_drink') boolean pref_food_drink,
               @RequestParameter('pref_comm_culture') boolean pref_comm_culture,
               @RequestParameter('pref_perf_vis_art') boolean pref_perf_vis_art,
               @RequestParameter('pref_film_media_ent') boolean pref_film_media_ent,
               @RequestParameter('pref_sports_fitness') boolean pref_sports_fitness,
               @RequestParameter('pref_health_well') boolean pref_health_well,
               @RequestParameter('pref_sci_tech') boolean pref_sci_tech,
               @RequestParameter('pref_trav_outd') boolean pref_trav_outd,
               @RequestParameter('pref_char_games') boolean pref_char_games,
               @RequestParameter('pref_religion_spirit') boolean pref_religion_spirit,
               @RequestParameter('pref_family_edu') boolean pref_family_edu,
               @RequestParameter('pref_season_holi') boolean pref_season_holi,
               @RequestParameter('pref_gov_poli') boolean pref_gov_poli,
               @RequestParameter('pref_fash_beaut') boolean pref_fash_beaut,
               @RequestParameter('pref_home_life') boolean pref_home_life,
               @RequestParameter('pref_auto_boat_air') boolean pref_auto_boat_air,
               @RequestParameter('pref_hobbies_ints') boolean pref_hobbies_ints,
               @RequestParameter('pref_other') boolean pref_other
    ) {

        def user = userService.signUp(username, password, age, location,
                pref_music,
                pref_bus_prof,
                pref_food_drink,
                pref_comm_culture,
                pref_perf_vis_art,
                pref_film_media_ent,
                pref_sports_fitness,
                pref_health_well,
                pref_sci_tech,
                pref_trav_outd,
                pref_char_games,
                pref_religion_spirit,
                pref_family_edu,
                pref_season_holi,
                pref_gov_poli,
                pref_fash_beaut,
                pref_home_life,
                pref_auto_boat_air,
                pref_hobbies_ints,
                pref_other
        )
        def payload = [username: user.username] as Object
        respond payload, status: HttpStatus.CREATED
    }

    def handleUserExists(UserExistsException userExistsException) {
        def payload = [error: userExistsException.message] as Object
        respond payload, status: HttpStatus.BAD_REQUEST
    }

    def handleIllegalArgument(IllegalArgumentException ex) {
        def payload = [error: ex.message] as Object
        respond payload, status: HttpStatus.BAD_REQUEST
    }

    // mapped to /api/user for the getUser() function in user-page.js
    @Secured(['ROLE_USER'])
    def show_user(){
        User user = User.get(springSecurityService.principal.id)
        respond user
    }

    @Secured(['ROLE_USER'])
    def add_user_RSVP(@RequestParameter('eventbrite_id') String eventbrite_id){
        User user = User.get(springSecurityService.principal.id)
        Event event = Event.findByEventbrite_id(eventbrite_id)

        if (!(event.attendees = user)){
            System.out.println(event.errors)
        }
        event.save()

        respond status: HttpStatus.ACCEPTED

    }

    @Secured(['ROLE_USER'])
    def remove_user_RSVP(@RequestParameter('eventbrite_id') String eventbrite_id){
        User user = User.get(springSecurityService.principal.id)
        Event event = Event.findByEventbrite_id(eventbrite_id)

        event.attendees = null

        event.save()

        respond status: HttpStatus.ACCEPTED

    }

    @Secured(['ROLE_USER'])
    def get_rsvp_events(){
        User user = User.get(springSecurityService.principal.id)
        def rsvp_events = user.getRsvp_events()
        respond rsvp_events
    }

    @Secured(['ROLE_USER'])
    def get_user_rated_events(){
        User user = User.get(springSecurityService.principal.id)
        def user_ratings = user.getRatings()
        respond user_ratings
    }

    @Secured(['ROLE_USER'])
    def get_users_rating(@RequestParameter('event_id') String event_id){

        User user = User.get(springSecurityService.principal.id)

        Event event = Event.findByEventbrite_id(event_id)

        def users_rating = Rating.findByEventAndRater(event, user)

        respond users_rating
    }
    
    @Secured(['ROLE_USER'])
    def edit(@RequestParameter('username') String username, @RequestParameter('password') String password,
             @RequestParameter('age') String age, @RequestParameter('location') String location,
             @RequestParameter('pref_music') boolean pref_music,
             @RequestParameter('pref_bus_prof') boolean pref_bus_prof,
             @RequestParameter('pref_food_drink') boolean pref_food_drink,
             @RequestParameter('pref_comm_culture') boolean pref_comm_culture,
             @RequestParameter('pref_perf_vis_art') boolean pref_perf_vis_art,
             @RequestParameter('pref_film_media_ent') boolean pref_film_media_ent,
             @RequestParameter('pref_sports_fitness') boolean pref_sports_fitness,
             @RequestParameter('pref_health_well') boolean pref_health_well,
             @RequestParameter('pref_sci_tech') boolean pref_sci_tech,
             @RequestParameter('pref_trav_outd') boolean pref_trav_outd,
             @RequestParameter('pref_char_games') boolean pref_char_games,
             @RequestParameter('pref_religion_spirit') boolean pref_religion_spirit,
             @RequestParameter('pref_family_edu') boolean pref_family_edu,
             @RequestParameter('pref_season_holi') boolean pref_season_holi,
             @RequestParameter('pref_gov_poli') boolean pref_gov_poli,
             @RequestParameter('pref_fash_beaut') boolean pref_fash_beaut,
             @RequestParameter('pref_home_life') boolean pref_home_life,
             @RequestParameter('pref_auto_boat_air') boolean pref_auto_boat_air,
             @RequestParameter('pref_hobbies_ints') boolean pref_hobbies_ints,
             @RequestParameter('pref_other') boolean pref_other
    ) {

        User user = User.get(springSecurityService.principal.id)

        if (user.username != username){
            def lowerCaseUsername = username.toLowerCase()
            if (User.findByUsername(lowerCaseUsername)){
                System.out.println("username already taken!")
                throw new UserExistsException("Username ${lowerCaseUsername} is already taken.")
            }
        }

        /** change password too TODO **/
        System.out.println("user.password = " + user.password)
        System.out.println("password = " + password)


        if (user.age != age){
            user.age = age
        }
        if (user.location != location){
            user.location = location
        }

        // process the preferences checkboxes
        if (pref_music){
            if (!user.preferences.contains("Music")){
                user.addToPreferences("Music").save()
                user.addToCategory_ids("103").save()
            }
        } else {
            if (user.preferences.contains("Music")){
                user.removeFromPreferences("Music").save()
                user.removeFromCategory_ids("103").save()
            }
        }

        if (pref_bus_prof){
            if (!user.preferences.contains("Business & Professional")) {
                user.addToPreferences("Business & Professional").save()
                user.addToCategory_ids("101").save()
            }
        } else {
            if (user.preferences.contains("Business & Professional")){
                user.removeFromPreferences("Business & Professional").save()
                user.removeFromCategory_ids("101").save()
            }
        }

        if (pref_food_drink){
            if (!user.preferences.contains("Food & Drink")) {
                user.addToPreferences("Food & Drink").save()
                user.addToCategory_ids("110").save()
            }
        } else {
            if (user.preferences.contains("Food & Drink")) {
                user.removeFromPreferences("Food & Drink").save()
                user.removeFromPreferences("110").save()
            }
        }

        if (pref_comm_culture){
            if (!user.preferences.contains("Community & Culture")) {
                user.addToPreferences("Community & Culture").save()
                user.addToCategory_ids("113").save()
            }
        } else {
            if (user.preferences.contains("Community & Culture")) {
                user.removeFromPreferences("Community & Culture").save()
                user.removeFromPreferences("113").save()
            }
        }

        if (pref_perf_vis_art){
            if (!user.preferences.contains("Performing & Visual Arts")) {
                user.addToPreferences("Performing & Visual Arts").save()
                user.addToCategory_ids("105").save()
            }
        } else {
            if (user.preferences.contains("Performing & Visual Arts")) {
                user.removeFromPreferences("Performing & Visual Arts").save()
                user.removeFromPreferences("105").save()
            }
        }

        if (pref_film_media_ent){
            if (!user.preferences.contains("Film, Media & Entertainment")) {
                user.addToPreferences("Film, Media & Entertainment").save()
                user.addToCategory_ids("104").save()
            }
        } else {
            if (user.preferences.contains("Film, Media & Entertainment")) {
                user.removeFromPreferences("Film, Media & Entertainment").save()
                user.removeFromPreferences("104").save()
            }
        }

        if (pref_sports_fitness){
            if (!user.preferences.contains("Sports & Fitness")) {
                user.addToPreferences("Sports & Fitness").save()
                user.addToCategory_ids("108").save()
            }
        } else {
            if (user.preferences.contains("Sports & Fitness")) {
                user.removeFromPreferences("Sports & Fitness").save()
                user.removeFromPreferences("108").save()
            }
        }

        if (pref_health_well){
            if (!user.preferences.contains("Health & Wellness")) {
                user.addToPreferences("Health & Wellness").save()
                user.addToCategory_ids("107").save()
            }
        } else {
            if (user.preferences.contains("Health & Wellness")) {
                user.removeFromPreferences("Health & Wellness").save()
                user.removeFromPreferences("107").save()
            }
        }

        if (pref_sci_tech){
            if (!user.preferences.contains("Science & Technology")) {
                user.addToPreferences("Science & Technology").save()
                user.addToCategory_ids("102").save()
            }
        } else {
            if (user.preferences.contains("Science & Technology")) {
                user.removeFromPreferences("Science & Technology").save()
                user.removeFromPreferences("102").save()
            }
        }

        if (pref_trav_outd){
            if (!user.preferences.contains("Travel & Outdoor")) {
                user.addToPreferences("Travel & Outdoor").save()
                user.addToCategory_ids("109").save()
            }
        } else {
            if (user.preferences.contains("Travel & Outdoor")) {
                user.removeFromPreferences("Travel & Outdoor").save()
                user.removeFromPreferences("109").save()
            }
        }

        if (pref_char_games){
            if (!user.preferences.contains("Charity & Games")) {
                user.addToPreferences("Charity & Games").save()
                user.addToCategory_ids("111").save()
            }
        } else {
            if (user.preferences.contains("Charity & Games")) {
                user.removeFromPreferences("Charity & Games").save()
                user.removeFromPreferences("111").save()
            }
        }

        if (pref_religion_spirit){
            if (!user.preferences.contains("Religion & Spirituality")) {
                user.addToPreferences("Religion & Spirituality").save()
                user.addToCategory_ids("114").save()
            }
        } else {
            if (user.preferences.contains("Religion & Spirituality")) {
                user.removeFromPreferences("Religion & Spirituality").save()
                user.removeFromPreferences("114").save()
            }
        }

        if (pref_family_edu){
            if (!user.preferences.contains("Family & Education")) {
                user.addToPreferences("Family & Education").save()
                user.addToCategory_ids("115").save()
            }
        } else {
            if (user.preferences.contains("Family & Education")) {
                user.removeFromPreferences("Family & Education").save()
                user.removeFromPreferences("115").save()
            }
        }

        if (pref_season_holi){
            if (!user.preferences.contains("Seasonal & Holiday")) {
                user.addToPreferences("Seasonal & Holiday").save()
                user.addToCategory_ids("116").save()
            }
        } else {
            if (user.preferences.contains("Seasonal & Holiday")) {
                user.removeFromPreferences("Seasonal & Holiday").save()
                user.removeFromPreferences("116").save()
            }
        }

        if (pref_gov_poli){
            if (!user.preferences.contains("Government & Politics")) {
                user.addToPreferences("Government & Politics").save()
                user.addToCategory_ids("112").save()
            }
        } else {
            if (user.preferences.contains("Government & Politics")) {
                user.removeFromPreferences("Government & Politics").save()
                user.removeFromPreferences("112").save()
            }
        }

        if (pref_fash_beaut){
            if (!user.preferences.contains("Fashion & Beauty")) {
                user.addToPreferences("Fashion & Beauty").save()
                user.addToCategory_ids("106").save()
            }
        } else {
            if (user.preferences.contains("Fashion & Beauty")) {
                user.removeFromPreferences("Fashion & Beauty").save()
                user.removeFromPreferences("106").save()
            }
        }

        if (pref_home_life){
            if (!user.preferences.contains("Home & Lifestyle")) {
                user.addToPreferences("Home & Lifestyle").save()
                user.addToCategory_ids("117").save()
            }
        } else {
            if (user.preferences.contains("Home & Lifestyle")) {
                user.removeFromPreferences("Home & Lifestyle").save()
                user.removeFromPreferences("117").save()
            }
        }

        if (pref_auto_boat_air){
            if (!user.preferences.contains("Auto, Boat & Air")) {
                user.addToPreferences("Auto, Boat & Air").save()
                user.addToCategory_ids("118").save()
            }
        } else {
            if (user.preferences.contains("Auto, Boat & Air")) {
                user.removeFromPreferences("Auto, Boat & Air").save()
                user.removeFromPreferences("118").save()
            }
        }

        if (pref_hobbies_ints){
            if (!user.preferences.contains("Hobbies & Special Interest")) {
                user.addToPreferences("Hobbies & Special Interest").save()
                user.addToCategory_ids("119").save()
            }
        } else {
            if (user.preferences.contains("Hobbies & Special Interest")) {
                user.removeFromPreferences("Hobbies & Special Interest").save()
                user.removeFromPreferences("119").save()
            }
        }

        if (pref_other){
            if (!user.preferences.contains("Other")) {
                user.addToPreferences("Other").save()
                user.addToCategory_ids("199").save()
            }
        } else {
            if (user.preferences.contains("Other")) {
                user.removeFromPreferences("Other").save()
                user.removeFromPreferences("199").save()
            }
        }


        user.save()

        respond user
    }

    def checkUserPreferences(){

    }

}
