import grails3.example.Comment
import grails3.example.Event
import grails3.example.Role
import grails3.example.User
import grails3.example.UserRole

class BootStrap {

    def init = { servletContext ->
        def role = new Role(authority: 'ROLE_USER').save()
        def user1 = new User(username: 'test', password: '2212', age: '100', location: 'London').save()
        UserRole.create(user1, role, true)
        user1.addToPreferences("Music").save()
        user1.addToCategory_ids("103").save()
        user1.addToPreferences("Film, Media and Entertainment").save()
        user1.addToCategory_ids("104").save()

        def testEvent = new Event(name: "test1", description: "test", eventbrite_id: "0000",
                start_date: "test", eventbrite_url: "test", category_name: "Music ", total_rating: 5,
                num_ratings: 5, average_rating: 5.0, img_url: "test").save()
        testEvent.addToComments(new Comment(comment_body: "test comment")).save()

        def user2 = new User(username: 'test2', password: '2212', age: '100', location: 'London').save()
        UserRole.create(user2, role, true)
    }
    def destroy = {
    }
}
