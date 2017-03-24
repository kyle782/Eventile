package grails3.example

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.web.RequestParameter
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
        } else {
            System.out.println("was able to set the event to the user")
        }
        event.save()

        respond status: HttpStatus.ACCEPTED

    }

    @Secured(['ROLE_USER'])
    def remove_user_RSVP(@RequestParameter('eventbrite_id') String eventbrite_id){
        User user = User.get(springSecurityService.principal.id)
        Event event = Event.findByEventbrite_id(eventbrite_id)

        event.attendees = null

        System.out.println("was able to remove the event from the user")

        event.save()
        System.out.println("event's attendees now " + event.attendees)

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

}
