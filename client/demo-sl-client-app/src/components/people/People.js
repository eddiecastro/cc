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
          <PeopleList/>
        </div>
        <div>
          <PeopleFrequency />
        </div>
        <div>
          <PeopleDuplicate />
        </div>
      </div>
    );
  }
};

export default People;
