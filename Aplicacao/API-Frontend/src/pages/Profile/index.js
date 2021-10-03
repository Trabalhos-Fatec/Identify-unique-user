import React from "react";
import {  useHistory } from "react-router-dom";
import { Button } from "primereact/button";

// import api from '../../services/api';

export default function Profile() {
  // const [secrets, setSecrets] = useState([]);
  // const [dropdown, setDropdown] = useState("");

  const history = useHistory();

  // useEffect( () => {
  //     if(isAuthenticated()){
  //         api.get('secrets', {
  //             headers: {
  //                 Authorization: 'Bearer ' + getToken(),
  //             }
  //         }).then(response => {
  //             setSecrets(response.data.data);
  //         })
  //     }else{
  //         logout();
  //         localStorage.clear();
  //         history.push('/');
  //     }
  // }, [history]);

  function handleLogout() {
    localStorage.clear();
    history.push("/");
  }

    return (
    <div className="">
      <header
        style={{
          borderBottomWidth: 1,
          borderBottomColor: "lightgray",
          borderBottomStyle: "solid",
        }}
        className="py-2 mb-5"
      >
        <div className="flex">
          <div className="flex-none flex align-items-center justify-content-center">
            <Button className="ml-3" onClick={handleLogout}>
              Sair
            </Button>
            <Button className="ml-3" onClick={()=>{history.push("/users");}}>
              Usuários
            </Button>
          </div>
          <></>
          <div className="flex-grow-1 flex align-items-center justify-content-center">
            <h1 className="text-blue-900">Painel de controle</h1>
          </div>
        </div>
      </header>
    </div>
  );
}
