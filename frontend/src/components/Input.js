import styled from 'styled-components/macro'

export default styled.input`
  color: #808080;
  font-size: 20px;
  font-weight: 800;
  height: 50px;
  margin: 20px 0 20px 0;
  padding-left: 20px;
  width: 100%;

  :hover {
    border-color: rgba(250, 200, 0, .5);
  }

  :focus-visible {
    outline-color: rgba(250, 200, 0, .5);
  }
`