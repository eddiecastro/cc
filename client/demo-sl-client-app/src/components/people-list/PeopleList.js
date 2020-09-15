import React from 'react';
import './PeopleList.css';
import PropTypes from 'prop-types';

class PeopleList extends React.Component {
  render() {
    const peopleListData = this.props.listOfPeople;

    const peopleList = peopleListData.map((person, i) =>
        <tr key={i} align="left">
          <td>{person.display_name}</td>
          <td>{person.email_address}</td>
          <td>{person.title}</td>
        </tr>
    );
      
    return (
      <div data-testid="PeopleList">
        <table> 
          <thead>
            <tr align="left">
              <th>Name</th>
              <th>Email</th>
              <th>Title</th>
            </tr>
          </thead>
          <tbody>
            {peopleList}
          </tbody>
        </table>
      </div>
    );
  }
};

PeopleList.propTypes = {
  listOfPeople: PropTypes.array.isRequired,
}

PeopleList.defaultProps = {
};

export default PeopleList;