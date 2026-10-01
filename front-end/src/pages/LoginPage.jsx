import React, { useState } from "react"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from "@mui/material/Typography";
import { Navigate, NavLink, useNavigate } from "react-router";

const LoginPage = () => {
    const navigate = useNavigate();
    const [userName, setUserName] = useState();
    const [password, setPassword] = useState();

    let [loginError, setLoginError] = useState(false)


    function onEdit(event) {
        if (event.key == "Enter") {
            let data = {
                password: password,
                username: userName,
            }

            fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => {
                    if (response.status == 200) {
                        navigate("/table")
                    } else {
                        console.log(response)
                        setLoginError(true)
                    }
                    return response.text();
                })



        }
    }

    return (
        <Box sx={{
            display: 'flex',
            justifyContent: "center",
            alignItems: "center",
            width: "100%",
            height: "100vh"
        }}>
            <Box sx={{
                display: 'flex',
                flexDirection: 'column',
                // justifyContent: "center",
                alignItems: "center",
                gap: 2,
                padding: 4,
                borderRadius: 4,
                maxWidth: 400,
                backgroundColor: 'rgba(255, 255, 255, 0)',
                boxShadow: 3,
            }}>
                <Typography variant="h1" sx={{ fontSize: "32px", textAlign: "center" }}>Login</Typography>
                <TextField error={loginError} sx={{
                    "& .MuiOutlinedInput-root": {
                        transition: "all 1s ease",

                        "& fieldset": {
                            transition: "border-color 1s ease",
                        },

                        "&.Mui-error fieldset": {
                            borderColor: "error.main",
                        },
                    },

                    "& .MuiInputLabel-root": {
                        transition: "color 0.3s ease",
                    },
                }} label="Email" variant="outlined" onKeyDown={onEdit} onChange={
                    (e) => {
                        setUserName(e.target.value);
                        setLoginError(false)
                    }
                } />
                <TextField error={loginError} label="Password" variant="outlined" onKeyDown={onEdit} onChange={(e) => setPassword(e.target.value)} />
            </Box>
        </Box>
    )

}

export default LoginPage;