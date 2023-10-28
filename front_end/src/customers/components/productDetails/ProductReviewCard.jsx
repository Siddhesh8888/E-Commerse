import { Avatar, Box, Grid, Rating } from '@mui/material';
import React from 'react'

function ProductReviewCard() {
    return (
        <div>
            <Grid container>
                <Grid item xs={1} spacing={2} gap={3}>
                    <Box>
                        <Avatar className='text-white' sx={{bgcolor: "#9155fd" }}>R</Avatar>
                    </Box>
                </Grid>
                <Grid item xs={9}>
                    <div className='space-y-2'>
                        <p className='font-semibold text-lg'>Ram</p>
                        <p className='opacity-70'>April 5, 2023</p>
                    </div>
                    <Rating value={4.5} name='half-rating' readOnly precision={.5}/>
                    <p>nice product, i like this shirt</p>
                </Grid>
            </Grid>
        </div>
    )
}

export default ProductReviewCard;