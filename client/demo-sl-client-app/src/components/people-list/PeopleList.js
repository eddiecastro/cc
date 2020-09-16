import React from 'react';
import './PeopleList.css';
import PropTypes from 'prop-types';
import Pagination from '../pagination/Pagination';
import Person from '../person/Person';

class PeopleList extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      peopleListData: [],
      peopleMetaData: null,
    };

    this.getPeople = this.getPeople.bind(this);
  }

  componentDidMount() {
    this.getPeople(1, 100); //TODO: constants
  }

  getPeople(page, pageSize) {
    const apiUrl = "http://localhost:8080/people?page=" + page + "&page_size=" + pageSize;

    fetch(apiUrl)
      .then(res => res.json())
      .then(
        (result) => {
          this.handleResult(result);
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  handleResult(result) {
    const peopleList = result.data.map((person, i) => 
      <Person key={i} person={person} />
    );

    this.setState({
      isLoaded: true,
      peopleListData: peopleList,
      peopleMetaData: result.metadata
    });
  }

  render() {
    const { error, isLoaded, peopleListData, peopleMetaData } = this.state;

    if (error) {
      return <div>Error loading people: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading people..</div>
    } else {
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
              {peopleListData}
            </tbody>
          </table>
          <Pagination totalPages={this.state.peopleMetaData.paging.total_pages} pageChangeCallback={this.getPeople} />
        </div>
      );
    }
  }
};

PeopleList.propTypes = {
}

PeopleList.defaultProps = {
};

export default PeopleList;