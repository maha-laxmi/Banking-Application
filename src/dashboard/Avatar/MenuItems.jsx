import React,{useState} from 'react'
import { useSelector,useDispatch } from 'react-redux';
import { updateSelectAccount } from '../../switchAccountSlice';
const MenuItems = () => {
  const [click,setclick]=useState(false);
  const handleClick=()=>setclick(!click);
  let {listOfAccount}=useSelector(state => state.reducer);
  let  dispatch = useDispatch()
  

  return (
    <>
    <div className="dropdown">
    <button className="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
    Dropdown button
  </button>
    <ul
        className="dropdown-menu"
      >
        {listOfAccount.map((item, index) => {
          console.log(item);
          return (
            <li key={index} onClick={() => dispatch(updateSelectAccount(item.accountNumber))} 
            className="dropdown-item">
                {item.accountNumber}
            </li>
          );
        })}
      </ul>
    </div>
      
    </>
  );
}

export default MenuItems