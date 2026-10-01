import { Container, Typography } from "@mui/material";
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { NavLink } from "react-router";
const Header = () => {
  return (
    <AppBar position="static"  sx={{ backgroundColor: "purple" }}>
      <Toolbar >
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Social Net
        </Typography>
        <NavLink to="/home" style={{ color: "white", textDecoration: "none" }}>
          <Button color="inherit" >Home</Button>
        </NavLink>
        <NavLink to="/profile" style={{ color: "white", textDecoration: "none" }}><Button color="inherit" >Login</Button></NavLink>
        <NavLink to="/create-article" style={{ color: "white", textDecoration: "none" }}><Button color="inherit" >Register</Button></NavLink>
      </Toolbar>
    </AppBar>
  )
}

export default Header