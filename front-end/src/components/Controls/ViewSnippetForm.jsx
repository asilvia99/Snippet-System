import React from 'react';

class ViewSnippetForm extends React.Component {

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
        if (this.state.value !== '') {
            this.props.history.push("/snippet/" + this.state.value);
        }
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input type="text" value={this.state.value} onChange={this.handleChange}
                       placeholder="Enter snippet id"/>
                <input type="submit" value="View"/>
            </form>
        );
    }
}

export default ViewSnippetForm;