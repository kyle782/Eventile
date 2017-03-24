import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';
import auth from './auth';
import { withRouter } from 'react-router';

import UserForm from './profile-edit-form';


function checkStatus(response) {
    if(response.status >= 200 && response.status < 300) {
        return response.json()
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class UserPage extends React.Component {
    constructor() {
        super();
        this.getUser = this.getUser.bind(this);
        this.success = this.success.bind(this);
        this.fail = this.fail.bind(this);
        this.render = this.render.bind(this);
        this.getUserCreatedEvents = this.getUserCreatedEvents.bind(this);
        this.success_got_created_events = this.success_got_created_events.bind(this);
        this.getUserRSVPEvents = this.getUserRSVPEvents.bind(this);
        this.success_got_rsvp_events = this.success_got_rsvp_events.bind(this);
        this.getUserRatedEvents = this.getUserRatedEvents.bind(this);
        this.success_got_rated_events = this.success_got_rated_events.bind(this);
        this.getRatedEventName = this.getRatedEventName.bind(this);
        this.submitEdit = this.submitEdit.bind(this);
        this.toggleEdit = this.toggleEdit.bind(this);
        this.success_edited = this.success_edited.bind(this);

        this.state = {
            name: '',
            age: '',
            location: '',
            gotUser: false,
            user_preferences: [],
            user_created_events: [],
            user_rsvp_events: [],
            user_ratings: [],
            rated_event: '',
            editing: false,
            auth: JSON.parse(localStorage.auth)
        }
    }

    getUser(){
        let token = this.state.auth.access_token; // authentication token to make sure user is signed in/authorized

        fetch("/api/user", {        // GET the user from the usercontroller, make REST call
            headers: {
                'Authorization': 'Bearer ' + token // pass authentication token as a header to the REST API call
            }
        })
            .then(checkStatus)
            .then(this.success)
            .then(this.getUserCreatedEvents)
            .catch(this.fail)

    }

    getUserCreatedEvents(){
        let token = this.state.auth.access_token;

        fetch("/api/event/get_user_created_events", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_got_created_events)
            .then(this.getUserRSVPEvents)
            .catch(this.fail)
    }

    getUserRSVPEvents(){
        let token = this.state.auth.access_token;

        fetch("/api/user/get_user_rsvp_events", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_got_rsvp_events)
            .then(this.getUserRatedEvents)
            .catch(this.fail)
    }

    getUserRatedEvents(){
        let token = this.state.auth.access_token;

        fetch("/api/user/get_user_rated_events", {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .then(this.success_got_rated_events)
            .catch(this.fail)
    }

    success_got_rated_events(rated_events){
        console.log("VIEW: got user's rated events: ", rated_events);
        this.setState({user_ratings: rated_events});
    }

    success_got_rsvp_events(rsvp_events){
        console.log("!!got user's rsvp events = ", rsvp_events);
        this.setState({user_rsvp_events: rsvp_events});

    }

    success_got_created_events(created_events){
        console.log("!!got user's created events = ", created_events);
        console.log("num of created events = ", created_events.length);
        this.setState({user_created_events: created_events});
    }

    success(user) {
        // update the states with the user JSON object
        console.log("success: user = ", user);
        this.setState({name: user.username, age: user.age, location: user.location,
            gotUser: true, user_preferences: user.preferences});
    }

    fail(error) {
        console.error("Search has failed", error);
        this.setState({gotUser: true});
        if(error.response.status == 401) {
            auth.logOut();
            this.props.router.replace({
                pathname: "/signin",
                state: {nextPath: "/search"}
            })
        }
    }

    getRatedEventName(rated_event){
        let token = this.state.auth.access_token;

        let response_event = fetch("/api/event/show_rated_event?q=" + rated_event.event.id, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(checkStatus)
            .catch(this.fail);

        console.log("rated event = ", response_event);

        this.setState({rated_event: response_event});
    }

    submitEdit(e) {
        e.preventDefault();
        let token = this.state.auth.access_token;
        console.log("token = " + token);
        let form = this.form.data();
        console.log("SUBMITTING EDIT!...", form);

        fetch("/api/user/edit" + "?username=" + form.username + "&password=" + form.password + "&age=" + form.age +
            "&location=" + form.location +
            "&pref_music=" + form.pref_music +
            "&pref_bus_prof=" + form.pref_bus_prof +
            "&pref_food_drink=" + form.pref_food_drink +
            "&pref_comm_culture=" + form.pref_comm_culture +
            "&pref_perf_vis_art=" + form.pref_perf_vis_art +
            "&pref_film_media_ent=" + form.pref_film_media_ent +
            "&pref_sports_fitness=" + form.pref_sports_fitness +
            "&pref_health_well=" + form.pref_health_well +
            "&pref_sci_tech=" + form.pref_sci_tech +
            "&pref_trav_outd=" + form.pref_trav_outd +
            "&pref_char_games=" + form.pref_char_games +
            "&pref_religion_spirit=" + form.pref_religion_spirit +
            "&pref_family_edu=" + form.pref_family_edu +
            "&pref_season_holi=" + form.pref_season_holi +
            "&pref_gov_poli=" + form.pref_gov_poli +
            "&pref_fash_beaut=" + form.pref_fash_beaut +
            "&pref_home_life=" + form.pref_home_life +
            "&pref_auto_boat_air=" + form.pref_auto_boat_air +
            "&pref_hobbies_ints=" + form.pref_hobbies_ints +
            "&pref_other=" + form.pref_other, {
            method: "PUT",
            headers: {
                'Authorization': 'Bearer ' + token
            },
        })
            .then(checkStatus)
            .then(this.success_edited)
            .catch(this.fail)

    }    

    toggleEdit() {
        this.setState({editing: !this.state.editing})
    }

    success_edited(edited_user_response){
        console.log("edited!! resposne = ", edited_user_response);
        this.setState({editing: false});
        this.setState({name: edited_user_response.username, age: edited_user_response.age, location: edited_user_response.location,
            gotUser: true, user_preferences: edited_user_response.preferences});
    }

    render() {

        // needed to stop the infinite looping
        if (this.state.gotUser == false){
            this.getUser();
        }

        let created_events = this.state.user_created_events.map( (created_event) => {
            return <div>
                <a href={"/event?q=" + created_event.eventbrite_id} target="_self"><p>Name: {created_event.name}</p></a>
            </div>
        });

        let prefs = this.state.user_preferences.map( (preference) => {
            return <div>
                <p>{preference}</p>
            </div>
        });

        let rsvp_events = this.state.user_rsvp_events.map( (rsvp_event) => {
            return <div>
                <a href={"/event?q=" + rsvp_event.eventbrite_id}><p>Name: {rsvp_event.name}</p></a>
            </div>
        });

        let rated_events = this.state.user_ratings.map( (rated_event) => {
            return <div>
                <a href={"/event?q=" + rated_event.eventbrite_id}><p>Name: {rated_event.event_name}</p></a>
                <p>Your Rating: {rated_event.users_rating}</p>
            </div>
        });

        return (

            <div>
                <center><h2> Profile Page for {this.state.name} </h2></center>
                <hr/>
                <div className="container">
                    Name: {this.state.name} <br/>
                    Location: {this.state.location} <br/>
                    Age: {this.state.age} <br/>

                    <h2> Selected Preferences: </h2>
                    {prefs}
                    <br/>
                    <h2> Your Created Events: </h2>
                    {created_events}
                    <h2> Your RSVPs: </h2>
                    {rsvp_events}
                    <h2> Your Event Ratings: </h2>
                    {rated_events}
            
                    <button className="btn btn-default" onClick={() => this.toggleEdit()} ref={ (ref) => this.form = ref }>Edit</button>
                    {this.state.editing ?
                        <UserForm submitLabel="Submit Changes" onSubmit={this.submitEdit} ref={ (ref) => this.form = ref }/>
                        : null
                    }
                    
                </div>
            </div>

        )
    }
}

export default withRouter(UserPage);
