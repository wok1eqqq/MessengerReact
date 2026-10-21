import RegistrationPage from "./pages/RegistrationPage.jsx"
import LoginPage from "./pages/LoginPage.jsx";
import UserTable from "./pages/UserTable.jsx";
import NotAuth from "./pages/NotAuth.jsx";
import { NavLink } from "react-router";
import { BrowserRouter, Route, Routes } from "react-router-dom"
import { createTheme, ThemeProvider } from "@mui/material/styles";
<style>
@import url('https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:ital,wght@0,200..800;1,200..800&display=swap');
</style>

function Context() {
const theme = createTheme({
  typography: {
    fontFamily: '"Plus Jakarta Sans", sans-serif',
  },
});
  return (
    <>
     <ThemeProvider theme={theme}>
      <Routes>
        <Route path="/" element={<RegistrationPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/table" element={<UserTable />} />
        <Route path="/notAuth" element={<NotAuth />} />
      </Routes>
</ThemeProvider>
    </>
  )

}

function App() {
  return (
    <BrowserRouter>
      <Context />
    </BrowserRouter>
  );
}

export default App;
