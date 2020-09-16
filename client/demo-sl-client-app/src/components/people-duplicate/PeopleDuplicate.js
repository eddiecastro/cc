import React from 'react';
import './PeopleDuplicate.css';
import Person from '../person/Person';
import PropTypes from 'prop-types';

class PeopleDuplicate extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      buttonClicked: false,
      peopleDuplicates: []
    }

    this.getDuplicates = this.getDuplicates.bind(this);
  }

  render() {
    if (this.state.buttonClicked === false) {
      return (
        <button onClick={this.getDuplicates}>
          Show potential duplicates
        </button>
      );
    } else {
      return (
        <div>
          <button onClick={this.getDuplicates}>
            Hide potential duplicates
          </button>
          <div data-testid="PeopleDuplicate"></div>
            <div>
              {this.state.peopleDuplicates}
            </div>
        </div>
      );
    }
  }

  getDuplicates() {
    if(this.state.buttonClicked === false) {
      const apiUrl = "http://localhost:8080/people/duplicates"

      fetch(apiUrl)
        .then(res => res.json())
        .then(
          (result) => {
            this.handleDuplicateResult(result);
          },
          (error) => {
            this.setState({
              //TODO: should show an alert
              error
            });
          }
        )
    } else {
      this.setState({
        peopleDuplicates: [],
        buttonClicked: false
      });
    }
  }

  handleDuplicateResult(result) {
    const duplicates = result.map((r, i) => {
      return (
        <div key={i} className="duplicate-container">
          <tr align="left">
           <td>Duplicate {i}</td>
          </tr>
          <Person person={r.duplicate1} />
          <Person person={r.duplicate2} />
        </div>
      )
    });

    this.setState({
      peopleDuplicates: duplicates,
      buttonClicked: true
    });
  }
};

PeopleDuplicate.propTypes = {
}

PeopleDuplicate.defaultProps = {
};

export default PeopleDuplicate;