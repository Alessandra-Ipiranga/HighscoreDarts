import styled from 'styled-components/macro'

export default styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: var(--background-light);
  color: var(--neutral-light);
  display: flex;
  place-items: center;
  grid-template-rows: min-content 1fr min-content;
  align-items: center;
  justify-content: center;
  `