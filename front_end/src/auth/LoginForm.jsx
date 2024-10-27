import { Padding, Password } from '@mui/icons-material'
import { Button, Grid, TextField } from '@mui/material'
import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux';
import { Form, useNavigate } from 'react-router-dom'
import { getUser, login } from '../state/auth/Action';

function LoginForm() {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit=(event)=>{
      event.preventDefault();
      const formData = new FormData(event.currentTarget);
      
      const userData = {
        email:formData.get("email"),
        password:formData.get("password")
      }
      dispatch(login(userData))
      console.log("userData : "+userData);
  
  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3} >
            <Grid item xs={12}>
                <TextField
                required
                id='email'
                name='email'
                label='Email'
                fullWidth
                autoComplete='email'
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                required
                id='password'
                name='password'
                label='Password'
                fullWidth
                autoComplete='password'
                />
            </Grid>
            <Grid item xs={12}>
                <Button
                className='bg-[#9155FD] w-full'
                type='submit'
                variant='contained'
                size='large'
                sx={{Padding:" .8rem 0"}}
                >Login</Button>
            </Grid>
        </Grid>
      </form>
      <div className='flex justify-center flex-col items-center'>
        <div className='py-3 flex items-center'>
          <p>If you don't have account? </p>
          <Button onClick={()=>navigate("/register")} className='ml-5' size='small'>Register</Button>
        </div>
      </div>
    </div>
  )
}

export default LoginForm
