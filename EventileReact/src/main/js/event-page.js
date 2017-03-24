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
        this.handle_first_time = this.handle_first_time.bind(this);
        this.handle_have_gone = this.handle_have_gone.bind(this);
        this.update_comments = this.update_comments.bind(this);
        this.success_update_comment = this.success_update_comment.bind(this);
        this.getRelatedEvents = this.getRelatedEvents.bind(this);
        this.success_got_related_events = this.success_got_related_events.bind(this);


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
            user_first_time: false,
            user_have_gone: false,
            loaded: false,
            users_rating: '',
            start_date_local: '',
            start_date_local_time: '',
            start_date_timezone: '',
            auth: JSON.parse(localStorage.auth),
            event_comments_ids: [],
            event_comments: [],
            related_events: []
        }

    }

    success_found_event(event_result) {
        console.log("got event with address info? ", event_result);
        this.setState({loaded: true});
        this.setState({
            name: event_result.name, description: event_result.description,
            category: event_result.category_name, venue_address: event_result.venue_address,
            venue_longitude: event_result.longitude, venue_latitude: event_result.latitude,
            eventbrite_id: event_result.eventbrite_id, start_date_local: event_result.start_date_local,
            start_date_timezone: event_result.start_date_timezone, start_date_local_time: event_result.start_date_local_time
        });
        if (event_result.num_ratings != 0) {
            this.setState({rating: event_result.average_rating})
        }
        if (event_result.image_url != ""){
            this.setState({image_url: event_result.img_url})
        }
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

    fail_comment(error) {
        console.log("FAILED UPDATING COMMENT = ", error);
    }

    getEvent() {
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        fetch("/view/event?q=" + query, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_found_event)
            .then(this.checkUserRSVPd)
            .then(this.getRelatedEvents)
            .catch(this.fail)

    }


    checkUserRSVPd(){
        let token = this.state.auth.access_token;
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
        console.log("COMMENT FORM = ", form);

        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        // make PUT REST call to be handled by EventController (mapped in urlMappings.groovy)
        fetch("/api/event/update_comments?q=" + query + "&c=" + form.comment, { // parameters for the method
            method: 'PUT',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_update_comment)
            .catch(this.fail_comment);
    }

    success_update_comment(comment_response){
        console.log("event results after updating comments =  ", comment_response);
        this.setState({event_comments: this.state.event_comments.concat(comment_response.author_name + ": " + comment_response.comment_body)});
    }

    /**
     * Method to update the rating for the event. Called when clicking on the button
     * (TODO: disable rating buttons if already rated)
     * @param new_rating, from the form on the page (just the button for now)
     */
    update_rating(new_rating) {
        let token = this.state.auth.access_tokgen;
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
                .catch(this.fail_comment);

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
                .catch(this.fail_comment);


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

    handle_first_time(){
        if (this.state.user_RSVP){
            // user is RSVP'd to the event, remove the first time
            this.setState({user_first_time: false});

            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/remove_first_time?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .catch(this.fail_comment);

        } else {
            // user is not RSVP'd to the event, add it to their rsvp
            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/add_first_time?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .catch(this.fail_comment);


        }
    }


    handle_have_gone(){
        if (this.state.user_RSVP){
            // user is RSVP'd to the event, remove the have gone
            this.setState({user_have_gone: false});

            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/remove_have_gone?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .catch(this.fail_comment);

        } else {
            // user is not RSVP'd to the event, add it to their rsvp
            let token = this.state.auth.access_token;

            let eventbrite_id = this.state.eventbrite_id;

            // make PUT REST call to be handled by UserController (mapped in urlMappings.groovy)
            fetch("/api/user/add_have_gone?eventbrite_id=" + eventbrite_id, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(checkStatus)
                .catch(this.fail_comment);


        }
    }

    getRelatedEvents(){
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;
        console.log("token = ", token);

        fetch("/api/event/get_related_events?current_event_category=" + this.state.category + "&q=" + query, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_got_related_events)
            .catch(this.failed_related)
    }

    success_got_related_events(related_events){
        console.log("got related events = ", related_events);
        this.setState({related_events: related_events});
        console.log("related_events state is now " + this.state.related_events)
    }

    getImageURL(event){
        return event.img_url;
    }

    failed_related(error) {
        if (error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false) {
            this.getEvent();
        }
        let comments = this.state.event_comments.map( (comment) => {
            return <div className="row">
                <p>{comment} </p>
            </div>
        });
        let RSVPCreated = () => <p className="alert alert-success">You are now RSVP'd to this event! Check it out in your profile page.</p>;
        let RSVPRemoved = () => <p className="alert alert-info">You are no longer RSVP'd to this event.</p>;
        let AnticipationCreated = () => <p className="alert alert-success">You have added you anticipation! Check it out in your profile page.</p>;
        let AnticipationRemoved = () => <p className="alert alert-info">You have removed your anticipation to this event.</p>;


        let related_events = this.state.related_events.map( (related_event) => {
            return <div className="card">
                <a href={"/event?q=" + related_event.eventbrite_id} target="_self">
                    <img className="card-img-top img-fluid" src={this.getImageURL(related_event)}/>
                </a>
                <div className="card-block">
                    <a href={"/event?q=" + related_event.eventbrite_id} target="_self">
                        <h4 className="card-title">{related_event.name}</h4></a>
                    <p className="card-text">{related_event.description}</p><br/>
                </div>
                <div className="card-footer">
                    <small className="text-muted">
                        Because you liked: {related_event.category_name} </small>
                </div>
            </div>
        });

        return (

            <div className="container">
                <br/>
                <center><h2>Event: {this.state.name}</h2></center>
                <hr/>

                <div className="row">

                    <div className="col-md-7">
                        <img className="img-responsive" src={this.state.image_url} alt=""/>
                        <br/>

                        {this.state.loaded ? <div className="col-md-6">
                            <iframe width="550" height="450" src={"https://www.google.com/maps/embed/v1/place?q=" + this.state.venue_address +
                            "&zoom=17&key=AIzaSyDxYMTYMBgLXzsw8WXEHuPX8g2sNzHEzyk"}>
                            </iframe>
                        </div>
                            : null
                        }
                        <br/>
                    </div>

                    <br/>

                    <div className="col-md-5">
                        <h3>Event Description</h3>
                        <p>{this.state.description}</p>
                        <h4>Event Category</h4>
                        <p>{this.state.category}</p>
                        <h4>Location</h4>
                        <a href={"http://maps.google.com/maps?q=" + this.state.venue_latitude + "," + this.state.venue_longitude}> <p>{this.state.venue_address}</p> </a>
                        <p>Start Date: {this.state.start_date_local}</p>
                        <p>Start Time: {this.state.start_date_local_time}</p>
                        <p>Time Zone: {this.state.start_date_timezone}</p>

                        <div className="row">
                        <div className="col-md-7">
                            {this.state.user_entered_RSVP ? this.state.user_RSVP ?
                                    <div>
                                        <button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>Revoke RSVP</button>
                                        <RSVPCreated/>
                                        <button className="btn btn-default" type="first-time" onClick={() => this.handle_first_time()}>First Time!</button>
                                        <AnticipationCreated/>
                                        <button className="btn btn-default" type="have-gone" onClick={() => this.handle_have_gone()}>I Have Gone Before!</button>
                                        <AnticipationRemoved/>
                                    </div>
                                    : <div><button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>RSVP!</button>
                                        <RSVPRemoved/></div>
                                : <button className="btn btn-default" type="RSVP" onClick={() => this.handleRSVP()}>RSVP!</button>
                            }
                        </div>
                        <br/><br/>
                        </div>

                        <div className="row">
                        <div className="col-md-4">
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
                        </div>

                    </div>
                </div>


                <div className="col-md-10">
                    <h2> Comments: </h2> <hr/>
                    {comments}
                    <CommentForm submitLabel="Post Comment" onSubmit={this.update_comments} ref={ (ref) => this.form = ref }/>
                </div>

                <hr/>

                <div className="row">
                {this.state.loaded ?
                    <div className="card-deck">
                        {related_events}
                    </div>
                    : null
                }
                </div>


            </div>
        )
    }
}

export default withRouter(EventPage);