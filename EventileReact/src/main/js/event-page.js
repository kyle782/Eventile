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

class EventPage extends React.Component {

    constructor() {
        super();
        this.getEvent = this.getEvent.bind(this);
        this.fail = this.fail.bind(this);
        this.success_found_event = this.success_found_event.bind(this);
        this.update_rating = this.update_rating.bind(this);
        this.success_update_rating = this.success_update_rating.bind(this);
        this.render = this.render.bind(this);

        this.state = {
            name: 'Loading...',
            description: '',
            category: '',
            rating: '.....',
            loaded: false,
            auth: JSON.parse(localStorage.auth)
        }

    }

    success_found_event(event_result) {
        this.setState({name: event_result.name, description: event_result.description,
            category: event_result.category_name});
        if (event_result.num_ratings != 0){
            this.setState({rating: event_result.average_rating})
        }
    }


    fail(error) {
        if(error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    getEvent(){
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        fetch("/api/event?q=" + query , {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_found_event)
            .catch(this.fail)

    }

    /**
     * Method to update the rating for the event. Called when clicking on the button
     * (TODO: disable rating buttons if already rated)
     * @param new_rating, from the form on the page (just the button for now)
     */
    update_rating(new_rating){
        let token = this.state.auth.access_token;
        let query = this.props.location.query.q;

        // make PUT REST call to be handled by EventController (mapped in urlMappings.groovy)
        fetch("/api/event/update_rating?q=" + query + "&r=" + new_rating , { // parameters for the method
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
        this.setState({rating: event_result.average_rating});
    }


    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getEvent();
            this.setState({loaded: true});
        }
        let this_event =
            <div className="col-sm-12 col-md-12 col-lg-12 tweet">
                <b>{this.state.name}</b>: {this.state.description} <br/> Category: {this.state.category} <br/>
                Rating: {this.state.rating}
            </div>;
        return (
            <div>
                <h1>Hello World</h1>
                <div className="col-lg-12">
                    {this_event}
                </div>
                <button type="submit" onClick={() => this.update_rating(5)}>Rate 5 </button>
                <button type="submit" onClick={() => this.update_rating(1)}>Rate 1 </button>
            </div>

        )
    }
}

export default withRouter(EventPage);