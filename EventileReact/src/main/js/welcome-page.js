/**
 * Created by gary on 2017-03-11.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';

import { withRouter } from 'react-router';

function checkStatus(response) {
    if(response.status >= 200 && response.status < 300) {
        return response.json();
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class WelcomePage extends React.Component {

    constructor() {
        super();
        this.getNearbyEvents = this.getNearbyEvents.bind(this);
        //this.getLocation = this.getLocation.bind(this);
        this.fail = this.fail.bind(this);
        this.success = this.success.bind(this);
        this.success_ip = this.success_ip.bind(this);

        this.state = {
            events: [],
            loaded: false,
            location: 'London, Ontario',
        }
    }

    getNearbyEvents() {
        console.log("Searching nearby...");

        let location = this.state.location;
        console.log("this location = " + this.state.location);

        fetch("/welcome_search?location=" + location)
            .then(checkStatus)
            .then(this.success)
            .catch(this.fail)
    }

    /**getLocation(){
        fetch("api.db-ip.com/addrinfo?api_key=bc2ab711d740d7cfa6fcb0ca8822cb327e38844f&addr=129.100.93.162", {
            method: "GET"
        })
            .then(checkStatus)
            .then(this.success_ip)
            .catch(this.fail)
    }**/

    success(events) {
        console.log("Search result", events);
        this.setState({events: events, loaded: true});
    }

    success_ip(ip) {
        console.log("Search result ip ", ip);
        this.setState({location: ip.city});
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({loaded: true});
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getNearbyEvents();
        }
        let events = this.state.events.map( (event) => {
            return <div className="col-sm-12 col-md-12 col-lg-12 tweet">
                <a href={"/pub/event?q=" + event.eventbrite_id} target="_self"><b>{event.name}</b></a>: {event.description} <br/> Category: {event.category_name}
            </div>
        });
        return (

            <div>
                <h1>Welcome to Eventile! </h1>
                <p> Log sign up or sign in </p>
                <h2>Popular Events Nearby: </h2>
                <div className="col-lg-12">
                    {events}
                </div>
            </div>
        )
    }

}

export default withRouter(WelcomePage);