import React, { useState } from "react"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from "@mui/material/Typography";
import { NavLink, useNavigate } from "react-router";
// import jwt from 'jsonwebtoken'

const RegistrationPage = () => {
    const [userName, setUserName] = useState();
    const [password, setPassword] = useState();
    const [email, setEmail] = useState();

    const passwordHints = [
        "Use 8 or more characters",
        "Use upper and lower case letters (e.g. Aa)",
        "Use a number (e.g. 1234)",
        "Use a symbol (e.g. !@#$)"
    ];
    function onEdit(event) {
        if (event.key == "Enter") {
            let data = {
                password: password,
                username: userName,
                email: email,
            }

            fetch("http://localhost:8080/auth/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            }).then(
                (response) => {
                    console.log(response)
                }
            )

            console.log(data)


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
                gap: "60px",
                padding: 4,
                borderRadius: 4,
                maxWidth: 500,
                backgroundColor: 'rgba(255, 255, 255, 0)',
                boxShadow: 3,
            }}>

                <Typography variant="h1" sx={{ fontSize: "32px", textAlign: "center", fontWeight: 600 }}>Create an account</Typography>

                <Box sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    // justifyContent: "center",
                    alignItems: "center",
                    gap: 4,
                    padding: 4,

                }}>
                    <Box sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        // justifyContent: "center",
                        alignItems: "center",
                        gap: 3,
                        padding: 4,

                    }}>
                        <TextField sx={{
                            width: "100%",
                            "& .MuiOutlinedInput-root": {
                                borderRadius: "12px",

                                "&.Mui-focused fieldset": {
                                    borderColor: "#999",
                                },
                            },
                            "& .MuiInputLabel-root.Mui-focused": {
                                color: "#999",
                            },
                        }} variant="outlined" label="Email" type="email" onKeyDown={onEdit} onChange={(e) => setEmail(e.target.value)} />
                        <TextField label="Username" sx={{
                            width: "100%",
                            "& .MuiOutlinedInput-root": {
                                borderRadius: "12px",

                                "&.Mui-focused fieldset": {
                                    borderColor: "#999",
                                },
                            },
                            "& .MuiInputLabel-root.Mui-focused": {
                                color: "#999",
                            },
                        }} variant="outlined" onKeyDown={onEdit} onChange={(e) => setUserName(e.target.value)} />
                        <TextField label="Password" sx={{
                            width: "100%", borderRadius: "30px",
                            "& .MuiOutlinedInput-root": {
                                borderRadius: "12px",

                                "&.Mui-focused fieldset": {
                                    borderColor: "#999",
                                },
                            },
                            "& .MuiInputLabel-root.Mui-focused": {
                                color: "#999",
                            },
                        }} variant="outlined" type="password" onKeyDown={onEdit} onChange={(e) => setPassword(e.target.value)} />
                        <Box
                            sx={{
                                display: "grid",
                                gridTemplateColumns: "1fr 1fr",
                                gap: "12px 24px",
                            }}
                        >
                            {passwordHints.map((hint) => (
                                <Typography
                                    key={hint}
                                    sx={{
                                        color: "#999",
                                        display: "flex",
                                        alignItems: "center",
                                        gap: 1,
                                        fontSize: "11px"

                                    }}
                                >
                                    {/* <Box
                                        sx={{
                                            width: 6,
                                            height: 6,
                                            borderRadius: "50%",
                                            bgcolor: "#999",
                                            flexShrink: 0,
                                        }}
                                    /> */}
                                    {hint}
                                </Typography>

                            ))}
                        </Box>
                    </Box>
                    <Box sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        // justifyContent: "center",
                        alignItems: "center",
                        gap: 2,
                        padding: 4,

                    }}>
                        <Typography variant="h1" sx={{ fontSize: "13px", textAlign: "center" }}>Already have account?</Typography>
                        <NavLink to="/login" style={{ color: "black", textDecoration: "none", fontSize: "13px", fontWeight: "600"}}>Log In to yout Account</NavLink>
                        <Typography variant="h1" sx={{ fontSize: "14px", textAlign: "center" }}>By creating an account, you agree to the Terms of use and Privacy Policy.</Typography>
                    </Box>

                </Box>

            </Box>
        </Box>
    )

}

export default RegistrationPage;