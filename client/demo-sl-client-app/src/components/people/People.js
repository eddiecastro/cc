import './People.css';
import React from 'react';
import PeopleList from '../people-list/PeopleList';
import PeopleFrequency from '../people-frequency/PeopleFrequency';
import PeopleDuplicate from '../people-duplicate/PeopleDuplicate';

class People extends React.Component {

  render() {
    return (
      <div className="people-container" style={{ display: "grid", gridTemplateColumns: "repeat(3, 1fr)", gridGap: 20 }}>
        <div>
          <PeopleList data-testid="PeopleList" />
        </div>
        <div>
          <PeopleFrequency data-testid="PeopleList"/>
        </div>
        <div>
          <PeopleDuplicate data-testid="PeopleList" />
        </div>
      </div>
    );
  }
};

export default People;
