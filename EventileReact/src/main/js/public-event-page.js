/**
 * Created by gary on 2017-03-09.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';

import { withRouter } from 'react-router';

function checkStatus(response) {
    if(response.status >= 200 && response.status < 300) {
        return response.json()
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class PublicEventPage extends React.Component {

    constructor() {
        super();
        this.getEvent = this.getEvent.bind(this);
        this.fail = this.fail.bind(this);
        this.success_found_event = this.success_found_event.bind(this);
        this.render = this.render.bind(this);
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
            loaded: false,
            users_rating: '',
            start_date_local: '',
            start_date_local_time: '',
            start_date_timezone: '',
            event_comments_ids: [],
            event_comments: [],
            related_events: [],
            has_comments: false,
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
            this.setState({rating: event_result.average_rating});
        }
        if (event_result.image_url != ""){
            this.setState({image_url: event_result.img_url});
        }
        if (event_result.comments.length != 0){
            this.setState({event_comments: event_result.comments, has_comments: true});
        } else {
            this.setState({has_comments: false});
        }
    }

    fail(error) {
        if (error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/"}
            })
        }
        let query = this.props.location.query.q;
        fetch("/show_created_event_public?q=" + query)
            .then(checkStatus)
            .then(this.success_found_event)
            .catch(this.fail);

    }

    getEvent() {
        let query = this.props.location.query.q;
        fetch("/view/event?q=" + query)
            .then(checkStatus)
            .then(this.success_found_event)
            .then(this.getRelatedEvents)
            .catch(this.fail)

    }

    getRelatedEvents(){
        let query = this.props.location.query.q;
        fetch("/get_related_events_public?current_event_category=" + this.state.category + "&q=" + query)
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
        console.log("failed getting related events ", error)
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getEvent();
        }
        let comments = this.state.event_comments.map( (comment) => {
            return <div className="row">
                <p>{comment} </p>
            </div>
        });
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
                        Category: {related_event.category_name} </small>
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
                            <div className="col-md-4">
                                <fieldset className="rating">
                                    <legend>Ratings</legend>
                                    <p>Average Rating: {this.state.rating}</p>
                                </fieldset>
                            </div>
                        </div>

                    </div>
                </div>

                <div className="col-md-10">
                    <h2> Comments: </h2> <hr/>
                    {this.state.has_comments ? {comments} : <p>No comments yet! Register or login and be the first to comment!</p>}
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

export default withRouter(PublicEventPage);