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
        this.search = this.search.bind(this);
        this.fail = this.fail.bind(this);
        this.success = this.success.bind(this);

        this.state = {
            events: [],
            auth: JSON.parse(localStorage.auth)
        }

    }

    search(e) {

        e.preventDefault();
        console.log("Searching...");
        let token = this.state.auth.access_token;
        let query = ReactDOM.findDOMNode(this.props);
        console.log("query = " + query);


        fetch("/api/event?q=" + query , {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
        .then(checkStatus)
        .then(this.success)
        .catch(this.fail)
    }

    success(events) {
        console.log("Search result", events);
        this.setState({events: events});
    }

    fail(error) {
        console.error("Search has failed", error);
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
            .then(this.success)
            .catch(this.fail)

    }

    render() {
        let events = this.state.events.map( (event) => {
            return <div className="col-sm-12 col-md-12 col-lg-12 tweet">
                <b>{event.name}</b>: {event.description} <br/> Category: {event.category_name}
            </div>
        });
        return (
            <div onLoad={this.getEvent()}>
                <h1>Hello World</h1>
                <div className="col-lg-12">
                    {events}
                </div>
            </div>

        )
    }
}

export default withRouter(EventPage);