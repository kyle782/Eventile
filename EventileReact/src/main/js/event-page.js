/**
 * Created by gary on 2017-03-09.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';
import CommentForm from './comment-form';


import {withRouter} from 'react-router';

function checkStatus(response) {
    if (response.status >= 200 && response.status < 300) {
        return response.json()
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};


class EventPage extends React.Component {

    constructor() {
        super();
        this.getEvent = this.getEvent.bind(this);
        this.fail = this.fail.bind(this);
        this.success_found_event = this.success_found_event.bind(this);
        this.update_rating = this.update_rating.bind(this);
        this.success_update_rating = this.success_update_rating.bind(this);
        this.render = this.render.bind(this);
        this.handleRSVP = this.handleRSVP.bind(this);
        this.success_rsvp = this.success_rsvp.bind(this);
        this.checkUserRSVPd = this.checkUserRSVPd.bind(this);
        this.success_check_user_rsvp = this.success_check_user_rsvp.bind(this);
        this.success_remove_rsvp = this.success_remove_rsvp.bind(this);
        this.update_comments = this.update_comments.bind(this);
        this.success_update_comment = this.success_update_comment(this);

        this.state = {
            name: 'Loading...',
            description: '',
            category: '',
            rating: '.....',
            rated: false,
            image_url: '',
            venue_address: '',
            venue_longitude: '',
            venue_latitude: '',
            eventbrite_id: '',
            user_RSVP: false,
            user_entered_RSVP: false,
            loaded: false,
            users_rating: '',
            auth: JSON.parse(localStorage.auth),
            event_comments: []
        }

    }

    success_found_event(event_result) {
        console.log("got event with address info? ", event_result);
        this.setState({
            name: event_result.name, description: event_result.description,
            category: event_result.category_name, venue_address: event_result.venue_address,
            venue_longitude: event_result.longitude, venue_latitude: event_result.latitude,
            eventbrite_id: event_result.eventbrite_id
            venue_longitude: event_result.longitude, venue_latitude: event_result.latitude
        });
        if (event_result.num_ratings != 0) {
            this.setState({rating: event_result.average_rating})
        }
        if (event_result.image_url != ""){
            this.setState({image_url: event_result.img_url})
        }
        console.log(event_result.comments.length);
        console.log(event_result.comments);
        if (event_result.comments.length !=0){
            this.setState({event_comments: event_result.comments})
        }
    }


    fail(error) {
        if (error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;
        fetch("/api/event/show_created_event?q=" + query, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_found_event)
            .catch(this.fail);


    }

    getEvent() {
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;
        this.setState({loaded: true});

        fetch("/view/event?q=" + query, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_found_event)
            .then(this.checkUserRSVPd)
            .catch(this.fail)

    }

    checkUserRSVPd(){
        let token = this.state.auth.access_token;
        console.log("token = " + token);
        let query = this.props.location.query.q;
        this.setState({loaded: true});

        fetch("/api/event/check_user_rsvp?q=" + query, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_check_user_rsvp)
    }

    success_check_user_rsvp(response){
        console.log("success, did user rsvp = ", response);
        console.log("response length = ", response.length);

        if (response.length != 0){
            this.setState({user_entered_RSVP: true, user_RSVP: true})
        } else {
            this.setState({user_entered_RSVP: false, user_RSVP: false})
        }
    }

    /**
     * Method to update the comments for the event. Called when clicking on the button
     * @param new_comment, from the form on the page (just the button for now)
     */
    update_comments(e) {
        e.preventDefault();
        let form = this.form.data();

        let body = "comment=" + form.comment + "&date=" + form.date;

        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        console.log("token="+token);

        // make PUT REST call to be handled by EventController (mapped in urlMappings.groovy)
        fetch("/api/event/update_comments?q=" + query + "&c=" + form.comment, { // parameters for the method
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            body:body
        })
            .then(checkStatus)
            .then(this.success_update_comment)
            .catch(this.fail);
    }

    success_update_comment(event_result){
        console.log("event_result is ", event_result);
        this.setState({event_comments: event_result.comments});
    }

    /**
     * Method to update the rating for the event. Called when clicking on the button
     * (TODO: disable rating buttons if already rated)
     * @param new_rating, from the form on the page (just the button for now)
     */
    update_rating(new_rating) {
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        this.setState({users_rating: new_rating});

        // make PUT REST call to be handled by EventController (mapped in urlMappings.groovy)
        fetch("/api/event/update_rating?q=" + query + "&r=" + new_rating, { // parameters for the method
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_update_rating)
            .catch(this.fail);
    }

    /**
     * Method to update the state variable with the new rating
     * @param event_result
     */
    success_update_rating(event_result) {
        console.log("success, rating is now ", event_result.average_rating);
        this.setState({rating: event_result.average_rating, rated: true});
    }

    handleRSVP(){
        console.log("ok rsvping");

        if (this.state.user_RSVP){
            // user is RSVP'd to the event, remove the event from their rsvp
            this.setState({user_RSVP: false});

            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/removeRSVP?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .then(this.success_remove_rsvp)
                .catch(this.fail);



        } else {
            // user is not RSVP'd to the event, add it to their rsvp
            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/addRSVP?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .then(this.success_rsvp)
                .catch(this.fail);


        }
    }

    success_rsvp(){
        console.log("rsvp'd!");
        this.setState({user_RSVP: true, user_entered_RSVP: true});
    }

    success_remove_rsvp(){
        console.log("removed rsvp!");
        this.setState({user_RSVP: false, user_entered_RSVP: true});
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false) {
            this.getEvent();
        }
        let comments = this.state.event_comments.map( (comment) => {
            return <div className="main">
                <p>{comment} </p>
            </div>
        });
        let RSVPCreated = () => <p className="alert alert-success">You are now RSVP'd to this event! Check it out in your profile page.</p>;
        let RSVPRemoved = () => <p className="alert alert-info">You are no longer RSVP'd to this event.</p>;

        return (

            <div className="container">
                <br/>
                <center><h2>Event: {this.state.name}</h2></center>
                <hr/>

                <div className="row">

                    <div className="col-md-7">
                        <img className="img-responsive" src={this.state.image_url} alt=""/>
                        <br/>
                        <div className="col-md-7">
                            {this.state.user_entered_RSVP ? this.state.user_RSVP ?
                                    <div>
                                        <button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>Revoke RSVP</button>
                                        <RSVPCreated/>
                                    </div>
                                    : <div><button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>RSVP!</button>
                                         <RSVPRemoved/></div>
                                : <button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>RSVP!</button>
                            }
                        </div>
                        <br/><br/>

                        <div className="col-md-7">
                            <fieldset className="rating">
                                <legend>Ratings</legend>
                                <p>Average Rating: {this.state.rating}</p>
                                <p>Your Rating: {this.state.users_rating}</p>
                                <input type="radio" id="star5" name="rating" value="5"
                                       onClick={() => this.update_rating(5)}/><label className="full" htmlFor="star5"
                                                                                     title="5 stars"/>
                                <input type="radio" id="star4" name="rating" value="4"
                                       onClick={() => this.update_rating(4)}/><label className="full" htmlFor="star4"
                                                                                     title="4 stars"/>
                                <input type="radio" id="star3" name="rating" value="3"
                                       onClick={() => this.update_rating(3)}/><label className="full" htmlFor="star3"
                                                                                     title="3 stars"/>
                                <input type="radio" id="star2" name="rating" value="2"
                                       onClick={() => this.update_rating(2)}/><label className="full" htmlFor="star2"
                                                                                     title="2 stars"/>
                                <input type="radio" id="star1" name="rating" value="1"
                                       onClick={() => this.update_rating(1)}/><label className="full" htmlFor="star1"
                                                                                     title="1 star"/>
                            </fieldset>
                        </div>

                        <div className="col-md-10">
                            <h2> Comments: </h2> <hr/>
                            {comments}
                            <CommentForm submitLabel="Post Comment" onSubmit={this.update_comments} ref={ (ref) => this.form = ref }/>
                        </div>

                    <br/>

                    <div className="col-md-5">
                        <h3>Event Description</h3>
                        <p>{this.state.description}</p>
                        <h4>Event Category</h4>
                        <p>{this.state.category}</p>
                        <h4>Location</h4>
                        <p>{this.state.venue_address}</p>
                        <p>Longitude: {this.state.venue_longitude}</p>
                        <p>Latitude: {this.state.venue_latitude}</p>
                    </div>
                </div>

            </div>
        )
    }
}

export default withRouter(EventPage);