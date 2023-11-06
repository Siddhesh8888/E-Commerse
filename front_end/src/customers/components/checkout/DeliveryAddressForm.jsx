import { Box, Button, Grid, TextField } from '@mui/material'
import React from 'react'
import AddressCard from '../addressCard/AddressCard'

function DeliveryAddressForm() {
    const handleSubmit = (e) => {
        e.preventDefault();

        const data = new FormData(e.currentTarget)
        const address = {
            firstName: data.get("firstName"),
            lastName: data.get("lastName"),
            streetAdderss: data.get("address"),
            city: data.get("city"),
            state: data.get("state"),
            zipCode: data.get("zip"),
            mobile: data.get("phoneNumber")
        }
        console.log("Address : ", address)
    }
    return (
        <div>
            <Grid container spacing={4}>
                <Grid item xs={12} sm={5} className='border rounded-md shadow-md h-[30rem] overflow-y-scroll'>
                    <div className='p-5 pt-7 border-b cursor-pointer'>
                        <AddressCard />
                        <Button variant='contained' sx={{ mt: 2 }} size='large'>Deliver Here</Button>
                    </div>

                </Grid>
                <Grid item xs={12} sm={7}>
                    <Box className='border rounded-md shadow-md p-5'>
                        <form onSubmit={handleSubmit}>
                            <Grid container spacing={3}>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='firstName' name='firstName' label='First Name' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='lastName' name='lastName' label='Last Name' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12}>
                                    <TextField required id='address' name='address' label='Address' autoComplete='given-name' fullWidth multiline rows={4} />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='city' name='city' label='City' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='state' name='state' label='State' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='zip' name='zip' label='Zip/Postal Code' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <TextField required id='phoneNumber' name='phoneNumber' label='Phone Number' autoComplete='given-name' fullWidth />
                                </Grid>
                                <Grid item xs={12} sm={6}>
                                    <Button variant='contained' sx={{ py: 1.5, mt: 2 }} size='large' type='submit'>Deliver Here</Button>
                                </Grid>
                            </Grid>
                        </form>

                    </Box>
                </Grid>
            </Grid>
        </div>
    )
}

export default DeliveryAddressForm