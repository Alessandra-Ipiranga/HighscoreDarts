import styled from 'styled-components/macro'

export default styled.input`
  margin: 20px 0 20px 0;
  height: 50px;
  padding-left: 20px;
  width: 100%;

  :hover {
    border-color: rgba(250, 200, 0, .5);
  }

  :focus-visible {
    outline-color: rgba(250, 200, 0, .5);
  }
`