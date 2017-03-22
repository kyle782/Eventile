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
        user1.addToPreferences("Film, Media & Entertainment").save()
        user1.addToCategory_ids("104").save()

<<<<<<< HEAD
        def testEvent = new Event(name: "test1", description: "test", eventbrite_id: "0000",
                start_date: "test", eventbrite_url: "test", category_name: "test", total_rating: 5,
                num_ratings: 5, average_rating: 5.0, img_url: "test").save()
        testEvent.addToComments(new Comment(comment_body: "test comment")).save()

        System.out.println("test event's name: " + testEvent.name)
        System.out.println("test event's comments = " + testEvent.comments.comment_body)




=======
        def testEvent = new Event(name: "test1", description: "test", eventbrite_id: "0000", start_date: "test", eventbrite_url: "test", category_name: "test", total_rating: 5, num_ratings: 5, average_rating: 5, img_url: "test").save()
        def testcomment = new Comment(comment: "test comment").save()
        testEvent.addToComments(testcomment).save()
        System.out.println(testEvent.getComments())
>>>>>>> 786ff47e010fdb16a5dc76808a8ea5009a86256a

        def user2 = new User(username: 'test2', password: '2212', age: '100', location: 'London').save()
        UserRole.create(user2, role, true)
    }
    def destroy = {
    }
}
