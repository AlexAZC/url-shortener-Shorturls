import * as React from 'react';
import Modal from '@mui/material/Modal';
import NuevoUrlCorto from './NuevoUrlCorto';


const AcortadorPopUp = ({open, setOpen, refetch}) => {

    const handleClose = () => {
        setOpen(false);
    }

  return (
    <Modal
    open={open}
    onClose={handleClose}
    aria-labelledby="modal-modal-title"
    aria-describedby="modal-modal-description"
  >
    <div className='flex justify-center items-center h-full w-full'>
        <NuevoUrlCorto setOpen={setOpen} refetch={refetch}/>
    </div>
    {/*<Box>
      <Typography id="modal-modal-title" variant="h6" component="h2">
        Text in a modal
      </Typography>
      <Typography id="modal-modal-description" sx={{ mt: 2 }}>
        Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
      </Typography>
    </Box>*/}
  </Modal>
  )
}
export default AcortadorPopUp



