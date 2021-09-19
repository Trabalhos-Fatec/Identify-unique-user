import { Button } from "primereact/button";
import { Link } from "react-router-dom";

import "./styles.css";

export default function Sucesso() {


  return (
      <div className="logon-container">
        <div className="row">Sucesso</div>        
        <Link className="no-underline text-blue-500" to="/">
          <Button className="ml-3" to="/">
            Continuar
          </Button>
        </Link>
      </div>
  );
}
