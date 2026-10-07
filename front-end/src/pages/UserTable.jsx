import React, { useState } from "react"
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from "@mui/material/Typography";
import { NavLink, useNavigate } from "react-router";
import { DataGrid } from '@mui/x-data-grid';
import Paper from '@mui/material/Paper';

// import jwt from 'jsonwebtoken'



const UserTable = () => {
    const [userName, setUserName] = useState();
    const [password, setPassword] = useState();
    const [email, setEmail] = useState();

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'userName', headerName: 'Username', width: 130 },
        { field: 'password', headerName: 'Password', width: 130 },
    ];


    const rows = [
        { id: 1, userName: 'Snow', password: 'Jon' },
        { id: 2, lastName: 'Lannister', firstName: 'Cersei' },
        { id: 3, lastName: 'Lannister', firstName: 'Jaime' },
        { id: 4, lastName: 'Stark', firstName: 'Arya' },
    ];

    const paginationModel = { page: 0, pageSize: 5 };

    fetch("http://localhost:8080/test/auth", {
        method: "GET",
        credentials: "include",
        headers: {
            "Content-Type": "application/json"
        },
        //body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status == 200) {
                console.log("ok");
            }
            else if (response.status == 403) {
                console.log("access denied");
            }
        });


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
                alignItems: "center",
                gap: 2,
                padding: 4,
                borderRadius: 4,
                maxWidth: 400,
                backgroundColor: 'rgba(255, 255, 255, 0)',
                boxShadow: 3,
            }}>
                <Paper sx={{ height: 400, width: '100%' }}>
                    <DataGrid
                        rows={rows}
                        columns={columns}
                        sx={{ border: 0 }}
                    />
                </Paper>
            </Box>
        </Box>
    )

}

export default UserTable;