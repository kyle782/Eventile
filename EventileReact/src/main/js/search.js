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

class Search extends React.Component {

    constructor() {
        super();
        this.search = this.search.bind(this);
        this.fail = this.fail.bind(this);
        this.success = this.success.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);

        this.state = {
            events: [],
            auth: JSON.parse(localStorage.auth),
            sort_date: false,
            sort_dist: false,
            found_events: true
        }
    }

    search(e) {
        e.preventDefault();
        console.log("Searching...");
        let token = this.state.auth.access_token;
        let query = ReactDOM.findDOMNode(this.refs.query).value.trim();

        this.setState({inProgress: true});

        fetch("/api/search?q=" + query + "&date=" + this.state.sort_date , {
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
        console.log("number of events = " + events.length);
        this.setState({events: events, inProgress: false});
        if (events.length > 0){
            this.setState({found_events: true});
        } else {
            this.setState({found_events: false});
        }
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({inProgress: false});
        if(error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    getImageURL(event){
        return event.img_url;
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        console.log(target.type);

        this.setState({
            [name]: value
        });
    }

    render() {

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
                    <small className="text-muted"> Category: {event.category_name} </small>
                </div>
            </div>
        });
        let Error = () => <div className="container">
            <p className="alert alert-danger">Sorry, no events found!</p>
        </div>;
        return (

            <div className="container">
                <center><h2> Search Page </h2></center>
                <hr/>
                <div className="row">
                    <div className="container">
                        <form className="col-lg-7" onSubmit={this.search} >
                            <div className="form-group col-lg-7">
                                <label className="sr-only" htmlFor="query">Search:</label>
                                <input type="text"
                                       className="form-control"
                                       id="query"
                                       placeholder="Enter your search query here! Example: drake"
                                       ref="query"
                                       disabled={this.state.inProgress}
                                />
                            </div>
                            <button type="submit" className="btn btn-default" disabled={this.state.inProgress}>Search!</button>
                        </form>
                    </div>
                </div>
                <br/>
                <h> Sort By  </h>

                <div className="form-group">
                    <label htmlFor="sort_date" className="col-sm-3 control-label">Date</label>
                    <div className="col-sm-9">
                        <input
                            name="sort_date"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.sort_date}
                            onChange={this.handleInputChange}
                            ref="sort_date"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="restrict_date" className="col-sm-3 control-label">Distance</label>
                    <div className="col-sm-9">
                        <input
                            name="sort_dist"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.sort_dist}
                            onChange={this.handleInputChange}
                            ref="sort_dist"
                        />
                    </div>
                </div>
                {this.state.found_events ?
                    <div className="card-columns">
                        {events}
                    </div>
                    :
                    <Error/>
                }
            </div>
        )
    }

}

export default withRouter(Search);
