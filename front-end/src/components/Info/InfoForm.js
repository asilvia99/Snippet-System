import React from 'react';

class InfoForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Updated Snippet Info was submitted: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input disabled={!this.props.isCreator} type="text" value={this.state.value} onChange={this.handleChange} placeHolder = "Enter snippet info"/>
                {this.props.isCreator &&
                <input type="submit" value="Save"/>
                }
            </form>
        );
    }
}

export default InfoForm;