/**
 * Created by gary on 2017-03-11.
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

class HomeDashboard extends React.Component {

    constructor() {
        super();
        this.getPreferenceEvents = this.getPreferenceEvents.bind(this);
        this.getLocation = this.getLocation.bind(this);
        this.fail = this.fail.bind(this);
        this.success = this.success.bind(this);
        this.success_ip = this.success_ip.bind(this);
        this.success_got_user = this.success_got_user.bind(this);

        this.state = {
            events: [],
            loaded: false,
            location: 'London, Ontario',
            user_has_prefs: false,
            user_prefs_ids: [],
            auth: JSON.parse(localStorage.auth)
        }
    }

    getLocation(){
        fetch("api.db-ip.com/addrinfo?api_key=bc2ab711d740d7cfa6fcb0ca8822cb327e38844f&addr=129.100.93.162", {
            method: "GET"
        })
            .then(checkStatus)
            .then(this.success_ip)
            .catch(this.fail)
    }

    success(events) {
        console.log("Search result", events);
        this.setState({events: events, loaded: true});
    }

    success_ip(ip) {
        console.log("Search result", ip);
        this.setState({location: ip.city});
    }

    success_got_user(user) {
        // update the states with the user JSON object
        console.log("dashboard success: user = ", user);

        if (user.preferences.length == 0){
            this.setState({loaded: true, user_has_prefs: false, user_prefs_ids: user.category_ids});
        } else {
            this.setState({loaded: true, user_has_prefs: true, user_prefs_ids: user.category_ids});
        }
        this.getPreferenceEvents()
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({loaded: true});
    }

    getUser(){
        let token = this.state.auth.access_token; // authentication token to make sure user is signed in/authorized

        fetch("/api/user", {        // GET the user from the usercontroller, make REST call
            headers: {
                'Authorization': 'Bearer ' + token // pass authentication token as a header to the REST API call
            }
        })
            .then(checkStatus)
            .then(this.success_got_user)
            .catch(this.fail)

    }

    getPreferenceEvents(){
        let token = this.state.auth.access_token; // authentication token to make sure user is signed in/authorized

        console.log("Searching based by preferences...");

        let preference_ids = this.state.user_prefs_ids;
        console.log("user's prefs = " + preference_ids);

        let has_prefs = this.state.user_has_prefs;
        console.log("has_prefs " + has_prefs);

        // if the user did not select any preferences, then just search by nearby like the Welcome page
        if (has_prefs == false){
            console.log("user did not select any");
            fetch("/welcome_search?location=" + "London, Ontario")
                .then(checkStatus)
                .then(this.success)
                .catch(this.fail)

        } else {
            // search based on the preferred categories if they have preferences
            console.log("user selected prefs");

            fetch("/api/dashboard?prefs=" + preference_ids , {
                headers: {
                    'Authorization': 'Bearer ' + token // pass authentication token as a header to the REST API call
                }
            })
                .then(checkStatus)
                .then(this.success)
                .catch(this.fail)
        }
    }

    getImageURL(event){
        return event.img_url;
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getUser();
        }
        let events = this.state.events.map( (event) => {
            return <div className="card">
                <a href={"/event?q=" + event.eventbrite_id} target="_self">
                    <img className="card-img-top img-fluid" src={this.getImageURL(event)}/>
                </a>
                <div className="card-block">
                    <a href={"/event?q=" + event.eventbrite_id} target="_self">
                        <h4 className="card-title">{event.name}</h4></a>
                    <p className="card-text">{event.description}</p><br/>
                </div>
                <div className="card-footer">
                    {this.state.user_has_prefs ?
                        <small className="text-muted">
                            Because you liked: {event.category_name} </small>
                    : <small className="text-muted">
                            Category: {event.category_name}</small>
                    }
                </div>
            </div>
        });
        return (

            <div className="container">
                <div>
                    <center><h2> Your Home Dashboard </h2></center>
                    <hr/>
                    <h3>Here are your personalized events:</h3>
                    <br/>
                    <div className="card-columns">
                        {events}
                    </div>
                </div>
            </div>


        )
    }

}



export default withRouter(HomeDashboard);