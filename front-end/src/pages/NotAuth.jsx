import React, { useState } from "react"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from "@mui/material/Typography";
import { Navigate, NavLink, useNavigate } from "react-router";

const NotAuth = () => {
  


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
                <Typography variant="h1" sx={{ fontSize: "32px", textAlign: "center" }}>Access denied</Typography>
               
                <Typography variant="h1" sx={{ fontSize: "13px", textAlign: "center" }}>Back to Login page</Typography>
                        <NavLink to="/login" style={{ color: "black", textDecoration: "none", fontSize: "13px", fontWeight: "600"}}>Log In to your Account</NavLink>
                
            </Box>
        </Box>
    )

}

export default NotAuth;