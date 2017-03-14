import React from 'react';
import UserForm from './new-signup';
import 'whatwg-fetch';
import {withRouter} from 'react-router';

const checkStatus = (response) => {
    if(response.status >= 200 && response.status < 300) {
        return response.json();
    } else {
        var error = new Error(response.statusText);
        error.response = response;
        throw error;
    }
};

class SignUp extends React.Component {
    constructor() {
        super();
        this.state = {
            name: '',
            password: '',
            error: ''
        };
        this.signUp = this.signUp.bind(this);
    }

    signUp(e) {
        e.preventDefault();
        let form = this.form.data();
        console.log("Signing up!...", form);

        let body = "username=" + form.username + "&password=" + form.password + "&age=" + form.age +
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
                "&pref_other=" + form.pref_other
            ;

        fetch("/api/signup", {
            method: 'POST',
            headers: {
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"
            },
            body: body
        })
        .then(checkStatus)
        .then(this.success.bind(this))
        .catch(this.readError.bind(this))
        .then(this.fail.bind(this))
    }

    success(user) {
        console.log("Signed up, please sign in", user);
        this.props.router.replace("/signin");
    }

    readError(error) {
        console.log("Failed to sign up", error);
        return error.response.json();
    }

    fail(error) {
        if(error) this.setState({error: error.error});
    }

    render () {
        let Error = () => <p className="alert alert-danger">{this.state.error}</p>;
        return (

            <div className="col-sm-4 col-sm-offset-4">
                { this.state.error ? <Error/> : null }
                <UserForm submitLabel="Sign up" onSubmit={this.signUp} ref={ (ref) => this.form = ref }/>

            </div>
        )
    }

}

export default withRouter(SignUp);

