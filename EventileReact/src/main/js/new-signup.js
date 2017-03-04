/**
 * Created by FrankJiao on 2017-03-02.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import 'whatwg-fetch';


class UserForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            pref_music: false,
            pref_bus_prof: false,
            pref_food_drink: false,
            pref_comm_culture: false,
            pref_perf_vis_art: false,
            pref_film_media_ent: false,
            pref_sports_fitness: false,
            pref_health_well: false,
            pref_sci_tech: false,
            pref_trav_outd: false,
            pref_char_games: false,
            pref_religion_spirit: false,
            pref_family_edu: false,
            pref_season_holi: false,
            pref_gov_poli: false,
            pref_fash_beaut: false,
            pref_home_life: false,
            pref_auto_boat_air: false,
            pref_hobbies_ints: false,
            pref_other: false,
        };

        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });
    }

    render() {
        return (

            <form className="form-horizontal" name="signInForm" onSubmit={this.props.onSubmit} ref="signInForm">
                <div className="form-group">
                    <label htmlFor="signin-name" className="col-sm-3 control-label">Username</label>
                    <div className="col-sm-9">
                        <input type="text"
                               className="form-control" id="signin-name"
                               placeholder="Name"
                               ref="name"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="inputPassword3" className="col-sm-3 control-label">Password</label>
                    <div className="col-sm-9">
                        <input type="password"
                               className="form-control"
                               id="signin-password"
                               placeholder="Password"
                               ref="password"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="signin-age" className="col-sm-3 control-label">Age</label>
                    <div className="col-sm-9">
                        <input type="age"
                               className="form-control"
                               id="signin-age"
                               placeholder="Age"
                               ref="age"
                        />
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="userlocation" className="col-sm-3 control-label">Location</label>
                    <div className="col-sm-9">
                        <input type="location"
                               className="form-control"
                               id="signin-location"
                               placeholder="Location"
                               ref="location"
                        />
                    </div>
                </div>

                {/** Checkboxes for preferences **/}

                <div className="form-group">
                    <label htmlFor="pref_music" className="col-sm-3 control-label">Music</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_music"
                            className="form-check" /** might have to change this to align horizontally **/
                            type="checkbox"
                            checked={this.state.pref_music}
                            onChange={this.handleInputChange}
                            ref="pref_music"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_bus_prof" className="col-sm-3 control-label">Business & Professional</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_bus_prof"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_bus_prof}
                            onChange={this.handleInputChange}
                            ref="pref_bus_prof"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_food_drink" className="col-sm-3 control-label">Food & Drink</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_food_drink"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_food_drink}
                            onChange={this.handleInputChange}
                            ref="pref_food_drink"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_comm_culture" className="col-sm-3 control-label">Community & Culture</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_comm_culture"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_comm_culture}
                            onChange={this.handleInputChange}
                            ref="pref_comm_culture"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_perf_vis_art" className="col-sm-3 control-label">Performing & Visual Arts</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_perf_vis_art"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_perf_vis_art}
                            onChange={this.handleInputChange}
                            ref="pref_perf_vis_art"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_film_media_ent" className="col-sm-3 control-label">Film, Media & Entertainment</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_film_media_ent"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_film_media_ent}
                            onChange={this.handleInputChange}
                            ref="pref_film_media_ent"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_sports_fitness" className="col-sm-3 control-label">Sports & Fitness</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_sports_fitness"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_sports_fitness}
                            onChange={this.handleInputChange}
                            ref="pref_sports_fitness"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_health_well" className="col-sm-3 control-label">Health & Wellness</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_health_well"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_health_well}
                            onChange={this.handleInputChange}
                            ref="pref_health_well"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_sci_tech" className="col-sm-3 control-label">Science & Technology</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_sci_tech"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_sci_tech}
                            onChange={this.handleInputChange}
                            ref="pref_sci_tech"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_trav_outd" className="col-sm-3 control-label">Travel & Outdoor</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_trav_outd"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_trav_outd}
                            onChange={this.handleInputChange}
                            ref="pref_trav_outd"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_char_games" className="col-sm-3 control-label">Charity & Games</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_char_games"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_char_games}
                            onChange={this.handleInputChange}
                            ref="pref_char_games"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_religion_spirit" className="col-sm-3 control-label">Religion & Spirituality</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_religion_spirit"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_religion_spirit}
                            onChange={this.handleInputChange}
                            ref="pref_religion_spirit"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_family_edu" className="col-sm-3 control-label">Family & Education</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_family_edu"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_family_edu}
                            onChange={this.handleInputChange}
                            ref="pref_family_edu"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_season_holi" className="col-sm-3 control-label">Seasonal & Holiday</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_season_holi"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_season_holi}
                            onChange={this.handleInputChange}
                            ref="pref_season_holi"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_gov_poli" className="col-sm-3 control-label">Government & Politics</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_gov_poli"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_gov_poli}
                            onChange={this.handleInputChange}
                            ref="pref_gov_poli"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_fash_beaut" className="col-sm-3 control-label">Fashion & Beauty</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_fash_beaut"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_fash_beaut}
                            onChange={this.handleInputChange}
                            ref="pref_fash_beaut"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_home_life" className="col-sm-3 control-label">Home & Lifestyle</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_home_life"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_home_life}
                            onChange={this.handleInputChange}
                            ref="pref_home_life"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_auto_boat_air" className="col-sm-3 control-label">Auto, Boat & Air</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_auto_boat_air"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_auto_boat_air}
                            onChange={this.handleInputChange}
                            ref="pref_auto_boat_air"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_hobbies_ints" className="col-sm-3 control-label">Hobbies & Special Interest</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_hobbies_ints"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_hobbies_ints}
                            onChange={this.handleInputChange}
                            ref="pref_hobbies_ints"
                        />
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="pref_other" className="col-sm-3 control-label">Other</label>
                    <div className="col-sm-9">
                        <input
                            name="pref_other"
                            className="form-check"
                            type="checkbox"
                            checked={this.state.pref_other}
                            onChange={this.handleInputChange}
                            ref="pref_other"
                        />
                    </div>
                </div>

                {/** Submit button **/}

                <div className="form-group">
                    <div className="col-sm-offset-3 col-sm-9">
                        <button type="submit" className="btn btn-default">{this.props.submitLabel}</button>
                    </div>
                </div>
            </form>
        )
    }

    data() {
        let name = ReactDOM.findDOMNode(this.refs.name).value.trim(),
            password = ReactDOM.findDOMNode(this.refs.password).value.trim(),
            age = ReactDOM.findDOMNode(this.refs.age).value.trim(),
            location = ReactDOM.findDOMNode(this.refs.location).value.trim();
        console.log(ReactDOM.findDOMNode(this.refs.pref_music).checked);
        console.log(ReactDOM.findDOMNode(this.refs.pref_bus_prof).checked);

        return {
            username: name,
            password: password,
            age: age,
            location: location
        }
    }

}

export default UserForm;
