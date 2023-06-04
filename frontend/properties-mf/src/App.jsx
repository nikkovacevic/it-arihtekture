import { useEffect, useState } from 'react';
import './App.css'
import axios from 'axios';
import ApartmentCard from './components/ApartmentCard.jsx';
import { Box, Grid, TablePagination } from '@mui/material';

const API_URL = 'http://localhost:8081/api/properties/'
function App() {
    const [apartments, setApartments] = useState([]);

    const [pageNumber, setPageNumber] = useState(0);
    const [pageSize, setPageSize] = useState(12);
    const [count, setCount] = useState(0);

    const loadApartments = () => {
        let requestDto = {
            pageNumber: pageNumber,
            pageSize: pageSize
        };

        axios.post(API_URL, requestDto)
            .then((res) => {
                console.log(res)
                setApartments(res.data.results);
                setCount(res.data.resultCount);
            })
            .catch((error) => console.log(error));

    };

    useEffect(() => {
        loadApartments();
    }, [pageNumber, pageSize])

    const handlePageChange = (event, newPageNumber) => {
        setPageNumber(newPageNumber);
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        })
    };

    const handleSizeChange = (event) => {
        setPageSize(parseInt(event.target.value, 10));
        setPageNumber(0)
    };

  return (
      <Box
      >
          <Grid
              container
              spacing={4}
              sx={{
                  p: 4,
                  display: 'flex',
                  justifyContent: 'center'
              }}
          >
              {apartments.map((apartment) => (
                  <ApartmentCard apartmentData={apartment} key={apartment.id}/>
              ))}

          </Grid>

          <TablePagination
              component='div'
              count={count}
              page={pageNumber}
              onPageChange={handlePageChange}
              rowsPerPage={pageSize}
              rowsPerPageOptions={[12, 48, 100]}
              onRowsPerPageChange={handleSizeChange}

              sx={{
                  my: 4,
                  display: 'flex',
                  justifyContent: 'center'
              }}
          />

      </Box>
  )
}

export default App
