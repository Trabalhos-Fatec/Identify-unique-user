import React, { useState, useEffect, useRef } from "react";
import { Link, useHistory } from "react-router-dom";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { InputMask } from "primereact/inputmask";
import { Toast } from "primereact/toast"
import FingerprintJS from '@fingerprintjs/fingerprintjs'
import api from '../../services/api';
import axios from 'axios'

// Styles
import "./styles.css";

export default function Resgister() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [telefone, setPhone] = useState();
  const [IP, setIP] = useState();
  const [fingerprint, setFingerprint] = useState("");
  const [components, setComponents] = useState("");
  const history = useHistory();
  const toast = useRef();
  const tracking = useRef([]);
  const presses = useRef([]);
  let konamitest = [0,0,0,0,0,0,0,0,0,0]
  const konami = ['ArrowUp','ArrowUp','ArrowDown','ArrowDown','ArrowLeft','ArrowRight','ArrowLeft','ArrowRight','b','a']                                             
  

  useEffect(
    () => {
      const update = (e) => {
        tracking.current.push({'x':e.x, 'y':e.y, 'click':false})
      }
      const updateClick = e => {
        tracking.current.push({'x':e.x, 'y':e.y, 'click':true})
      }
      const updateKey = e => {
        presses.current.push(e.key)
        konamitest=[konamitest[1],konamitest[2],konamitest[3],konamitest[4],konamitest[5],konamitest[6],konamitest[7],konamitest[8],konamitest[9],e.key]
        let isK = true
        for(const press in konamitest){
          if(konamitest[press]!==konami[press])
            isK=false
        }
        if(isK)
        alert('Wahoo!')
      }
      window.addEventListener('mousemove', update)
      window.addEventListener('touchmove', update)
      window.addEventListener('mousedown', updateClick)
      window.addEventListener('keydown', updateKey)
      return () => {
        window.removeEventListener('mousemove', update)
        window.removeEventListener('touchmove', update)
        window.removeEventListener('mousedown', updateClick)
        window.removeEventListener('keydown', updateKey)
      }
    },
  )


  useEffect(() => {
    const fpPromise = FingerprintJS.load();
    (async () => {
        const fp = await fpPromise
        const result = await fp.get()

        setFingerprint(result.visitorId)
        setComponents(JSON.stringify(result))

        const res = await axios.get('https://geolocation-db.com/json/')
        setIP(res.data.IPv4)
      })()

  }, []);

  async function handleRegister(event) {
    event.preventDefault();

    const data = {
      "nome": name,
      "senha": password,
      "fingerprint": fingerprint,
      "components": components,
      "presses": JSON.stringify(presses),
      "mouse": JSON.stringify(tracking),
      "autorizacao": [{ "nome": "ROLE_USER" }],
      "dados": {
        "telefone": [{ telefone }],
        "email": [{ email }],
      }
    }

    api({
      method: 'post',
      url: '/usuario/',
      data: data
    })
      .then(function (response) {
        toast.current.show({ severity: 'success', summary: 'Sucesso', life: 3000 });
        history.push("/")
      }).catch((error) => {
        toast.current.show({ severity: 'error', summary: 'Erro!', detail: 'Falha ao contatar o servidor' });
      })
  }

  function teste() {
    axios({
      method: 'get',
      url: 'http://localhost:8080/tracerouter/chaining',
      "templated": false
    })
      .then((response) => {
        console.log(response)
      }).catch((error) => {
        console.log(error)
        toast.current.show({ severity: 'error', summary: 'Erro!', detail: 'Falha ao contatar o servidor' });
      })
  }

  function teste2() {
    axios({
      method: 'get',
      url: `http://localhost:8080/tracerouter/tracerouter/${IP}`
    })
      .then((response) => {
        console.log(response)
      }).catch((error) => {
        console.log(error)
        toast.current.show({ severity: 'error', summary: 'Erro!', detail: 'Falha ao contatar o servidor' });
      })
  }
  return (
    <div className="logon-container">
      <button onClick={teste}>teste</button>
      <button onClick={teste2}>teste</button>
      <div className="surface-card p-5 shadow-6 border-round">
        <section className="my-4">
          <h1 className="no-underline text-blue-500">Cadastro de usuário</h1>
        </section>
        <Toast ref={toast} />
        <form onSubmit={handleRegister}>
          <div className="flex">
            <div className="flex flex-row">
              <div className="col">
                <div className="">
                  <label
                    htmlFor="email"
                    className="block text-800 font-medium mt-5"
                  >
                    Nome
                  </label>
                  <InputText
                    id="username"
                    className="w-full mb-3"
                    required
                    placeholder="Nome"
                    value={name}
                    onChange={(event) => setName(event.target.value)}
                  />
                </div>
                <div className="">
                  <label
                    htmlFor="email"
                    className="block text-800 font-medium mt-3"
                  >
                    E-mail
                  </label>
                  <InputText
                    id="email"
                    className="w-full mb-3"
                    required
                    type="email"
                    placeholder="E-mail"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                  />
                </div>
              </div>
              <div className="col">
                <div className="">
                  <label
                    htmlFor="phone"
                    className="block text-800 font-medium mt-5"
                  >
                    Telefone
                  </label>
                  <InputMask
                    id="phone"
                    mask="(99) 9999-9999?9"
                    className="w-full mb-3"
                    required
                    value={telefone}
                    maxlength="12"
                    placeholder="(99) 99999-9999"
                    onChange={(e) => setPhone(e.target.value)}
                  />
                </div>
                <div className="">
                  <label
                    htmlFor="email"
                    className="block text-800 font-medium mt-3"
                  >
                    Senha
                  </label>
                  <InputText
                    id="password"
                    type="password"
                    required
                    className="w-full mb-3"
                    placeholder="Senha"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                  />

                </div>
              </div>
            </div>
          </div>
          <div className="line-height-3 mt-5 text-sm">
            <Button label="Cadastrar" className="w-full" type="submit" />
          </div>
        </form>
        <div className="line-height-3 mt-5 text-sm flex-row-reverse">
          <Link className="no-underline text-blue-500" to="/">
            &#8617;	 Voltar ao início
          </Link>
        </div>
      </div>
    </div>
  )
  }