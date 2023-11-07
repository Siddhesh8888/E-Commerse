import { Grid } from '@mui/material'
import React from 'react'
import OrderCard from './OrderCard'

function Order() {
    const orderStatus = [
        { label: 'On The Way', value: 'on_the_way' },
        { label: 'Delivered', value: 'delivered' },
        { label: 'Canceled', value: 'canceled' },
        { label: 'Returned', value: 'returned' }
    ]
    return (
        <div className='px-5 lg:px-10'>
            <Grid container spacing={2} sx={{ justifyContent: 'space-between' }}>
                <Grid item xs={2.5}>
                    <div className='p-5 border rounded-md shadow-lg h-auto sticky top-5 ml-5'>
                        <h1 className='lext-lg font-bold'>Filter</h1>
                        <div className='space-y-4 mt-10'>
                            <h1 className='uppercase font-semibold'>order status</h1>
                            {orderStatus.map((option) =>
                                <div className='flex items-center'>
                                    <input type='checkbox' defaultValue={option.value} className='h-4 w-4 text-indigo-400 focus:ring-indigo-500' />
                                    <label className='ml-3 text-sm' htmlFor={option.value}>{option.label}</label>
                                </div>)}
                        </div>
                    </div>
                </Grid>
                <Grid item xs={9}>
                    <div className='space-y-5'>
                        {[1, 1, 1, 1].map((item) => <OrderCard />)}
                    </div>
                </Grid>
            </Grid>
        </div>
    )
}

export default Order