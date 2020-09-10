import React from 'react';
import styles from './PeopleList.module.css';

class PeopleList extends React.Component {
  render() {
    const peopleListData = this.props.listOfPeople;

    const peopleList = peopleListData.map((person) =>
        <tr align="left">
          <td>{person.display_name}</td>
          <td>{person.email_address}</td>
          <td>{person.title}</td>
        </tr>
      );
      
      return (
        <div className={styles.PeopleList} data-testid="PeopleList">
          <table border="1px solid black" border-spacing="0"> 
            <tr align="left">
              <th>Name</th>
              <th>Email</th>
              <th>Title</th>
            </tr>
            {peopleList}
          </table>
        </div>
      );
  }
};

export default PeopleList;