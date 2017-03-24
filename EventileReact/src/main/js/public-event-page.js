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

        this.state = {
            name: 'Loading...',
            description: '',
            category: '',
            rating: '.....',
            image_url: '',
            venue_address: '',
            venue_longitude: '',
            venue_latitude: '',
            loaded: false,
            comments: [],
        }

    }

    success_found_event(event_result) {
        this.setState({
            name: event_result.name, description: event_result.description,
            category: event_result.category_name, venue_address: event_result.venue_address,
            venue_longitude: event_result.longitude, venue_latitude: event_result.latitude
        });
        if (event_result.num_ratings != 0) {
            this.setState({rating: event_result.average_rating})
        }
        if (event_result.image_url != ""){
            this.setState({image_url: event_result.img_url})
        }
    }


    fail(error) {
        console.log("failed " + error);
        this.setState({loaded: true})

    }

    getEvent(){
        let query = this.props.location.query.q;

        fetch("/view/event?q=" + query)
            .then(checkStatus)
            .then(this.success_found_event)
            .catch(this.fail)

    }

    render() {
        // stops the infinite looping & app crashing
        if (this.state.loaded == false){
            this.getEvent();
        }

        return (
            <div className="main">
                <br/>
                <center><h2>Event: {this.state.name}</h2></center>
                <hr/>

                <div className="row">

                    <div className="col-md-8">
                        <img className="img-responsive" src={this.state.image_url} alt=""/>
                    </div>

                    <div className="col-md-4">
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

export default withRouter(PublicEventPage);