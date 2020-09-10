import React from 'react';
import PeopleList from '../people-list/PeopleList';

class People extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      peopleListData: [],
      peopleMetaData: null
    };
  }

  componentDidMount() {
    this.getPeople(1, 100); //TODO: constants
  }

  render() {
    const { error, isLoaded, peopleListData, peopleMetaData } = this.state;

    if (error) {
      return <div>Error loading people: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading people..</div>
    } else {
      return <PeopleList listOfPeople={peopleListData}></PeopleList>
    }
  }

  /**
   * NOTE: For the sake of this step (and the one after):
   * Create a button that, when clicked, displays a frequency count of all the unique characters in all the 
   * email addresses of all the People you have access to, sorted by frequency count (the count below).
   * 
   * We're pulling all possible pages of people so that all characters can be counted and all duplicates can be considered.
   * */
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

  handleResult(result, page) {
    this.setState({
      isLoaded: true,
      peopleListData: result.data,
      peopleMetaData: result.metadata
    });

    /**
    this.state.peopleMetaData.paging
    {current_page: 1, next_page: 2, prev_page: 0, total_pages: 4, total_count: 351}
     */

     /**
      * a better solution:
      * add a button for paging so user can see all users
      * if user presses the email character count button, let the backend gather all of the possible users and give that info to the UI
      * if user presses the duplicate finder button, let the backend gather all of the possible duplicates and give that info to the UI
      */
  }

};

export default People;
