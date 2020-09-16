import React from 'react';
import './Person.css';
import PropTypes from 'prop-types';
import Pagination from '../pagination/Pagination';

class Person extends React.Component {

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <tr align="left">
        <td>{this.props.person.display_name}</td>
        <td>{this.props.person.email_address}</td>
        <td>{this.props.person.title}</td>
      </tr>
    );
  }
};

Person.propTypes = {
  person: PropTypes.object,
}

Person.defaultProps = {
  person: []
};

export default Person;