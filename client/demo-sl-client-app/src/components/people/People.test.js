import React from 'react';
import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import People from './People';

describe('<People />', () => {
  test('it should mount', () => {
    render(<People />);
    
    const people = screen.getByTestId('People');

    expect(people).toBeInTheDocument();
  });
});