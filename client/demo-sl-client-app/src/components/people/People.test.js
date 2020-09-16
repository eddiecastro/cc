import React from 'react';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

import People from './People';

let container = null;
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it("renders", () => {
  act(() => {
    render(<People />, container);
  });
  expect(container.querySelector("[data-testid='PeopleList']"));
  expect(container.querySelector("[data-testid='PeopleFrequency']"));
  expect(container.querySelector("[data-testid='PeopleDuplicate']"));
});