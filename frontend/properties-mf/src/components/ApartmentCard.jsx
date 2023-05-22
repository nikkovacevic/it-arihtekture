import React from 'react';
import {Box, Button, Grid, Paper} from "@mui/material";
import formatTitle from "../utils/utils";

// eslint-disable-next-line react/prop-types
function ApartmentCard({apartmentData}) {

	return (
		<Grid
			item
			xs={12}
			sm={6}
			md={4}
			lg={3}
		>
			<Paper
				elevation={8}
				sx={{
					display: 'flex',
					flexDirection: 'column',
					justifyContent: 'space-between',
					alignItems: 'center',
					p: 2,
					borderRadius: 4,
					height: '95%'
				}}
			>
				<Box sx={{
					display: 'flex',
					flexDirection: 'column',
					justifyContent: 'flex-start',
				}}>
					<Box>
						<img
							src={apartmentData.image}
							style={{width: '100%', borderRadius: '8px'}}
							alt={'property image'}
						/>
					</Box>
					<Box
						sx={{
							width: '100%',
							display: 'flex',
							flexDirection: 'column',
							justifyContent: 'flex-start'
						}}
					>
                    <span
						style={{
							marginTop: '4px',
							fontSize: '14px',
							color: '#676767'
						}}
					>
                        {apartmentData.location}
                    </span>

						<span
							style={{
								marginTop: '8px',
								fontSize: '24px'
							}}
						>
                        {formatTitle(apartmentData.title)}
                    </span>
						<span
							style={{
								marginTop: '16px',
								fontSize: '18px'
							}}
						>
                        {apartmentData.price}
                    </span>

					</Box>
				</Box>


				<Box
					sx={{
						width: '100%',
						mt: 3,
						display: 'flex',
						justifyContent: 'flex-end'
					}}
				>
					<a
						href={apartmentData.link}
						target='_blank'
					>
						<Button
							variant='text'
							sx={{fontWeight: 'bold'}}
						>
							More info
						</Button>
					</a>
				</Box>
			</Paper>
		</Grid>
	);
}

export default ApartmentCard;