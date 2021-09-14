import styled from 'styled-components/macro'

export default function Header() {
    return (
        <Wrapper>
            <Logo className="logo" src="Logo_LetsPlayDarts.webp" alt="logo"/>
        </Wrapper>
    )
}

const Wrapper = styled.header`
  align-items: center;
  background-color: #3c3c3c;
  display: flex;
  justify-content: center;
  margin-bottom: 40px;
`

const Logo = styled.img `
  .logo {
    width: 350px;
    padding: 10px 10px;
  }
`