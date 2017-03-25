package grails3.example

import grails.transaction.Transactional
import grails3.example.util.OptionalCategory
import java.util.ArrayList

class UserService {

    public static final String ROLE_USER = 'ROLE_USER'

    @Transactional(rollbackFor = [UserExistsException, IllegalArgumentException])
    def signUp(String username, String password, String age, String location,
               boolean pref_music,
               boolean pref_bus_prof,
               boolean pref_food_drink,
               boolean pref_comm_culture,
               boolean pref_perf_vis_art,
               boolean pref_film_media_ent,
               boolean pref_sports_fitness,
               boolean pref_health_well,
               boolean pref_sci_tech,
               boolean pref_trav_outd,
               boolean pref_char_games,
               boolean pref_religion_spirit,
               boolean pref_family_edu,
               boolean pref_season_holi,
               boolean pref_gov_poli,
               boolean pref_fash_beaut,
               boolean pref_home_life,
               boolean pref_auto_boat_air,
               boolean pref_hobbies_ints,
               boolean pref_other
    ) {
        if(!username || !password) throw new IllegalArgumentException("username and password must not be blank")

        def lowerCaseUsername = username.toLowerCase()
        def role = Optional
            .ofNullable(Role.findByAuthority(ROLE_USER))
            .orElse(new Role(authority: ROLE_USER).save())

        def user = use(OptionalCategory) {
            Optional
            .ofNullable(User.findByUsername(lowerCaseUsername))
            .andThrow { User user ->
                new UserExistsException("User ${lowerCaseUsername} is already signed up")
            }
            .orElse(new User(username: lowerCaseUsername, password: password,
                    age: age, location: location).save())
        } as User

        // process the preferences checkboxes
        if (pref_music){
            user.addToPreferences("Music").save()
            user.addToCategory_ids("103").save()
        }
        if (pref_bus_prof){
            user.addToPreferences("Business and Professional").save()
            user.addToCategory_ids("101").save()
        }
        if (pref_food_drink){
            user.addToPreferences("Food and Drink").save()
            user.addToCategory_ids("110").save()
        }
        if (pref_comm_culture){
            user.addToPreferences("Community and Culture").save()
            user.addToCategory_ids("113").save()
        }
        if (pref_perf_vis_art){
            user.addToPreferences("Performing and Visual Arts").save()
            user.addToCategory_ids("105").save()
        }
        if (pref_film_media_ent){
            user.addToPreferences("Film, Media and Entertainment").save()
            user.addToCategory_ids("104").save()
        }
        if (pref_sports_fitness){
            user.addToPreferences("Sports and Fitness").save()
            user.addToCategory_ids("108").save()
        }
        if (pref_health_well){
            user.addToPreferences("Health and Wellness").save()
            user.addToCategory_ids("107").save()
        }
        if (pref_sci_tech){
            user.addToPreferences("Science and Technology").save()
            user.addToCategory_ids("102").save()
        }
        if (pref_trav_outd){
            user.addToPreferences("Travel and Outdoor").save()
            user.addToCategory_ids("109").save()
        }
        if (pref_char_games){
            user.addToPreferences("Charity and Games").save()
            user.addToCategory_ids("111").save()
        }
        if (pref_religion_spirit){
            user.addToPreferences("Religion and Spirituality").save()
            user.addToCategory_ids("114").save()
        }
        if (pref_family_edu){
            user.addToPreferences("Family and Education").save()
            user.addToCategory_ids("115").save()
        }
        if (pref_season_holi){
            user.addToPreferences("Seasonal and Holiday").save()
            user.addToCategory_ids("116").save()
        }
        if (pref_gov_poli){
            user.addToPreferences("Government and Politics").save()
            user.addToCategory_ids("112").save()
        }
        if (pref_fash_beaut){
            user.addToPreferences("Fashion and Beauty").save()
            user.addToCategory_ids("106").save()
        }
        if (pref_home_life){
            user.addToPreferences("Home and Lifestyle").save()
            user.addToCategory_ids("117").save()
        }
        if (pref_auto_boat_air){
            user.addToPreferences("Auto, Boat and Air").save()
            user.addToCategory_ids("118").save()
        }
        if (pref_hobbies_ints){
            user.addToPreferences("Hobbies and Special Interest").save()
            user.addToCategory_ids("119").save()
        }
        if (pref_other){
            user.addToPreferences("Other").save()
            user.addToCategory_ids("199").save()
        }

        System.out.println("user's category ids = " + user.category_ids)

        log.debug("Created user ${user}")

        UserRole.create(user, role)
        log.debug("Granted user role ${role.authority}#${role.id}")

        user
    }
}
