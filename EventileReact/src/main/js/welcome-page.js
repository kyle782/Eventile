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
        this.fail = this.fail.bind(this);
        this.success = this.success.bind(this);
        this.success_ip = this.success_ip.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

        this.state = {
            events: [],
            loaded: false,
            location: 'London, Ontario',
            new_location: '',
            searching: false
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

    success(events) {
        console.log("Search result", events);
        this.setState({events: events, loaded: true, searching: false});
    }

    success_ip(ip) {
        console.log("Search result ip ", ip);
        this.setState({location: ip.city});
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({loaded: true, searching: false});
    }

    getImageURL(event){
        return event.img_url;
    }

    handleChange(event) {
        this.setState({new_location: event.target.value});
    }

    handleSubmit(event) {
        console.log("new location: " + this.state.new_location);
        this.setState({location: this.state.new_location, searching: true, loaded: false});
        this.getNearbyEvents();
        event.preventDefault();
    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getNearbyEvents();
        }
        let events = this.state.events.map( (event) => {
            return <div className="card">
                {this.state.searching ? null :
                    <div>
                        <a href={"/pub/event?q=" + event.eventbrite_id} target="_self">
                            <img className="card-img-top img-fluid" src={this.getImageURL(event)}/>
                        </a>
                        <div className="card-block">
                            <a href={"/pub/event?q=" + event.eventbrite_id} target="_self">
                                <h4 className="card-title">{event.name}</h4></a>
                            <p className="card-text">{event.description}</p><br/>
                        </div>
                        <div className="card-footer">
                            <small className="text-muted"> Category: {event.category_name} </small>
                        </div>
                    </div>
                }
            </div>
        });
        return (
           <div className="main">
               <div className="intro-header">
                   <div className="container">
                       <div className="row">
                           <div className="col-lg-12">
                               <div className="intro-message">
                                   <h1>Welcome to Eventile!</h1>
                                   <h3>Register/Login to find personalized events nearby!</h3>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>

                <div className="container">
                    <div>
                        <br/>
                        <center><h2>Popular Events Nearby (London, Ontario)</h2></center>
                        <hr/>
                        <form onSubmit={this.handleSubmit} className="form-inline">
                            <label>Not your location? Change it here: &ensp; </label>
                            <input type="text" value={this.state.new_location} onChange={this.handleChange}
                                   className="form-control" placeholder="London, Ontario"/>
                            <input type="submit" value="Submit" className="btn btn-default"/>
                        </form>
                        <br/>
                        { this.state.searching ?
                            <p>Searching....please wait...</p> :
                            null
                        }
                        <div className="card-columns">
                            {events}
                        </div>
                    </div>
                </div>
           </div>
        )
    }

}

export default withRouter(WelcomePage);